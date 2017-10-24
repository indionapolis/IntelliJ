package TREE;

/**
 * Project name: HomeWork
 * Created by pavel on 10.10.2017.
 * v1.1
 */
public class BTree<K extends Comparable<K>, V>  {

    //properties of tree
    private int height;
    private int order;
    private int size;

    private Node root;



    public BTree(int order) {                 //Initializes empty Btree and set up order
        this.order = order;
        this.root = new Node(0);
    }


    /**
     * Find operation with support recursive method {@code find}
     * @param key - key
     * @return value with key
     */
    public V find(K key) {
        return find(root, key, height);
    }

    private V find(Node node, K key, int lvl) {
        Entry[] children = node.children;


        if (lvl == 0) {

            for (int i = 0; i < node.degree; i++) {                                         //search for entry
                if (eq(key, children[i].key)) return (V) children[i].value;                 //return
            }

        }
        else {

            for (int i = 0; i < node.degree; i++) {                                         //search for right node
                if (i+1 == node.degree || less(key, children[i+1].key)){
                    return (V) find(children[i].next, key, lvl-1);
                }
            }

        }
        return null;
    }


    /**
     * Insertion operation with support recursive method {@code insert}
     * @param key - key
     * @param value - value
     */
    public void insert(K key, V value) {

        Node node = insert(root, key, value, height);                                   //insertion
        size++;

        if (node == null) return;


        Node other = new Node(2);                                               //if we need restructure root after insert

        other.children[0] = new Entry(root.children[0].key, null, root);
        other.children[1] = new Entry(node.children[0].key, null, node);
        root = other;                                                                   //tree grow from down to up (from leaf to root)
        height++;
    }

    private Node insert(Node node, K key, V value, int lvl) {
        int i;
        Entry entry = new Entry(key, value, null);                                //new entry to store


        if (lvl == 0) {
            for (i = 0; i < node.degree; i++) {                                         //search for right position for entry
                if (less(key, node.children[i].key)) break;
            }
        }

        else

        {
            for (i = 0; i < node.degree; i++) {                                         //search for right node
                if ((i+1 == node.degree) || less(key, node.children[i+1].key)) {
                    Node some = insert(node.children[i++].next, key, value, lvl-1); //recursive call
                    if (some == null) return null;
                    entry.key = some.children[0].key;                                   //after restructuring
                    entry.next = some;
                    break;
                }
            }
        }

        for (int j = node.degree; j > i; j--){
            node.children[j] = node.children[j-1];
        }
        node.children[i] = entry;                                                       //put on the right position
        node.degree++;

        if (node.degree < order){
            return null;
        } else {
            return restructuring(node);                                                 //if node is overflow
        }

    }




    /**
     * Restructuring to ensure that the tree stays balanced after every insertion.
     * @param node node with overflow
     * @return new node
     */
    private Node restructuring(Node node) {
        Node other = new Node(order/2);                                         //davide node on 2
        node.degree = order - order/2;
        for (int i = 0; i < order/2; i++){
            other.children[i] = node.children[order - order/2+i];
        }
        return other;
    }




    /**
     * Method for inorder traverse
     * @return a string representation of keys.
     */
    public String traverse() {
        return traverse(root, height);
    }

    private String traverse(Node node, int lvl) {
        String string = "";
        Entry[] children = node.children;

        if (lvl == 0) {
            for (int i = 0; i < node.degree; i++) {
                string += children[i].key + " ";
            }
        }
        else {
            for (int i = 0; i < node.degree; i++) {
                string += traverse(children[i].next, lvl - 1);
            }
        }
        return string;
    }



    //exercise from tutorial
    public void traverseBetween(K start, K end){
        traverseBetween(root, height, start, end);
    }

    public void traverseBetween(Node visitor, int lvl, K start, K end){
        Entry[] children = visitor.children;

        if (lvl == 0) {
            for (int i = 0; i < visitor.degree; i++) {
                //check isBetween
                if ((less(children[i].key, end) || eq(children[i].key, end)) && (less(start, children[i].key) || eq(children[i].key, start))){
                    System.out.println( children[i].key + " " + children[i].value + "\n");
                }
            }
        }
        else {
            for (int i = 0; i < visitor.degree; i++) {
                //check isBetween
                if (less(end, children[0].key)) return;
                if (less(children[visitor.degree-1].key, start)) return;
                traverseBetween(children[i].next, lvl-1, start, end);
            }
        }
    }




    /**
     * support methods to comparing
     * @param k1 first key
     * @param k2 another key
     * @return boolean answer
     */
    private boolean less(Comparable k1, Comparable k2) {
        return k1.compareTo(k2) < 0;
    }

    private boolean eq(Comparable k1, Comparable k2) {
        return k1.compareTo(k2) == 0;
    }


    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public int height() {
        return height;
    }




    private class Node {
        private int degree;                         // number of children
        private Entry[] children;

        private Node(int degree) {
            this.degree = degree;
            this.children = new Entry[order];
        }
    }


    private class Entry<K extends Comparable<K>, V> {
        private K key;
        private V value;
        private Node next;

        public Entry(K key, V value, Node next) {
            this.key  = key;
            this.value  = value;
            this.next = next;
        }
    }
}
