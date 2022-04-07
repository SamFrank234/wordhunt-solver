package solver;

import javafx.PathLineChart;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class WordHunt {

    private final LetterNode[][] board;
    private final Dictionary dict;
    private final ArrayList<String> words = new ArrayList<>();
    private int totalPts = 0;
    private int emptyLetters = 16;


    //Main functions: Create Empty Board, Add Letters, Find Words, Find Best solver.Path
    public WordHunt(int sideLength ) {
        board = new LetterNode[sideLength][sideLength];
        for (int row = 0; row < sideLength; row++) {
            for (int col = 0; col < sideLength; col++) {
                board[row][col] = new LetterNode(row, col);
                setNeighbors(board[row][col]);
            }
        }

        dict = new Dictionary("/Users/sam/Downloads/WordHuntSolver/src/scrabbleDict.txt");
    }

    private void setNeighbors(LetterNode node) {
        for (int row = -1; row <= 1; row++) {
            for (int col = -1; col <= 1; col++) {
                    try {
                        LetterNode neighbor = board[node.getRow() + row][node.getCol() + col];
                        if(neighbor!=null && neighbor!=node){
                            node.addNeighbor(neighbor);
                            neighbor.addNeighbor(node);
                        }
                    } catch (IndexOutOfBoundsException e) {
                        //System.out.println("IndexOutOfBoundsException");
                    }

            }
        }
    }

    public boolean addLetters(String letters) {
        if (board == null) {
            return false;
        }

        if (letters.length() != board.length * board.length) {
            return false;
        }
        char[] chars = letters.toCharArray();
        int letIndex = 0;
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board.length; col++) {
                board[row][col].setLetter(chars[letIndex]);
                letIndex++;
            }
        }
        return true;
    }

    public void findWords() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                Word newWord = new Word(board[row][col]);
                recWordBuilder(newWord);
            }
        }
    }

    private void recWordBuilder(Word word) {
        String currWord = word.toString();
        if (currWord.length() >= 3 && dict.wordExists(currWord)) {
            if(word.firstLetter().getWords().size()==0){ emptyLetters --;}
            word.firstLetter().addWord(word);
            addInOrder(words, currWord);
            totalPts+=word.points();
        }
        for (int i = 0; i < word.lastLetter().neighbors().size(); i++) {
            LetterNode nextLetter = word.lastLetter().neighbors().get(i);
            String testStr = currWord + nextLetter;
            if (!word.contains(nextLetter) && dict.wordBeginsWith(testStr)) {
                word.addLetter(nextLetter);
                recWordBuilder(word);
            }
        }
        word.removeLast();
    }

    public Path findOptimalPath(){
        WHPathFinder pathFinder = new WHPathFinder(this);
        return pathFinder.optimalPath();
    }
    //End of main functions


    //Getters used by PathFinder or in Testing
    public int emptyLetters(){
        return emptyLetters;
    }

    public LetterNode[][] getBoard(){
        return board;
    }

    public Dictionary dict(){
        return dict;
    }

    //End of Getters


    //Various Print Methods:
    public void printBoard(){
        for (LetterNode [] row: board){
            for(LetterNode node : row){
                System.out.print(node.toString()+"\t");
            }
            System.out.println();
        }
    }

    public void printNeighbors(){
        for (LetterNode[] row :
                board) {
            for (LetterNode node :
                    row) {
                System.out.print(node+":");
                for (LetterNode neighbor :
                        node.neighbors()) {
                    System.out.print("\t"+neighbor);
                }
                System.out.println();
            }
        }
    }

    public void printWords(){
        for (String s :
                words) {
            System.out.println(s);
        }
    }
    
    public void printWordsByLetter(){
        for (LetterNode [] row: board){
            for(LetterNode node : row){
                System.out.print(node.toString()+": ");
                    for (int i = 0; i < node.getWords().size(); i++) {
                        System.out.print(node.getWords().get(i));
                        if(i!=node.getWords().size()-1) { System.out.print(", "); }
                    }
                System.out.println();
            }
        }
    }

    public void printStats(){
        for (LetterNode [] row: board){
            for(LetterNode node : row){
                double roundedScoreIndex = (double)((int)(node.scoreIndex()*100+0.5))/100;
                System.out.print(node.toString()+": "+roundedScoreIndex+"\t\t");
                if(roundedScoreIndex==0.0) {
                    System.out.print("\t");
                }
            }
            System.out.println();
        }
    }

    public void printMaxScore(){
        System.out.println("Maximum Possible Score: "+totalPts);
    }
    //End of Print methods


    public static void main(String[] args) {
        WordHunt game = new WordHunt(4);

        LocalTime start = LocalTime.now();
        //protects against bad entry (wrong number of letters)
        if(game.addLetters("otekdshrarsthalu")){
            game.printBoard();
            LocalTime wordStart = LocalTime.now();
            game.findWords();
            LocalTime wordEnd = LocalTime.now();

            System.out.println("\nAll possible words(longest to shortest)");
            game.printWords();

            System.out.println("\nAll possible words grouped by starting letter:");
            game.printWordsByLetter();

            System.out.println("\nLetter score indexes mapped onto board:");
            game.printStats();

            System.out.println("\n");
            game.printMaxScore();

            System.out.println("\nOptimal Path:");
            LocalTime pathStart = LocalTime.now();
            System.out.println(game.findOptimalPath());
            LocalTime pathEnd = LocalTime.now();

            LocalTime end = LocalTime.now();
            System.out.println("All Words and Optimal Path found in "+ elapsedTime(start, end)+" seconds:");
            System.out.println("\u2022 "+elapsedTime(wordStart, wordEnd)+" seconds to find all words");
            System.out.println("\u2022 "+elapsedTime(pathStart, pathEnd)+" seconds to find optimal path");

            Scanner s = new Scanner(System.in);
            System.out.println("Type G/g to see the line graph of this path, or type any other key to quit");

            if(s.next().equalsIgnoreCase("g")) {
                PathLineChart.graph(game.findOptimalPath());
            }

            System.out.println("\nProgram Complete");

        }

    }

    private static double elapsedTime(LocalTime start, LocalTime end){
        double startNano = start.getNano()+start.getSecond()*Math.pow(10,9);
        double endNano = end.getNano()+end.getSecond()*Math.pow(10,9);
        return (endNano-startNano)/Math.pow(10,9);
    }

    //Helper Method for inserting into arrays also used by solver.LetterNode
    public static void addInOrder(ArrayList<String> list, String str) {
        for (int i = 0; i < list.size(); i++) {
            if (str.length() > list.get(i).length()) {
                list.add(i, str);
                return;
            }
            if(str.equals(list.get(i))){
                return;
            }
            if (str.length() == list.get(i).length() && str.compareTo(list.get(i)) < 0) {
                list.add(i, str);
                return;
            }
        }
        list.add(str);
    }
}

