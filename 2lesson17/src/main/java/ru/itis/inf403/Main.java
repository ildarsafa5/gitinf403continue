package ru.itis.inf403;

public class Main implements CallBack {

    public void work() {
        Thread2 thread2 = new Thread2(this);
        new Thread(thread2).start();
        while (true) {
            System.out.println("WORK");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void call() {
        System.out.println("File read is completed");
    }

    public static void main(String[] args) {
        new Main().work();
    }
}
