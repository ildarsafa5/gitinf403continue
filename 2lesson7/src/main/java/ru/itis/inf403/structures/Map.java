package ru.itis.inf403.structures;

import java.util.function.Function;


public interface Map<K,V> extends Iterable<Map.Entry<K,V>> {
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
    Map<K,V> copyOf();

    interface Entry<K,V> {
        void setKey(K key);
        void setValue(V value);
        K getKey();
        V getValue();
    }

    <R> List403<R> map(Function<V,R> function);

}
