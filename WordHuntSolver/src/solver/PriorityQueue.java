package solver;

public class PriorityQueue<E extends Comparable<E>> implements Queue<E>{

    private BinaryHeap<E> heap;
    private int size;


    public PriorityQueue(Priority p){
        if(p == Priority.MIN){
            heap = new MinHeap<>();
        }
        else if(p == Priority.MAX){
            heap = new MaxHeap<>();
        }
        size = 0;
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public void enqueue(E e) {
        if(heap.insert(e)){
            size++;
        }
    }

    @Override
    public E first() {
        return heap.top();
    }

    @Override
    public E dequeue() {
        size--;
        return heap.extract();
    }


    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(Priority.MIN);
        for(int i=0; i<4; i++){
            queue.enqueue(i+1);
        }
        while(!queue.isEmpty()){
            System.out.print("Removed "+queue.dequeue()+" Current heap array: ");
            queue.heap.printArr();
        }
    }
}
