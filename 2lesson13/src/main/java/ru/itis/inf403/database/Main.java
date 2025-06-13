package ru.itis.inf403.database;

import ru.itis.inf403.model.Student;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DBUtils.readIndex();
        process();
        DBUtils.returnFile();
        DBUtils.writeIndex();
    }

    public static void process() {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("Введите операцию");
            System.out.println("Добавить студента: 1");
            System.out.println("Найти студента по id: 2");
            System.out.println("Удалить студента по id: 3");
            System.out.println("Изменить студента: 4");
            System.out.println("Выйти: 5");
            int s = scanner.nextInt();
            switch (s) {
                case 1:
                    Student student = new Student();
                    System.out.println("Напишите последовательно имя, фамилию, отчество, номер группы, Id");
                    scanner.nextLine();
                    student.setName(scanner.nextLine());
                    student.setLastName(scanner.nextLine());
                    student.setFatherName(scanner.nextLine());
                    student.setGroup(scanner.nextLine());
                    student.setId(scanner.nextInt());
                    DBUtils.appendObject(student);
                    System.out.println("Студент добавлен");
                    break;
                case 2:
                    System.out.println("Введите id");
                    Student student1 = DBUtils.findStudent(scanner.nextInt());
                    if (student1 == null) {
                        System.out.println("Студент не найден");
                    } else {
                        System.out.println(student1);
                    }
                    break;
                case 3:
                    System.out.println("Введите id удаляемого студента");
                    DBUtils.delete(scanner.nextInt());
                    System.out.println("Если студент с таким id был в списке, он успешно удалён");
                    break;
                case 4:
                    System.out.println("Введите новые данные студента");
                    System.out.println("Напишите последовательно имя, фамилию, отчество, номер группы, Id");
                    Student student2 = new Student();
                    scanner.nextLine();
                    student2.setName(scanner.nextLine());
                    student2.setLastName(scanner.nextLine());
                    student2.setFatherName(scanner.nextLine());
                    student2.setGroup(scanner.nextLine());
                    student2.setId(scanner.nextInt());
                    DBUtils.editStudent(student2);
                    System.out.println("Студент изменён");
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Введите число от 1 до 5");
                    break;

            }
        }
    }
}
