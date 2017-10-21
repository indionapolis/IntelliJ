package TREE;

/**
 * Project name: HomeWork
 * Created by pavel on 21.10.2017.
 */
public interface BST<K extends Comparable<K>, V>{

    /**
     *  returns all entries with key if they exist, null otherwise.
     */
    V find(K key);

    /**
     *  inserts an entry with key key.
     */
    void insert(K key, V value);

    /**
     *  removes all entries with key k.
     */
    V remove(K key);

    /**
     *  gets the string of the inorder traversal of the tree.
     */
    String traverse();

    /**
     *  prints out the tree.
     */
    void print();

}
