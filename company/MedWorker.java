package com.company;

import java.util.Scanner;

public class MedWorker implements ListRecord {
    String position = "врача общей специальности ";
    int age = 0;
    String name;
    String gender;

    public MedWorker() {
        setAge();
        setName();
        setGender();
    }

    void setName() {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите имя: ");
        name = in.nextLine();
    }

    void setAge() {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите возраст : ");
        age = in.nextInt();
    }

    void setGender() {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите пол: ");
        gender = in.nextLine();
    }

    public MedWorker(String position, int age, String name, String gender) {
        this.position = position;
        this.age = age;
        this.name = name;
        this.gender = gender;
    }

    public MedWorker(int age, String name) {
        this.age = age;
        this.name = name;
    }

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    @Override
    public void show() {

        System.out.println("специальность " + position);
        System.out.println("Возраст: " + age);
        System.out.println("Имя: " + name);
        System.out.println("Пол: " + gender);


    }


    public String showString() {
        return ("специальность " + position + "\n" +
                "Возраст: " + age + "\n" +
                "Имя: " + name + "\n" +
                "Пол: " + gender);
    }

}
