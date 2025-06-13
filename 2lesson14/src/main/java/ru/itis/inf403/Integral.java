package ru.itis.inf403;

import java.util.function.Consumer;

public class Integral implements Consumer<Double> {
    private static int N;
    private volatile double integral;
    public static double fucntion(double x) {
        return Math.exp(-x * x/2) * Math.sin(x);
    }

    public static void main(String[] args) throws InterruptedException {
        Integral integralObject = new Integral();
        int countProc = Runtime.getRuntime().availableProcessors();
        N = 10000/countProc; // количество столбцов для каждой подзадачи
        double h = (3. - 1.)/countProc;
        Thread[] threads = new Thread[countProc];
        long start = System.nanoTime();
        for (int i = 0; i < countProc; i++) {
            threads[i] = new Thread(new PartSumCalculate(1+i*h,1+(i+1)*h,integralObject));
            threads[i].start();
        }
        for (int i = 0; i < countProc; i++) {
            threads[i].join();
        }
        long end = System.nanoTime()-start;
        System.out.println(integralObject.integral);
        integralObject.integral = 0;
        long start1 = System.nanoTime();
        for (int i = 0; i < countProc; i++) {
            threads[i] = new Thread(new PartSumCalculate(1+i*h,1+(i+1)*h,integralObject));
            threads[i].start();
            threads[i].join();
        }
        long end1 = System.nanoTime()-start1;
        System.out.println(integralObject.integral);
        System.out.println("Время с использованием многопоточности " + end);
        System.out.println("Время без использования многопоточности "+ end1);


    }
    // Частичная сумма которая будет расчитываться в потоке
    public static double partSum(double a, double b, int N) {
        double h = (b - a)/N;
        double sum = 0;
        for (int i = 0; i < N; i++) {
            sum += h * (fucntion(a+h*i) + fucntion(a + h * (i+1)))/2;
        }
        return sum;
    }

    static class PartSumCalculate implements Runnable {
        private double a;
        private double b;
        private Consumer<Double> consumer;

        public PartSumCalculate(double a, double b, Consumer<Double> consumer) {
            this.a = a;
            this.b = b;
            this.consumer = consumer;
        }

        public void run() {
            double result = partSum(a,b,N);
            consumer.accept(result);
        }
    }

    public synchronized     void accept(Double d) {
        integral+=d;
    }
}
