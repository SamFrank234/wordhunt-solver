package solver;

import java.util.ArrayList;

public class WHPathFinder {

    private final WordHunt game;
    private Path minPath;
    public double minArea=0;
    private final PriorityQueue<Path> queue;

    public WHPathFinder(WordHunt wh){
        game = wh;
        queue = new PriorityQueue<>(Priority.MIN);
    }

    public Path optimalPath(){
        for ( int row = 0; row<game.getBoard().length; row++) {
            for ( int col = 0; col<game.getBoard()[row].length; col++) {
                if(game.getBoard()[row][col].scoreIndex()>0){
                    Path path = new Path(game.getBoard()[row][col]);
                    queue.enqueue(path);
                }
            }
        }
        recPathSearch(queue.dequeue());
        return minPath;
    }

    private void recPathSearch(Path path){
        if(path.size()== 16- game.emptyLetters() &&
                ( minPath ==null || path.getCumArea()< minPath.getCumArea() )){
            minPath = path;
            minArea = path.getCumArea();
            //System.out.println("Hooray");
        }
        ArrayList<LetterNode> neighbors = path.neighbors();
        for (LetterNode neighbor : neighbors) {

            double additionalArea = path.getCumScore() + neighbor.scoreIndex() / 2;
            if (!path.contains(neighbor) && neighbor.scoreIndex() > 0 &&
                    (minArea == 0.0 || path.getCumArea() + additionalArea < minArea)) {
                Path newest = path.addChild(neighbor);
                //System.out.println("Enqueue");
                if (minArea == 0.0 || newest.getCumArea() < minArea) {
                    queue.enqueue(newest);
                }
            }

            if (!queue.isEmpty()) {
                recPathSearch(queue.dequeue());
            }
        }
    }
}
