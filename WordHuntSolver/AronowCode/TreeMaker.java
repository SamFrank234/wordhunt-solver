import java.io.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class TreeMaker {
    public static LetTree[][] trees;
    public static char[][] board;
    public static boolean seen;
    public static LetNode temp;
    public static String cur;

    public static void main(String[] args) throws IOException {

        trees = new LetTree[4][4];
        board = makeBoard();

        //LocalTime start = java.time.LocalTime.now();
        //LocalTime dictStart = java.time.LocalTime.now();
        Dictionary.makeDictionary();
        //LocalTime dictEnd = java.time.LocalTime.now();

        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                trees[row][col] = new LetTree(new LetNode(board[row][col],row,col));
                trees[row][col].getRoot().setParent(trees[row][col].getRoot());
            }
        }
        /*LocalTime makeTreeStart = java.time.LocalTime.now();
        LocalTime makeTreeEnd = java.time.LocalTime.now();
        LocalTime findWordsStart = java.time.LocalTime.now();
        LocalTime findWordsEnd = java.time.LocalTime.now();
        LocalTime entireTreeStart = java.time.LocalTime.now();

        double findWordsTimeTaken=0;
        double makeTreeTimeTaken =0;
*/
        ArrayList<String> words = new ArrayList();
        for (LetTree[] treeArr : trees){
            for (LetTree tree: treeArr){
                //makeTreeStart = java.time.LocalTime.now();
                makeTree(tree.getRoot());
               // makeTreeEnd = java.time.LocalTime.now();

                /*double makeTreeStartNano = makeTreeStart.getNano()+makeTreeStart.getSecond()*Math.pow(10,9);
                double makeTreeEndNano = makeTreeEnd.getNano()+makeTreeEnd.getSecond()*Math.pow(10,9);
                makeTreeTimeTaken += (makeTreeEndNano-makeTreeStartNano)/Math.pow(10,9);

                findWordsStart = java.time.LocalTime.now();*/
                tree.findWords(tree.getRoot(),"");
                /*findWordsEnd = java.time.LocalTime.now();

                double findWordsStartNano = findWordsStart.getNano()+findWordsStart.getSecond()*Math.pow(10,9);
                double findWordsEndNano = findWordsEnd.getNano()+findWordsEnd.getSecond()*Math.pow(10,9);
                findWordsTimeTaken += (findWordsEndNano-findWordsStartNano)/Math.pow(10,9);
*/
                for (String word : tree.getWords()){
                    words.add(word);
                }
            }
        }
        //LocalTime entireTreeEnd = java.time.LocalTime.now();
        words = sortList(words);
        for (String word: words){
            System.out.println(word);
        }
        /*LocalTime end = java.time.LocalTime.now();

        double startNano = start.getNano()+start.getSecond()*Math.pow(10,9);
        double endNano = end.getNano()+end.getSecond()*Math.pow(10,9);
        double timeTaken = (endNano-startNano)/Math.pow(10,9);
        System.out.println("seconds in total: "+ timeTaken);

        System.out.println("seconds to make tree: "+ makeTreeTimeTaken);

        System.out.println("seconds to make words: "+ findWordsTimeTaken);

        double entireTreeStartNano = entireTreeStart.getNano()+entireTreeStart.getSecond()*Math.pow(10,9);
        double entireTreeEndNano = entireTreeEnd.getNano()+entireTreeEnd.getSecond()*Math.pow(10,9);
        double entireTreeTimeTaken = (entireTreeEndNano-entireTreeStartNano)/Math.pow(10,9);
        System.out.println("seconds for whole tree process: "+ entireTreeTimeTaken);

        double dictStartNano = dictStart.getNano()+dictStart.getSecond()*Math.pow(10,9);
        double dictEndNano = dictEnd.getNano()+dictEnd.getSecond()*Math.pow(10,9);
        double dictTimeTaken = (dictEndNano-dictStartNano)/Math.pow(10,9);
        System.out.println("seconds for dictionary formation: "+ dictTimeTaken);*/
    }

   public static void makeTree(LetNode root) {
        ArrayList<LetNode> neighbors = getNeighbors(root);
        for (LetNode node : neighbors) {
            cur = "";//here
            seen = false;
            temp = root;
            while (temp.getParent() != temp) {
                if (temp.getParent().equals(node)) {
                    seen = true;
                    break;
                }
                temp = temp.getParent();
            }
            if (!seen) {
                findBranchString(root); //here
                if (Dictionary.isBeginWord(cur)) { //here
                    root.addNode(node);
                    node.setParent(root);
                    makeTree(node);
                }//here
            }
        }

   }

   public static void findBranchString(LetNode base){
       if (!base.getParent().equals(base)){
           findBranchString(base.getParent());
       }
       cur += base.getVal();
   }

    public static ArrayList<LetNode> getNeighbors(LetNode node) {
        ArrayList<LetNode> neighbors = new ArrayList();
        for (int row = node.getRow() - 1; row <= node.getRow() + 1; row++) {
            for (int col = node.getCol() - 1; col <= node.getCol() + 1; col++) {
                try {
                    if (row!=node.getRow() || col!=node.getCol()) {
                        neighbors.add(new LetNode(board[row][col], row, col));
                    }
                } catch (IndexOutOfBoundsException e) {
                }
            }
        }
        return neighbors;
    }

    public static char[][] makeBoard(){
        char[][] board = new char[4][4];
        System.out.println("enter the letters");
        Scanner kyb = new Scanner(System.in);
        String lets = kyb.nextLine();
        lets = lets.toUpperCase();
        for (int i =0; i<16; i++){
            board[i/4][i%4] = lets.charAt(i);
        }
        return board;
    }

    public static ArrayList<String> sortList(ArrayList<String> words) {
        for (int i = 1; i < words.size(); i++) {
            String temp = words.get(i);
            int j = i - 1;
            while (j >= 0 && temp.length() < words.get(j).length()) {
                words.set(j + 1, words.get(j));
                j--;
            }
            words.set(j + 1, temp);
        }
        return words;
    }
}
