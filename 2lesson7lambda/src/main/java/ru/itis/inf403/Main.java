package ru.itis.inf403;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        List403<String> list = new List403Impl();
        list.add("Роман");
        list.add("Азалия");
        list.add("Камиль");

        Set<Integer> set = list.map(s -> s.length());

    }
}
