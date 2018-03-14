package OCT2017;

/**
 * Project name: HomeWork
 * Created by pavel on 27.10.2017.
 * Pavel Nikulin BS1-8
 * Copyright ©
 * v0.0
 */
public interface BT<K extends Comparable<K>, V> {

    V find(K key);

    void insert(K key, V value);

    String traverse();
}
