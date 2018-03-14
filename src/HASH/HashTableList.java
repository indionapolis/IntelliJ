package HASH;

/**
 * Project name: HomeWork
 * Created by pavel on 05.09.17.
 */
public class HashTableList<K,V>  implements Dictionary<K,V>{
    private int volume = 0;

    private int capacity;

    private final static int DEFAULT_INITIAL_CAPACITY = 128;

    private Node[] arr;

    /**
     * Node for items
     * @param <K> - key
     * @param <V> - value
     */
    static class Node<K,V> {
        final int hash;
        K key;
        V value;
        Node<K,V> next;
        Node<K,V> previous;

        Node(int hash, K key, V value,Node<K,V> previous, Node<K,V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.previous = previous;
            this.next = next;
        }

        public final String toString() { return key + "=" + value; }
    }


    /**
     * @return volume of HashTableList
     */
    @Override
    public int volume() {
        return this.volume;
    }


    /**
     * @return is HashTableList empty
     */
    @Override
    public boolean isEmpty() {
        return (volume() == 0);
    }

    @Override
    public String[] keys() {
        return new String[volume];
    }

    @Override
    public String[] elements() {
        return new String[volume];
    }


    /**
     *
     * @param key
     * @param value
     */
    @Override
    public void put(K key, V value) {
        int hash = hash(key)%capacity;

        for (Node link = this.arr[hash]; link != null; link = link.next){
            if (link.key.equals(key)) {
                link.value = value; //if key already exist, change value and exit
                return;
            }
        }

        if (this.arr[hash] == null){ //if hash cell do not exist
            this.arr[hash] = new Node(hash, key, value, null, null);
        }
        else {                       //if hash cell already exist but key do not exist
            this.arr[hash].previous = new Node(hash, key, value , null, this.arr[hash]);
            this.arr[hash] = this.arr[hash].previous;
        }

        volume++;
    }


    /**
     *
     * @param key
     * @return
     */
    @Override
    public V get(K key) {
        int hash = hash(key)%capacity;
        for (Node link = this.arr[hash]; link != null; link = link.next){
            if (link.key.equals(key)) return (V) link.value;
        }
        return null;
    }


    /**
     *
     * @param key
     * @return
     */
    @Override
    public V remove(K key) {
        int hash = hash(key)%capacity;
        V value = null;
        for (Node link = this.arr[hash]; link != null; link = link.next){
            if (link.key.equals(key)){
                value = (V) link.value;
                if (link.previous != null) link.previous.next = link.next;
                if (link.next != null) link.next.previous = link.previous;
                volume--;
                break;
            }
        }
        return value;
    }


    /**
     * create HashTableList with default capacity
     */
    public HashTableList() {
        this(DEFAULT_INITIAL_CAPACITY);
    }


    /**
     * create HashTableList with set up capacity
     * @param size - set up capacity
     */
    public HashTableList(int size){
        this.capacity = size;
        this.arr = new Node[capacity];
    }


    /**
     *
     * @param key
     * @return
     */
    static int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }


}
