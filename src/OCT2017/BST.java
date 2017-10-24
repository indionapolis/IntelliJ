/**
 * Project name: HomeWork
 * Created by pavel on 21.10.2017.
 * Pavel Nikulin BS1-8 ©
 * v0.0
 */
public interface BST<K extends Comparable<K>>{

    /**
     *  returns all entries with key if they exist, null otherwise.
     */
    K find(K key);

    /**
     *  inserts an entry with key.
     */
    void insert(K key);

    /**
     *  removes all entries with key.
     */
    K remove(K key);

    /**
     *  gets the string of the inorder traversal of the tree.
     */
    String traverse();

    /**
     *  prints out the tree.
     */
    String print();

}
