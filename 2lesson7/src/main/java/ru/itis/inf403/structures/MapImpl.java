package ru.itis.inf403;

import ru.itis.inf403.listAndSet.List403;
import ru.itis.inf403.listAndSet.List403Impl;
import ru.itis.inf403.listAndSet.Set400;
import ru.itis.inf403.listAndSet.Set400Impl;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MapImpl<K,V> implements Map<K,V> {
    private Node<K, V>[] array;
    private int size;
    private class Node<K, V> {
        Entry<K, V> value;
        Node<K, V> next;
        public Node(K key, V value) {
            this.value = new EntryImpl<>(key,value);
        }
    }

    class EntryImpl<K, V> implements Entry<K, V> {
        private K key;
        private V value;

        public EntryImpl(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public void setKey(K key) {
            this.key = key;
        }

        @Override
        public void setValue(V value) {
            this.value = value;
        }
    }

    public MapImpl() {
        array = new Node[16];
    }

    @Override
    public void put(K key, V value) {
        int index = Math.abs(key.hashCode() % 16);
        Node<K,V> temp = new Node(key,value);
        if (array[index] == null) {
            array[index] = temp;
        } else {
            Node<K,V> current = array[index];
            while(current.next != null) {
                if (current.value.getKey().equals(key)) {
                    current.value.setKey(key);
                    current.value.setValue(value);
                    return;
                }
                current = current.next;
            }
            current.next = temp;
        }
        size++;
    }

    @Override
    public V get(K key) {
        int index = Math.abs(key.hashCode() % 16);
        if (array[index] == null) {
            return null;
        }
        Node<K,V> current = array[index];
        if (current.next == null) {
            return current.value.getValue();
        } else {
            while(true) {
                if (current.value.getKey().equals(key)) {
                    return current.value.getValue();
                }
                if (current.next == null) {
                    break;
                }
                current = current.next;
            }
        }
        return null;
    }

    @Override
    public Set400<K> keySet() {
        Set400<K> set = new Set400Impl();
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                if (array[i].next == null) {
                    set.add(array[i].value.getKey());
                } else {
                    Node<K,V> current = array[i];
                    while (true) {
                        set.add(current.value.getKey());
                        if (current.next == null) {
                            break;
                        }
                        current = current.next;
                    }
                }
            }
        }
        return set;
    }

    @Override
    public List403<V> values() {
        List403<V> list = new List403Impl();
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                Node<K,V> current = array[i];
                while (true) {
                    list.add(current.value.getValue());
                    if (current.next == null) {
                        break;
                    }
                    current = current.next;
                }
            }
        }
        return list;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean containsKeys(K key) {
        Set400<K> set = keySet();
        return set.contains(key);
    }

    @Override
    public boolean isEmpty() {
        for (int i = 0; i < array.length; i++) {
            if (array[i]!=null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean containsValue(V value) {
        List403<V> values = values();
        for (int i = 0; i < values.size(); i++) {
            if (values.indexOf(i).equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V remove(K key) {
        if (!containsKeys(key)) {
            return null;
        }
        int index = Math.abs(key.hashCode()%16);
        Node<K,V> current = array[index];
        if (array[index].next == null) {
            array[index] = null;
            size--;
            return current.value.getValue();
        } else {
            while(true) {
                if (current.next.value.getKey().equals(key)) {
                    Node<K,V> next = current.next;
                    current.next = current.next.next;
                    size--;
                    return next.value.getValue();
                }
                current = current.next;
            }
        }
    }

    @Override
    public void clear() {
        array = new Node[16];
    }

    private int findNextIndex(int index) {
        for (int i = index+1; i < array.length; i++) {
            if (array[i]!=null) {
                return i;
            }
        }
        return -1;
    }

    private Node<K,V> findNextNode(Node<K,V> first, int index) {
        Node<K,V> current = first;
        for (int i = 1; i < index; i++) {
            current = current.next;
        }
        return current.next;
    }

    public Iterator<Entry<K,V>> iterator() {
        return new MapIterator();
    }

    class MapIterator implements Iterator<Entry<K,V>> {
        private int curIndex=0;
        private K[] array1 = keySet().getAll((K[]) new Object[0]);
        @Override
        public boolean hasNext() {
            return curIndex < array1.length;
        }
        @Override
        public Entry<K,V> next() {
            if (curIndex == array1.length) {
                throw new NoSuchElementException();
            }
            K key = array1[curIndex++];
            return new EntryImpl(key,get(key));
        }
//        private Node<K,V> currentNode = array[0];
//        public Entry<K, V> next() {
//            if (currentNode == null) {
//                while (currentNode == null) {
//                    currentNode = array[++arrayStep];
//                }
//            }
//            head = currentNode.value;
//            currentNode = currentNode.next;
//            step++;
//            return head;
//        }
    }
}