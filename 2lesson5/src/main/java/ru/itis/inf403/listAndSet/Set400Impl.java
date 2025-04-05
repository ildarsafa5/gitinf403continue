package ru.itis.inf403.listAndSet;

import java.lang.reflect.Array;

public class Set400Impl<T> implements Set400<T> {
    private int size;
    private T[] array;

    public Set400Impl() {
        size=0;
        array = (T[]) new Object[10];
    }

    public void add(T elem) {
        if (contains(elem)) {
            return;
        } else if (size == array.length) {
            grow();
        }
        array[size++] = elem;
    }

    private void grow() {
        T[] newarray = (T[]) new Object[(int)(size*1.5)];
        for (int i = 0; i < size; i++) {
            newarray[i] = array[i];
        }
        array = newarray;
    }

    public boolean contains(T elem) {
        for (int i = 0; i < size; i++) {
            if (elem.equals(array[i])){
                return true;
            }
        }
        return false;
    }

    public T remove(T elem) throws NotElemException{
        if (!contains(elem)) {
            throw new NotElemException();
        }
        int cnt = 0;
        for (int i = 0; i < size; i++) {
            if (array[i].equals(elem)){
                cnt=i;
            }
        }
        for (int i = cnt; i < size-1; i++) {
            array[i]=array[i+1];
        }
        array[size-1]=null;
        size--;
        return elem;
    }

    public T[] getAll(T[] c) {
        T[] newarray = (T[]) Array.newInstance(c.getClass().componentType(),size);
        for (int i = 0; i < size; i++) {
            newarray[i] = (T) array[i];
        }
        return newarray;
    }

    public int size() {
        return size;
    }
}
