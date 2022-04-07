package solver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Dictionary {

    private String dictFilePathName;
    private ArrayList <String> entries = new ArrayList<>();

    public Dictionary(String dictFilePathName){
        this.dictFilePathName = dictFilePathName;
        try{
            parseDictFile();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    private void parseDictFile() throws IOException {
        BufferedReader dictReader = new BufferedReader(new FileReader(dictFilePathName));
        for(String curWord = dictReader.readLine(); curWord!=null; curWord=dictReader.readLine()){
            entries.add(curWord.toUpperCase());
        }
    }

    public boolean wordExists(String target){
        return recContains(target.toUpperCase(),0, entries.size());
    }

    private boolean recContains(String target, int lo, int hi){
        if(lo>hi){return false;}
        int mid = (lo+hi)/2;
        if(entries.get(mid).equals(target)){ return true;}
        if(entries.get(mid).compareTo(target)>0){return recContains(target, lo, mid-1);}
        return recContains(target, mid+1, hi);
    }

    //checks to see if any words in dictionary start with given substring
    public boolean wordBeginsWith(String targetWordStartsWith){
        return recRootChecker(targetWordStartsWith.toUpperCase(), 0, entries.size());
    }

    private boolean recRootChecker(String target, int lo, int hi){
        if(lo>hi){
            return false;
        }
        int mid = (lo+hi)/2;
        if(entries.get(mid).length()>=target.length() && target.equals(entries.get(mid).substring(0,target.length()))){
            return true;
        }
        if(entries.get(mid).compareTo(target)>0){
            return recRootChecker(target, lo, mid-1);
        }
        return recRootChecker(target, mid+1, hi);
    }


    public static void main(String [] args){
        Dictionary dict = new Dictionary("/Users/sam/Downloads/WordHuntSolver/src/scrabbleDict.txt");
        System.out.println(dict.wordExists("car"));
        System.out.println(dict.wordBeginsWith("aspha"));
    }
}
