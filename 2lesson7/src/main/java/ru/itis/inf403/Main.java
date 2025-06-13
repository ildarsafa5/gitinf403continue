package ru.itis.inf403;

import ru.itis.inf403.structures.Map;
import ru.itis.inf403.structures.MapImpl;

import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        Map map = new MapImpl();
        HashSet<String> a = new HashSet<>();
        a.add("d");
        a.add("c");
        map.put("a",a);
        HashSet<String> b = new HashSet<>();
        b.add("a");
        b.add("g");
        b.add("f");
        b.add("c");
        map.put("b",b);
        map.put("c",new HashSet<String>());
        map.put("d",new HashSet<String>());
        HashSet<String> f = new HashSet<>();
        f.add("d");
        map.put("f",f);
        HashSet<String> g = new HashSet<>();
        g.add("d");
        g.add("c");
        g.add("f");
        map.put("g",g);
        Graph graph = new Graph(map);
        System.out.println(graph.topSort());



    }
}
