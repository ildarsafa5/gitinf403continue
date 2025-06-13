package ru.itis.inf403;

import ru.itis.inf403.structures.List403;
import ru.itis.inf403.structures.List403Impl;
import ru.itis.inf403.structures.Map;


import java.util.HashSet;

public class Graph {
    private Map<String, HashSet<String>> graph;

    public Graph(Map<String,HashSet<String>> graph) {
        this.graph = graph;
    }

    public List403<String> topSort() {
        Map<String,HashSet<String>> current = graph.copyOf();
        List403Impl<String> posledovat = new List403Impl();
        while(posledovat.size()!=graph.size()) {
            Map.Entry<String,HashSet<String>> cur = null;
            for(Map.Entry<String,HashSet<String>> n: current) {
                if (n.getValue().isEmpty()) {
                    cur = n;
                    break;
                }
            }
            for(Map.Entry<String,HashSet<String>> n : current) {
                n.getValue().remove(cur.getKey());
            }
            posledovat.add(cur.getKey());
            current.remove(cur.getKey());
        }
        return posledovat;
    }

}
