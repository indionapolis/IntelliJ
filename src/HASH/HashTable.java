package HASH;

/**
 * Project name: HomeWork
 * Created by pavel on 25.09.17.
 */
public class HashTable<K,V> {
    private Node[] nodes;
    private int size;
    private int capacity;

    static final int DEF_CAPACITY = 128;

    public HashTable(){
        this(DEF_CAPACITY);
    }

    public HashTable(int capacity){
        if (capacity < DEF_CAPACITY) capacity = DEF_CAPACITY;
        this.capacity = capacity;
        this.nodes = new Node[this.capacity];
    }



    /**
     * Maps the specified key to the specified
     * value in this hash table.
     * @param   key     the hash table key
     * @param   value   the value
     */
    public void put(K key, V value){
        if (this.size > (this.capacity*4)/5) resize();                                                     //if table is full more then 80%

        int i;
        for (i = 0; ((this.nodes[(hash(key) + (i * i)) % this.capacity] != null) &&
                (!this.nodes[(hash(key) + (i * i)) % this.capacity].delete)); i++) {                       //until we meet an empty or similar or delited node
            if (this.nodes[(hash(key) + (i * i)) % this.capacity].key.equals(key)) {
                this.nodes[(hash(key) + (i * i)) % this.capacity].value = value;                           //if key already exist
                return;
            }
        }
        this.nodes[(hash(key) + (i * i)) % this.capacity] = new Node(hash(key), key, value);               //put new node
        this.nodes[hash(key)].SequenceLength++;
        size++;
    }


    /**
     * Returns the value to which the specified key is mapped,
     * or null if this map contains no mapping for the key
     * @param   key the key whose associated value is to be returned
     * @return he value to which the specified key is mapped, or
     *         null if this map contains no mapping for the key
     */
    public V get(K key){

        int i;
        for (i = 0; this.nodes[ (hash(key) + (i * i)) % this.capacity] != null; i++) {  //until we meet node that empty
            if (this.nodes[(hash(key) + (i * i)) % this.capacity].key.equals(key)) {
                return (V) this.nodes[(hash(key) + (i * i)) % this.capacity].value;     //if we find, return value

            }
        }
        return null;                                                                    //if we do not find, return null
    }


    /**
     * Removes the key (and its corresponding value) from this
     * hashtable. This method does nothing if the key is not in the hashtable.
     * @param key the key that needs to be removed
     * @return the value to which the key had been mapped in this hashtable,
     *          or null if the key did not have a mapping
     */
    public V remove(K key){
        if (this.nodes[hash(key)] != null) this.nodes[hash(key)].SequenceLength--;

        int i;
        for (i = 0; this.nodes[(hash(key) + (i * i)) % this.capacity] != null; i++) {    //until we meet node that empty
            if (this.nodes[(hash(key) + (i * i)) % this.capacity].key.equals(key)) {
                this.nodes[(hash(key) + (i * i)) % this.capacity].delete = true;         //for search (we can not make pointer = null)
                this.nodes[hash(key)].SequenceLength--;
                size--;
                return (V) this.nodes[(hash(key) + (i * i)) % this.capacity].value;

            }
        }
        return null;                                                                     //if we do not find, return null
    }


    /**
     * if table is full, make capacity in 2 times more
     */
    private void resize(){
        HashTable moreNodes = new HashTable(this.capacity * 2);

        for (int i = 0; i < this.capacity; i++) {
            if (nodes[i] != null) moreNodes.put(nodes[i].key, nodes[i].value);
        }

        this.capacity = this.capacity * 2;
        this.nodes = moreNodes.nodes;
    }


    /**
     * hash function
     * @param key
     * @return hash of key
     */
    private int hash(K key){
        String s = String.valueOf(key).toLowerCase();                    //make key type string
        int code = 0;

        for (int i = 0, j = 2; i < s.length(); i++, j++) {               //calculate a number depend on key sting
            if (i == 0) code += 2 * (((int) s.charAt(i)) - 97);          //in alphabet order 1st element more then any previous
            if (i != 0) code += (((int) s.charAt(i)) - 97) / (j*j);      //in alphabet order not 1st element less then any previous
        }

        return Math.abs(code) % this.capacity;                           //get result
    }


    /**
     * @return size
     */
    public int size(){
        return this.size;
    }


    public int capacity(){
        return this.capacity;
    }

    /**
     * @return is empty
     */
    public boolean isEmpty(){
        return (this.size == 0);
    }


    /**
     *
     * @return the length of the longest probing sequence
     */
    public int getMaxProbabingSequenceLength(){
        int maxSequenceProbing = 0;

        for (int i = 0; i < size; i++) {
            if (nodes[i] == null) continue;
            if (nodes[i].SequenceLength > maxSequenceProbing) maxSequenceProbing = nodes[i].SequenceLength;
        }

        return maxSequenceProbing;
    }

    public String[] entrySet(){
        String[] entry = new String[this.size];
        for (int i = 0, j = 0; j < size; i++) {
            if (nodes[i] != null && !nodes[i].delete){
                entry[j] = "<\"" + String.valueOf(nodes[i].key) + "\", " + String.valueOf(nodes[i].value) + ">";
                j++;
            }
        }
        return entry;
    }



    public String[] keySet(){
        String[] keys = new String[this.size];
        for (int i = 0, j = 0; j < size; i++) {
            if (nodes[i] != null && !nodes[i].delete){
                keys[j] = String.valueOf(nodes[i].key);
                j++;
            }
        }
        return keys;
    }



    public String[] valueSet(){
        String[] values = new String[this.size];
        for (int i = 0, j = 0; j < size; i++) {
            if (nodes[i] != null && !nodes[i].delete){
                values[j] = String.valueOf(nodes[i].value);
                j++;
            }
        }
        return values;
    }



    static class Node<K,V> {
        int SequenceLength = 1;
        final int hash;
        final K key;
        V value;
        boolean delete;

        Node(int hash, K key, V value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.delete = false;
        }
    }
}
