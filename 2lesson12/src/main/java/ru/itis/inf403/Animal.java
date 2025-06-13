package ru.itis.inf403;


import java.io.Serializable;

public class Animal implements Serializable {
    public static long version = 0;
    private int age;
    private String name;
    private AnimalClass category;
    private AnimalType type;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AnimalClass getCategory() {
        return category;
    }

    public void setCategory(AnimalClass category) {
        this.category = category;
    }

    public AnimalType getType() {
        return type;
    }

    public void setType(AnimalType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", type=" + type +
                '}';
    }
}
