package solver;

public class MinHeap<E extends Comparable<E>> extends BinaryHeap<E> {

    public MinHeap(){
        super();
    }

    @Override
    public boolean insert(E e) {
        return super.insert(e);
    }

    @Override
    protected void bubbleUp(int index){
        if(hasParent(index) && array()[index].compareTo(array()[parentIndex(index)])<0){
            swap(index, parentIndex(index));
            bubbleUp(parentIndex(index));
        }
    }

    public E min(){
        return top();
    }

    public E extractMin(){
        return extract();
    }

    @Override
    protected void sinkDown(int index){
        if(!hasLeftChild(index)){
            return;
        }
        if(!hasRightChild(index) || array(leftIndex(index)).compareTo(array(rightIndex(index)))<0){
            if(array(leftIndex(index)).compareTo(array(index))<0){
                swap(index, leftIndex(index));
                sinkDown(leftIndex(index));
            }
            return;
        }
        if(array(rightIndex(index)).compareTo(array(index))<0){
            swap(index, rightIndex(index));
            sinkDown(rightIndex(index));
        }
    }


    public static void main(String[] args) {
        MinHeap<Integer> heap = new MinHeap<>();
        for (int i = 0; i < 20; i++) {
            heap.insert((int)(Math.random()*50+1));
        }
        heap.printArr();
        while(heap.size()>0){
            System.out.print(heap.extractMin()+": ");
            heap.printArr();
        }
    }
}
