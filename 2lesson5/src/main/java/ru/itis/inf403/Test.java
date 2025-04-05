package ru.itis.inf403;

public class Test {
    public static void main(String[] args) {
        MapImpl<Integer,String> map = new MapImpl();
        map.put(12,"Ильдар");
        System.out.println(map.size());
        System.out.println(map.get(12));
    }
}
