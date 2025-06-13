package ru.itis.inf403;

import ru.itis.inf403.structures.List403;

import ru.itis.inf403.structures.MapImpl;


public class MainFunction {
    public static void main(String[] args) {
        MapImpl<Integer,String> map = new MapImpl();
        map.put(12,"Ильдар");
        map.put(28,"dss");
        map.put(27,"Sven");
        map.put(29,"Deg");
        List403<Integer> list = map.map((s) -> s.hashCode());
        System.out.println(list);
    }
}
