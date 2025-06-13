package ru.itis.inf403;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        Animal animal = new Animal();
        animal.setName("Алиса");
        animal.setAge(12);
        animal.setCategory(AnimalClass.Млекопитающие);
        animal.setType(AnimalType.Лиса);
        byte[] savedData = null;
        try(ByteArrayOutputStream baos = new ByteArrayOutputStream(); ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(animal);
            savedData = baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        for (byte b : savedData) {
//            System.out.print(b);
//        }
        try(ByteArrayInputStream bais = new ByteArrayInputStream(savedData);ObjectInputStream ois = new ObjectInputStream(bais)) {
            Animal animal2 = (Animal) ois.readObject();
            System.out.println(animal2);
        } catch(IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
