package solver;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Path implements Comparable<Path>{

    private static class PathNode {
        private LetterNode element;
        private PathNode parent;
        private int depth;
        private double cumScore;
        private double cumArea;

        public PathNode(PathNode parent, LetterNode element){
            this.element = element;
            this.parent = parent;
            cumScore = element.scoreIndex();
            cumArea = element.scoreIndex()/2;
            depth = 0;
            if(parent!=null){
                cumScore+= parent.getCumScore();
                cumArea += parent.getCumArea() + parent.getCumScore();
                depth = parent().getDepth()+1;
            }
        }

        public LetterNode element(){
            return element;
        }

        public double getScoreIndex(){
            return element.scoreIndex();
        }

        public PathNode parent(){
            return parent;
        }

        public double getCumScore() {
            return cumScore;
        }

        public double getCumArea() {
            return cumArea;
        }

        public int getDepth() {
            return depth;
        }

        public String toString(){
            return element.toString();
        }
    }

    private PathNode root;

    public Path(PathNode root){
        this.root = root;
    }

    public Path (LetterNode letter){
        this.root = new PathNode(null, letter);
    }

    public Path addChild(LetterNode child){
        return new Path(new PathNode(root, child));
    }

    public ArrayList<LetterNode> neighbors(){
        return root.element().neighbors();
    }

    public double getCumScore(){
        return root.getCumScore();
    }

    public double getCumArea(){
        return root.getCumArea();
    }

    public int size(){
        return root.getDepth()+1;
    }

    public boolean contains(LetterNode node){
        PathNode walk = root;
        while(walk!=null){
            if(walk.element().equals(node)){
                return true;
            }
            walk = walk.parent();
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        PathNode walk = root;
        while(walk!=null){
            sb.append(walk.toString());
            sb.append(walk.element().getCoords());
            sb.append(": ");
            for (int i=0; i<walk.element().getWords().size(); i++) {
                sb.append(walk.element().getWords().get(i));
                if(i<walk.element().getWords().size()-1){ sb.append(", ");}
            }
            sb.append("\n");
            walk = walk.parent();
        }
        return sb.toString();
    }

    @Override
    public int compareTo(@NotNull Path other) {
        return (int)(this.root.getCumScore()/(this.size()) - other.root.getCumScore()/(other.size()));
    }

    public String[][] scoresArr(){
       String[][] answer = new String[2][root.getDepth()+1];
        PathNode walk = root;
        for(int i=0; i<answer[0].length; i++){
            answer[0][i] = walk.element().toString() + walk.element.getCoords();
            answer[1][i] = String.valueOf(walk.getScoreIndex());
            walk=walk.parent();
        }
        return answer;
    }
}
