package ru.itis.inf403;

public class Main {
    public static void main(String[] args) throws IllegalAccessException{
        JSONMapper mapper = new JSONMapper();
        Student student = new Student("Сафиуллин","Ильдар","Рустэмович","11-403",18);
        System.out.println(mapper.toJson(student));
    }
}
