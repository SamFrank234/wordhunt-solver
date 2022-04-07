package solver;

import org.jetbrains.annotations.NotNull;

public class Word implements Comparable<Word>{
    private DoublyLinkedList<LetterNode> word;
    private int points;
    private String wordAsStr;

    public Word(LetterNode firstLetter) {
        word = new DoublyLinkedList<>();
        word.addFirst(firstLetter);
        wordAsStr = firstLetter.toString();
        points = 0;
    }

    public Word(Word oldWord, LetterNode newLetter) {
        this.word = oldWord.word;
        addLetter(newLetter);
    }

    public void addLetter(LetterNode letter) {
        wordAsStr+=letter.toString();
        word.addLast(letter);
    }

    public boolean contains(LetterNode letter) {
        return word.contains(letter);
    }

    public LetterNode firstLetter() {
        return word.first();
    }

    public LetterNode lastLetter() {
        return word.last();
    }

    public void removeLast(){
        wordAsStr = wordAsStr.substring(0, wordAsStr.length()-1);
        word.removeLast();
    }

    public int size(){
        return word.size();
    }

    public int points() {
        switch (word.size()) {
            case 3:
                return 100;
            case 4:
                return 400;
            case 5:
                return 800;
            case 6:
                return 1400;
            case 7:
                return 1800;
            case 8:
                return 2200;
            case 9:
                return 2600;
            case 10:
                return 3000;
        }
        return 0;
    }

    @Override
    public String toString() {
        return wordAsStr;
    }

    @Override
    public int compareTo(@NotNull Word o) {
        if(this.size()!=o.size()){
            return o.size() - this.size();
        }
        return this.toString().compareTo(o.toString())*(-1);
    }
}