package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";


    public static void main(String[] args) {
        ArrayList<ListRecord> list = new ArrayList<>();

        int ans = 0;

        while (ans != 9) {
            System.out.println("Меню\n" +
                    "1. Добавить запись\n" +
                    "2. Удалить запись\n" +
                    "3. Изменить запись\n" +
                    "4. Поиск записи\n" +
                    "5. Вывод на экран\n" +
                    "6. Вывод число записей\n" +
                    "7. добавть в файл записи\n" +
                    "8. считать с файлла \n" +
                    "9. Выход \n" +
                    "Выберите действие");
            try {
                Scanner in = new Scanner(System.in);
                ans = in.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Введите число");
                ans = 0;
            }

            Scanner in = new Scanner(System.in);

            try {
                switch (ans) {
                    case 1 -> addRecordToEnd(list, in);
                    case 2 -> deleteRecord(list, in);
                    case 3 -> changeRecord(list, in);
                    case 5 -> printRecord(list);
                    case 6 -> CountRecord(list);
                    case 7 -> addRecordInFile(list);
                    case 8 -> readRecordFromFile(list);
                    case 9 -> {}
                    default -> System.out.println("Выберите действие заново");
                }


            } catch (InputMismatchException e) {
                System.out.println("Ошибка ввода \n");
            }
        }

    }

    private static void readRecordFromFile(ArrayList<ListRecord> list) {
        try (BufferedReader br = new BufferedReader(new FileReader("notes.txt"))) {

            //считывание построчно
            String str;
            String position;
            String name;
            int age;
            String gender;
            int timeWork;
            int recordCounter = 0;

            while ((str = br.readLine()) != null) {

                String[] parts;
                parts = br.readLine().split(" ");

                position = parts[1];
                parts = br.readLine().split(" ");
                age = Integer.parseInt(parts[1]);
                parts = br.readLine().split(" ");
                name = parts[1];
                parts = br.readLine().split(" ");


                if (!isNumeric(parts[parts.length - 1])) {
                    gender = parts[1];
                    list.add(new MedWorker(position, age, name, gender));
                }
                if (isNumeric(parts[parts.length - 1])) {
                    timeWork = Integer.parseInt(parts[parts.length - 1]);
                    list.add(new Therapist(position, age, name, timeWork));
                }


            }


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void addRecordInFile(ArrayList<ListRecord> list) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("notes.txt", true))) {
            int count = 0;
            File file = new File("notes.txt");
            for (ListRecord record : list) {
                bw.newLine();
                // System.out.println(file.length());

                if (file.length() != 0 && count == 0) {
                    bw.write("\n");
                }
                bw.write(record.showString());
                count++;
                if (count != list.size()) {
                    bw.write("\n");
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void CountRecord(ArrayList<ListRecord> list) {
        if (list.isEmpty()) {
            System.out.println("Список пуст\n");
            return;
        }
        System.out.println("В списке " + list.size() + " записей");
        return;
    }

    private static void changeRecord(ArrayList<ListRecord> list, Scanner in) {
        try {
            if (list.isEmpty()) {
                System.out.println("Нет записей для поиска\n");
                return;
            }
            System.out.println("какую запись хотите измениить ?");

            int index = in.nextInt();

            System.out.println("что вы хотите измнитьть ? \n" +
                    "1 age \n" +
                    "2 name \n");
            int indexChange = in.nextInt();
            switch (indexChange) {
                case 1:
                    System.out.println("введите новый  возраст ");
                    int chAge = in.nextInt();

                    list.get(index - 1).setAge(chAge);

                    break;

                case 2:
                    System.out.println("введите новый  имя ");
                    Scanner in1 = new Scanner(System.in);
                    String chName = in1.nextLine();

                    list.get(index - 1).setName(chName);

                    break;

                default:
                    break;
            }

            System.out.println("Запись успешно изменена\n");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Вы вышли за диапазон значений");
        }
        return;
    }

    private static void deleteRecord(ArrayList<ListRecord> list, Scanner in) {
        if (list.isEmpty()) {
            System.out.println("Нет записей для удаления\n");
            return;
        }

        System.out.println("1 Удалить последнюю запись\n" +
                "2 Удалить запись по индексу\n");
        int localAns = in.nextInt();
        switch (localAns) {
            case 1:
                list.remove(list.size() - 1);
                System.out.println("Запись успешно удалена\n");
                break;
            case 2:
                System.out.println("Введите индекс для удаления записи от 0 до " + (list.size()));
                try {
                    int index = in.nextInt();
                    list.remove(index);
                    System.out.println("Запись успешно удалена\n");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Вы вышли за диапазон значений");
                }
                break;
            default:
                break;
        }

    }

    private static void printRecord(ArrayList<ListRecord> list) {
        if (list.isEmpty()) {
            System.out.println("Список пуст\n");
            return;
        }
        int i = 1;
        for (ListRecord record : list) {
            System.out.println("Запись № " + i);
            i++;
            record.show();
        }
    }

    private static void addRecordToEnd(ArrayList<ListRecord> list, Scanner in) {
        int localAns;
        System.out.println("1 Добавить запись в конец\n" +
                "2 Добавить запись по индексу\n");
        localAns = in.nextInt();
        try {
            switch (localAns) {
                case 1:

                    System.out.println("1) добавить врача общей специальности \n" +
                            "2) добавить терапевта  \n ");
                    localAns = in.nextInt();
                    switch (localAns) {
                        case 1:
                            list.add(new MedWorker());
                            System.out.println("Запись успешно добалена\n");
                            break;

                        case 2:
                            list.add(new Therapist());
                            System.out.println("Запись успешно добалена\n");
                            break;

                        default:
                            System.out.println(ANSI_RED + "вы вышли за пределы границы !!!" + ANSI_RESET);
                            break;
                    }
                    break;
                case 2:
                    System.out.println("Введите индекс для добавления от 0 до " + (list.size()));

                    int index = in.nextInt();
                    if (index > list.size()) {
                        System.out.println("некоректный ввод");
                        break;
                    }
                    try {
                        System.out.println("1) добавить врача общей специальности \n" +
                                "2) добавить терапевта  \n ");
                        localAns = in.nextInt();
                        switch (localAns) {
                            case 1:

                                list.add(index, new MedWorker());
                                System.out.println("Запись успешно добалена\n");
                                break;

                            case 2:
                                list.add(index, new Therapist());
                                System.out.println("Запись успешно добалена\n");
                                break;

                            default:
                                break;
                        }
                    } catch (StackOverflowError e) {
                        System.out.println("Вы вышли за диапазон значений");
                    }
            }
        } catch (InputMismatchException e) {
            System.out.println("Ошибка ввода \n");
        }
    }


    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
