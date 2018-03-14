package IMPORTANT;

/**
 * Project name: HomeWork
 * Created by pavel on 29.08.17.
 */
public class LinkedStack<E> {
    /**
     * Node an item of IMPORTANT.LinkedStack with value E tape
     */
    class Node {
        E value;
        Node next;
    }
    private Node top = null; // pointer to the top
    private int size = 0;

    /**
     * insert an item at the top of the stack
     * @param item
     */
    public void push(E item){
        size++;
        Node node = new Node();
        node.value = item;
        node.next = top;
        top = node;
    }

    /**
     * remove an item from the top of the stack
     * if stack is Empty, return null
     * @return item
     */
    public E pop(){
        if (isEmpty()) return null;
        E item = top.value;
        top = top.next;
        size--;
        return item;
    }

    /**
     * returns the item at the top (without removing it)
     * if stack is Empty, return null
     * @return value
     */
    public E peek(){
        return isEmpty() ? null : top.value;
    }

    /**
     * returns the number of items in the stack
     * @return size
     */
    public int size(){
        return size;
    }

    /**
     * returns whether stacks has no items
     */
    public boolean isEmpty(){
        return (size == 0);
    }

    /**
     * @return all stack in one string
     */
    public String toString(){
        Node sub_top = top;
        String s ="";
        for (int i = 0; i < size; i++) {
            s+=sub_top.value + " ";
            sub_top = sub_top.next;
        }
        return s;
    }

}
