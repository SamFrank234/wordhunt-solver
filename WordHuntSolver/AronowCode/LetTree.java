import java.io.IOException;
import java.util.ArrayList;

public class LetTree {
    private LetNode root;
    private ArrayList<String> words;
    public static String res="";

    public LetTree(LetNode root){
        this.root = root;
        words = new ArrayList();
    }

    public LetNode getRoot(){
        return root;
    }

    public void findWords(LetNode node,String cur) {
        cur+=node.getVal();
        for (int i=0; i<node.getNodes().size(); i++){
            findWords(node.getNode(i),cur);
        }
       if (cur.length()>2 && !words.contains(cur) && Dictionary.isValidWord(cur.toUpperCase())) {
            words.add(cur);
        }
    }

    public ArrayList<String> getWords(){
        return words;
    }
}