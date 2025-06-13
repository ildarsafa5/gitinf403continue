package ru.itis.inf403;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Menu2 implements Iterable<String>{
    private String dish1;
    private String dish2;
    private String dish3;
    public Menu2() {
        dish1 = "Картошка";
        dish2 = "Мясо";
        dish3 = "Макароны";
    }

    public Iterator<String> iterator() {
        return new Menu2Iterator();
    }

    class Menu2Iterator implements Iterator<String> {
        private int currentIndex = 0;
        public boolean hasNext() {
            return currentIndex<3;
        }
        public String next() {
            if (currentIndex == 3) {
                throw new NoSuchElementException();
            }
            return switch (currentIndex++) {
                case 0 -> dish1;
                case 1 -> dish2;
                case 2 -> dish3;
                default -> throw new NoSuchElementException();
            };
        }
    }
}
