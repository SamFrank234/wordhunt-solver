package solver;

import java.util.ArrayList;
import java.util.Objects;

public class LetterNode implements Comparable<LetterNode>{
    public char letter = '-';
    private int row = 0;
    private int col = 0;
    private ArrayList<LetterNode> neighbors = new ArrayList<>();
    private ArrayList<String> words = new ArrayList<>();
    private int totalPts = 0;
    private int totalLets = 0;


    public LetterNode(char let, int row, int col) {
        letter = let;
        this.row = row;
        this.col = col;
    }

    public LetterNode(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public void setLetter(char let) {
        letter = let;
    }

    public void addNeighbor(LetterNode node) {
        neighbors.add(node);
    }

    public void addWord(Word word){
        WordHunt.addInOrder(words, word.toString()); //What is best practice here? Nested class?
        totalPts+=word.points();
        totalLets+=word.size();
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public char getLetter() {
        return letter;
    }

    public ArrayList<LetterNode> neighbors() {
        return neighbors;
    }

    public double scoreIndex(){
        return Math.pow(totalPts, 1.25)/totalLets;
    }

    public String toString() {
        return(String.valueOf(letter));
    }

    public String getCoords(){
        StringBuilder sb = new StringBuilder("(");
        sb.append(row+1);
        sb.append(", ");
        sb.append(col+1);
        sb.append(")");
        return sb.toString();
    }

    public ArrayList<String> getWords() {
        return words;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LetterNode)) return false;
        LetterNode that = (LetterNode) o;
        return letter == that.getLetter() && row == that.getRow() && col == that.getCol();
    }

    @Override
    public int hashCode() {
        return Objects.hash(letter, row, col);
    }

    @Override
    public int compareTo(LetterNode other) {
        return (int)(this.scoreIndex()- other.scoreIndex());
    }

    public void printNeighbors(){
        for (LetterNode neighbor :
                neighbors) {
            System.out.print(neighbor+", ");
        }
    }
}