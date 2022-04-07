import java.io.IOException;

public class LetTester {

    public static void main(String[] args) throws IOException {
        Dictionary.makeDictionary();
        LetTree tree = new LetTree(new LetNode('A'));

        tree.getRoot().addNode('B');
        tree.getRoot().addNode('C');
        tree.getRoot().addNode('D');

        tree.getRoot().getNode(0).addNode('E');
        tree.getRoot().getNode(1).addNode('F');
        tree.getRoot().getNode(1).addNode('G');
        tree.getRoot().getNode(1).addNode('H');
        tree.getRoot().getNode(2).addNode('I');
        tree.getRoot().getNode(2).addNode('J');

        tree.getRoot().getNode(0).getNode(0).addNode('K');
        tree.getRoot().getNode(0).getNode(0).addNode('L');
        tree.getRoot().getNode(1).getNode(0).addNode('M');
        tree.getRoot().getNode(1).getNode(1).addNode('N');
        tree.getRoot().getNode(1).getNode(1).addNode('O');
        tree.getRoot().getNode(2).getNode(1).addNode('P');
        tree.getRoot().getNode(2).getNode(1).addNode('Q');
        tree.getRoot().getNode(2).getNode(1).addNode('R');
        tree.getRoot().getNode(2).getNode(1).addNode('S');



        tree.getRoot().getNode(2).getNode(1).getNode(0).addNode('T');
        tree.getRoot().getNode(2).getNode(1).getNode(0).addNode('U');
        tree.getRoot().getNode(2).getNode(1).getNode(3).addNode('V');
        tree.getRoot().getNode(2).getNode(1).getNode(0).getNode(0).addNode('W');
        tree.getRoot().getNode(2).getNode(1).getNode(0).getNode(1).addNode('X');
        tree.getRoot().getNode(2).getNode(1).getNode(3).getNode(0).addNode('Y');
        tree.getRoot().getNode(2).getNode(1).getNode(3).getNode(0).addNode('Z');

        //a
        //b c d
        //e f g h i j
        //k l m n o

        //  |               |-------k
        //  |       |-------e
        //  |-------b       |-------l
        //  |
        //  |
        //  |
        //  |       |-------f-------m
        //  |       |
        //  |       |       |-------n
        //  |-------c-------g
        //  |       |       |-------o
        //  |       |
        //  |		|-------h
        //  |
        //  a
        //  |       |-------i
        //  |       |
        //  |       |
        //  |		|               |-------t------w
        //  |-------d       |-------p
        //  |       |       |       |-------u------x
        //  |       |       |
        //  |       |       |-------q
        //  |		|-------j
        //  |               |
        //  |               |-------r
        //  |               |
        //  |               |              |-------y
        //  |               |-------s------v
        //  |                              |------z

        tree.findWords(tree.getRoot(),"");
        System.out.println(tree.getWords());

    }
}
