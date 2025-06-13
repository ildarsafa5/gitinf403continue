package ru.itis.inf403;

import ru.itis.inf403.structures.Map;
import ru.itis.inf403.structures.MapImpl;

public class Main1 {
    public static void main(String[] args) {
        Map<Integer,String> a = new MapImpl<>();
        a.put(17,"22");
        System.out.println(a.get(33));

    }
}
