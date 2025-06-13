package ru.itis.inf403.graph;

import java.util.HashSet;
import java.util.Set;

public class Node extends Thread {
    private int name;
    private int time;
    private Set<Node> predki;

    public Node() {}

    public Node(int time, int name) {
        this.time = time;
        this.name = name;
        predki = new HashSet<>();
    }


    public int getTime() {
        return time;
    }

    public Set<Node> getPredki() {
        return predki;
    }

    public void setPredki(Set<Node> predki) {
        this.predki = predki;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void run() {
        try {
            for (Node n: predki) {
                n.join();
            }
            System.out.println("Вошли в вершину " + name);
            Thread.sleep(time*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
