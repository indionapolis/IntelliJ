package HASH;

/**
 * Project name: HomeWork
 * Created by pavel on 09.09.17.
 */
public interface Dictionary<K,V> {

    /**
     * @return size of Dictionary
     */
    public int volume();

    /**
     * @return empty or not
     */
    public boolean isEmpty();

    /**
     * @return all keys from Dictionary
     */
    public String[] keys();

    /**
     * @return all values from Dictionary
     */
    public String[] elements();

    /**
     * put element in Dictionary
     * @param key
     * @param value
     */
    public void put(K key, V value);

    /**
     * return key'st element
     * @param key
     * @return element
     */
    public V get(K key);

    /**
     * return key'st element and delete him
     * @param key
     * @return element
     */
    public V remove(K key);


}
