package NOV2017;

import AUG2017.New2;
import IMPORTANT.LinkedQueue;

import java.util.Arrays;

/**
 * Project name: HomeWork
 * Created by pavel on 19.10.2017.
 * v1.0
 */

public class BinaryHeap<K extends Comparable<K>, V> {
    private static final int DEF_CAPACITY = 100;
    private Node[] array;
    private int size;



    /**
     * Constructs a new IMPORTANT.BinaryHeap.
     */
    public BinaryHeap() {
        this(DEF_CAPACITY);
    }

    public BinaryHeap(int capacity){
        array =  new Node[capacity];
        size = 0;
    }



    /**
     * Add a value to the heap.
     * @param key
     * @param value
     */
    public void insert(int key, Object value) {
        if (isFull()) {
            resize();
        }

        array[++size] = new Node(key, value);

        goToRightPlace();
    }



    /**
     * Returns (but does not remove) the minimum element in the heap.
     */
    public V min() {
        if (isEmpty()){
            return null;
        }else {
            return (V) array[1].value;
        }
    }



    /**
     * Removes and returns the minimum element in the heap.
     */
    public V removeMin() {
        // what do want return?
        V result = min();

        // get rid of the last leaf/decrement
        array[1] = array[size];
        array[size--] = null;

        correctPlace();

        return result;
    }




    /**
     * Performs operation to place the element that is at the
     * root of the heap in its correct place so that the heap maintains the
     * min-heap order property.
     */
    protected void correctPlace() {
        int index = 1;

        while (hasLeftChild(index)) {
            // which of my children is smaller?
            int smallerChild = leftIndex(index);

            // bubble with the smaller child, if I have a smaller child
            if (hasRightChild(index)
                    && array[leftIndex(index)].key.compareTo(array[rightIndex(index)].key) > 0) {
                smallerChild = rightIndex(index);
            }

            if (array[index].key.compareTo(array[smallerChild].key) > 0) {
                swap(index, smallerChild);
            } else {
                // otherwise, get outta here!
                break;
            }

            // make sure to update loop counter/index of where last el is put
            index = smallerChild;
        }
    }


    /**
     * Performs operation to place a newly inserted element
     * (i.e. the element that is at the size index) in its correct place so
     * that the heap maintains the min-heap order property.
     */
    protected void goToRightPlace() {
        int id = this.size;

        while (hasParent(id)
                && (parent(id).key.compareTo(array[id].key) > 0)) {
            // parent/child are out of order; swap them
            swap(id, parentIndex(id));
            id = parentIndex(id);
        }
    }


    public void upData(){
        LinkedQueue queue = new LinkedQueue();
        while (!isEmpty()){
            queue.add(removeMin());
        }
        while (!queue.isEmpty()){
            insert(((NOV2017.Graph.Vertex)queue.peek()).key, queue.remove());
        }
    }



    protected boolean hasParent(int i) {
        return i > 1;
    }

    protected Node parent(int i) {
        return array[parentIndex(i)];
    }

    protected int parentIndex(int i) {
        return i / 2;
    }


    protected int leftIndex(int i) {
        return i * 2;
    }

    protected int rightIndex(int i) {
        return i * 2 + 1;
    }


    protected boolean hasLeftChild(int i) {
        return leftIndex(i) <= size;
    }

    protected boolean hasRightChild(int i) {
        return rightIndex(i) <= size;
    }


    public boolean isEmpty() {
        return size == 0;
    }

    private boolean isFull(){
        return size >= array.length - 1;
    }


    protected void resize() {
        array = Arrays.copyOf(array, array.length * 2);
    }


    protected void swap(int index1, int index2) {
        Node tmp = array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;
    }

    private class Node<K extends Comparable<K>, V>{
        private Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
        K key;
        V value;
    }
}
