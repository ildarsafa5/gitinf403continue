package ru.itis.inf403;

import ru.itis.inf403.listAndSet.Set400;
import ru.itis.inf403.listAndSet.Set400Impl;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        MapImpl<Integer,String> map = new MapImpl();
        map.put(12,"Ильдар");
        map.put(28,"dss");
        map.put(27,"Sven");
        map.put(29,"Deg");
        System.out.println(map.size());
        System.out.println(map.isEmpty());
        System.out.println(map.get(28));
        Integer[] array = map.keySet().getAll(new Integer[0]);
        System.out.println(Arrays.toString(array));
        System.out.println(map.values());
        System.out.println(map.containsValue("Ильдар"));
        System.out.println(map.containsKeys(12));
        System.out.println(map.size());
        System.out.println("------------------");
        for(String n : map) {
            System.out.println(n);
        }
    }
}
