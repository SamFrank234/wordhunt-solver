import java.util.ArrayList;

public class LetNode {
    private char val;
    private int row;
    private int col;
    private LetNode parent;

    private ArrayList<LetNode> nodes;

    public LetNode(char val){
        this.val = val;
        nodes = new ArrayList<>();
    }

    public LetNode(char val, int row, int col){
        this.val = val;
        this.row = row;
        this.col = col;
        nodes = new ArrayList<>();
    }

    public int getRow(){
        return row;
    }

    public int getCol(){
        return col;
    }

    public void setParent(LetNode parent){
        this.parent = parent;
    }

    public void addNode(char nodeVal){
        nodes.add(new LetNode(nodeVal));
    }
    public void addNode(LetNode node){
        nodes.add(node);
    }

    public ArrayList<LetNode> getNodes(){
        return nodes;
    }
    public LetNode getNode(int index){
        return nodes.get(index);
    }
    public LetNode getParent(){
        try{
            return parent;
        }
        catch(NullPointerException e){
           return null;
        }
    }

    public char getVal(){
        return val;
    }

    public boolean equals(LetNode other){
            return this.row==other.row && this.col ==other.col;
    }

    public String toString(){
        return "("+row+","+col+"); "+val;
    }

}
