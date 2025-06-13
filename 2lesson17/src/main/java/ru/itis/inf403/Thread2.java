package ru.itis.inf403;


public class Thread2 implements Runnable{
    private CallBack callBack;

    public Thread2(CallBack callBack) {
        this.callBack = callBack;
    }

    public void run() {
        try {
            Thread.sleep(10000);
            callBack.call();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }



}
