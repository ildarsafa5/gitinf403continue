package ru.itis.inf403.graph;

import java.util.HashSet;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Node first = new Node(2,1);
        Node second = new Node(5,2);
        HashSet<Node> forSecond = new HashSet();
        forSecond.add(first);
        second.setPredki(forSecond);
        Node third = new Node(6,3);
        HashSet<Node> forThird = new HashSet();
        forThird.add(first);
        third.setPredki(forThird);
        Node fourth = new Node(4,4);
        HashSet<Node> forFourth = new HashSet();
        forFourth.add(second);
        forFourth.add(third);
        fourth.setPredki(forFourth);
        Node fifth = new Node(8,5);
        HashSet<Node> forFifth = new HashSet();
        forFifth.add(second);
        fifth.setPredki(forFifth);
        Node seventh = new Node(11,7);
        HashSet<Node> forSeventh = new HashSet();
        forSeventh.add(third);
        seventh.setPredki(forSeventh);
        Node sixth = new Node(7,6);
        HashSet<Node> forSixth = new HashSet();
        forSixth.add(fifth);
        sixth.setPredki(forSixth);
        Node eight = new Node(5,8);
        HashSet<Node> forEight = new HashSet();
        forEight.add(sixth);
        forEight.add(seventh);
        eight.setPredki(forEight);
        Node ninth = new Node(3,9);
        HashSet<Node> forNinth = new HashSet();
        forNinth.add(eight);
        forNinth.add(seventh);
        ninth.setPredki(forNinth);
        Node tenth = new Node(4,10);
        HashSet<Node> forTenth = new HashSet();
        forTenth.add(ninth);
        tenth.setPredki(forTenth);
        Graph graph = new Graph();
        graph.add(first);
        graph.add(second);
        graph.add(tenth);
        graph.add(third);
        graph.add(fourth);
        graph.add(fifth);
        graph.add(sixth);
        graph.add(seventh);
        graph.add(eight);
        graph.add(ninth);
        long start = System.nanoTime();
        graph.traversal();
        System.out.println((System.nanoTime()-start)/1000000000 + "секунд");
    }
}
