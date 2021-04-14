package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Сalculator {

    public static void main(String[] args) {

        StringBuilder digitsMas = new StringBuilder();
        StringBuilder znaksMas = new StringBuilder();

        Scanner sq = new Scanner(System.in);
        System.out.println("Введите пример : ");
        String str = sq.nextLine();

        String[] strMas = str.split(" ");

        for (int i = 0; i < strMas.length; i++) {

            if (isNumber(strMas[i])) {
                digitsMas.append(strMas[i]);
                digitsMas.append(" ");

            }

            if (znak(strMas[i])) {
                znaksMas.append(strMas[i]);
                znaksMas.append(" ");
            }

            //Integer.parseInt(String.valueOf(digitsMas.get(0)))
        }
        System.out.println(result(strMas(digitsMas), strMas(znaksMas)));
    }

    public static boolean isNumber(String element) {
        try {
            Integer.parseInt(element);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean znak(String element) {

        boolean result = false;
        String znaki = "+/*-";
        char[] znakiMas = znaki.toCharArray();
        char[] elementMas = element.toCharArray();

        for (char i : elementMas) {
            for (char elementZnac : znakiMas) {
                if (i == elementZnac) {
                    result = true;
                    break;
                }
            }
            if (result)
                break;
        }
        return result;
    }

    public static int result(String[] digitMasStr, String[] znakMas) {
        int result = 0;
        int[] digitMas = processLine(digitMasStr);

        for (int i = 0; i < znakMas.length; i++) {
            if (znakMas[i].equals("*")) {
                result = result + digitMas[i] * digitMas[i + 1];
            } else if (znakMas[i].equals("/")) {
                result = result + digitMas[i] / digitMas[i + 1];
            } else if (znakMas[i].equals("+")) {
                result = result + digitMas[i] + digitMas[i + 1];
            } else if (znakMas[i].equals("-")) {
                result = result + digitMas[i] - digitMas[i + 1];
            }
        }


        return result;
    }


    public static int[] processLine(String[] string) {
        int[] intarray = new int[string.length];
        int i = 0;
        for (String str : string) {
            intarray[i] = Integer.parseInt(str);
            i++;
        }
        return intarray;
    }


    public static String[] strMas(StringBuilder str) {
        return (str.toString().split(" "));
    }

}
