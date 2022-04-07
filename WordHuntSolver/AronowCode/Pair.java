public class Pair {
    private char let;
    private int begin;
    private int end;

    public Pair(char let, int begin, int end){
        this.let = let;
        this.begin = begin;
        this.end =end;
    }

    public String getLet(){
        return String.valueOf(let);
    }

    public int getBegin(){
        return begin;
    }

    public int getEnd(){
        return end;
    }
}
