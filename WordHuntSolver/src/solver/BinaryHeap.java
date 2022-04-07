package solver;

public abstract class BinaryHeap <E extends Comparable<E>>{

    private E[] array;
    private int size;
    protected final int START = 1;

    public BinaryHeap(){
        array = (E[]) new Comparable[65];
        size=0;
    }

    public boolean insert(E e){
        if(size>=array.length){
            return false;
        }
        array[START+size] = e;
        bubbleUp(START+size);
        size++;
        return true;
    }

    protected abstract void bubbleUp(int index);

    public E top(){
        return array[START];
    }

    public E extract(){
        E answer = top();
        swap(START, size);
        size--;
        sinkDown(START);
        return answer;
    }

    protected abstract void sinkDown(int index);

    public int size(){
        return size;
    }

    protected E[] array(){
        return array;
    }

    protected E array(int index){
        return array[index];
    }

    protected void swap(int x, int y){
        E temp = array[x];
        array[x] = array[y];
        array[y] = temp;
    }

    protected boolean hasLeftChild(int index){
        return leftIndex(index) <= size;
    }

    protected boolean hasRightChild(int index){
        return rightIndex(index) <= size;
    }

    protected boolean hasParent(int index){
        return parentIndex(index)>=START;
    }

    protected int leftIndex(int rootIndex){
        return rootIndex*2;
    }

    protected int rightIndex(int rootIndex){
        return rootIndex*2 +1;
    }

    protected int parentIndex(int childIndex){
        return childIndex/2;
    }

    public void printArr(){
        for(int i=1; i<=size; i++){
            System.out.print(array[i].toString());
            if(i+1<array.length && array[i+1]!=null){
                System.out.print(", ");
            }
        }
        System.out.println();
    }
}
