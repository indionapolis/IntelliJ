package SEP2017;

/**
 * Project name: HomeWork
 * Created by pavel on 29.08.17.
 */
public class LinkedQueue<E> {
    /**
     * Node an item of LinkedQueue with value E tape
     */
    class Node {
        E value;
        Node next;
    }
    private Node first = null; // pointer to the first
    private Node last = null;  // pointer to the last
    private int size = 0;

    /**
     * Add an element to the back
     * @param item
     */
    public void add(E item){
        Node node = new Node();
        node.value = item;
        if (isEmpty()) first = node;
        else last.next = node;
        last = node;
        size++;
    }

    /**
     * Remove the front element
     * @return
     */
    public E remove(){
        if (isEmpty()) return null;
        E item = first.value;
        first = first.next;
        size--;
        return item;
    }

    /**
     * returns the item at the top (without removing it)
     * if queue is Empty, return null
     * @return value
     */
    public E peek(){
        return isEmpty() ? null : first.value;
    }

    /**
     * returns the number of items in the queue
     * @return size
     */
    public int size(){
        return size;
    }

    /**
     * returns whether queue has no items
     */
    public boolean isEmpty(){
        return (size == 0);
    }

    /**
     * @return all queue in one string
     */
    public String toString(){
        Node sub_first = first;
        String s ="";
        for (int i = 0; i < size; i++) {
            s+=sub_first.value + " ";
            sub_first = sub_first.next;
        }
        return s;
    }
}
