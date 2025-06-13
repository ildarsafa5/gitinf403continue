package ru.itis.inf403.graph;

import java.util.HashSet;
import java.util.Set;

public class Graph {
    private Set<Node> graph;

    public Graph(Set<Node> graph) {
        this.graph = graph;
    }

    public Graph() {
        graph = new HashSet<>();
    }

    public void setGraph(Set<Node> graph) {
        this.graph = graph;
    }

    public Set<Node> getGraph() {
        return graph;
    }

    public void traversal() throws InterruptedException {
        for (Node n: graph) {
            n.start();
        }

        for (Node n: graph) {
            n.join();
        }
    }

    public void add(Node vertex) {
        graph.add(vertex);
    }
}
