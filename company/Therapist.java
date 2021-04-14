package com.company;

import java.util.Scanner;

public class Therapist implements ListRecord{ // терапевт extends MedWorker
    String position = "терапевт ";
    int timeWork;
    int age;
    String name;

    public Therapist() {

        setAgeTherapist();
        setNameTherapist();
        setTimeWorkTherapist();
    }

    public Therapist(String position, int age, String name, int timeWork) {
        this.age = age;
        this.name = name;
        this.timeWork = timeWork;
    }

    public int getTimeWork() {
        return timeWork;
    }

    public void setTimeWork(int timeWork) {
        this.timeWork = timeWork;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }



    void setNameTherapist() {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите имя: ");
        name = in.nextLine();
    }

    void setAgeTherapist() {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите возраст : ");
        age = in.nextInt();
    }

    void setTimeWorkTherapist() {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите опыт работы : ");
        timeWork = in.nextInt();
    }

    public String showString() {
        return ("специальность " + position + "\n" +
                "Возраст: " + age + "\n" +
                "Имя: " + name + "\n" +
                "Пол: " + timeWork);
    }


    @Override
    public void show() {
        System.out.println("специальность " + position);
        System.out.println("Возраст: " + age);
        System.out.println("Имя: " + name);
        System.out.println("опыт работы : " + timeWork);
    }

    @Override
    public void setAge(int age) {
        this.age = age;
    }
}


