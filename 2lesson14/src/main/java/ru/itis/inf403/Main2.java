package ru.itis.inf403;

public class Main2 {
    public static void main(String[] args) {
        Thread task1 = new Thread(new MyTask2(1));
        Thread task2 = new Thread(new MyTask2(2));
        Thread task3 = new Thread(new MyTask2(3));

        task1.start();
        task2.start();
        task3.start();

    }

    static class MyTask2 implements Runnable {
        private int index;

        public MyTask2(int index) {
            this.index = index;
        }
        public void run() {
            System.out.println("Привет из параллельного потока " + index);
        }
    }


}
