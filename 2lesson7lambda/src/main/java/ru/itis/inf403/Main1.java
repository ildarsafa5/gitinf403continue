package ru.itis.inf403;

public class Main1 {
    public static void main(String[] args) {
        String s = new String("fsfs");
        List403<String> a = new List403Impl<>();
        List403<String> b = new List403Impl<>();
        a.add(s);
        b.add(s);
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());

    }
}
