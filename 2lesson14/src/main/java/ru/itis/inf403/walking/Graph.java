package ru.itis.inf403.walking;

public class Graph {
    public static final int speed = 1000;
    public static final int[][] matrix = {
            { 0, 5, 11, 7, 0,  0,  0, 0, 0, 0, 0},
            { 5, 0,  0, 4, 0,  3,  7, 0, 0, 0, 0},
            {11, 0,  0, 0, 8,  0,  0, 0, 0, 0, 0},
            { 7, 4,  0, 0, 5,  0,  0, 9, 0, 0, 0},
            { 0, 0,  8, 5, 0,  0,  0, 6, 0, 7, 0},
            { 0, 3,  0, 0, 0,  0,  0,12, 8, 0, 0},
            { 0, 7,  0, 0, 0,  0,  0, 0,11, 0, 0},
            { 0, 0,  0, 9, 6, 12,  0, 0, 0, 0, 9},
            { 0, 0,  0, 0, 0,  8, 11, 0, 0, 0, 2},
            { 0, 0,  0, 0, 7,  0,  0, 0, 0, 0, 3},
            { 0, 0,  0, 0, 0,  0,  0, 9, 2, 3, 0},
    };


    private static boolean[] comand1 = new boolean[11];
    private static boolean[] comand2 = new boolean[11];

    public static synchronized boolean getComand1(int i) {
        return comand1[i];
    }
    public static synchronized boolean getComand2(int i) {
        return comand2[i];
    }

    public static synchronized void setComand1(int n) {
        comand1[n] = true;
    }

    public static synchronized void setComand2(int n) {
        comand2[n] = true;
    }

//    public static synchronized boolean readAndWriteI(int n) {
//        if (!comand1[n]) {
//            comand1[n] = true;
//        } else {
//            return false;
//        }
//    }
}
