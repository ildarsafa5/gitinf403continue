package ru.itis.inf403;


import ru.itis.inf403.listAndSet.List403;
import ru.itis.inf403.listAndSet.Set400;

import java.util.Iterator;


public interface Map<K,V> {
    void put(K key, V value);
    V get(K key);
    Set400<K> keySet();
    List403<V> values();
    int size();
    boolean containsKeys(K key);
    boolean isEmpty();
    boolean containsValue(V value);
    V remove(K key);
    void clear();

    interface Entry<K,V> {
        void setKey(K key);
        void setValue(V value);
        K getKey();
        V getValue();
    }

}
