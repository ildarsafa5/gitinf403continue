package ru.itis.inf403.listAndSet;

public interface Set400<T> {
    int size();
    boolean contains(T element);
    void add(T element);
    T[] getAll(T[] c);
    T remove(T element);

}
