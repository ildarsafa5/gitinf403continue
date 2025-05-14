package ru.itis.inf403;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {

        List<Person1> people = new ArrayList<>();
        initPersonList(people);
        //1 найти младшего
        Optional<Person1> person = people
                .stream()
                .min((a, b) -> a.getAge()-b.getAge());
        System.out.println(person.orElse(new Person1("Олег",18)));
        //2 вывести только имена заглавными буквами
        people
                .stream()
                .map(a -> a.getName().toUpperCase())
                .sorted()
                .toList().forEach(s -> System.out.println(s));
    }

    public static void initPersonList(List<Person1> person1s) {
        person1s.add(new Person1("Пушкин", 30));
        person1s.add(new Person1("Гоголь", 65));
        person1s.add(new Person1("Маяковский", 42));
        person1s.add(new Person1("Твэн", 20));
        person1s.add(new Person1("Тургенев", 28));
    }
}
