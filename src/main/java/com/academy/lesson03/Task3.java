/* Задание 2:
  Напишите программу, которая:
    - считывает строку
	- считывает подстроку
	- выводит исходные строку и подстроку и выводит кол-во вхождений строки в подстроку
*/
package com.academy.lesson03;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task3 {

    public static void main(String[] args) {

        // Получаем строку
        System.out.println("Введите строку:");
        String newString = getOurString();

        // Получаем подстроку
        System.out.println("Введите подстроку:");
        String newSubString = getOurString();

        // Выводим строку
        printOurString("Ваша строка:\n", newString);

        // Выводим подстроку
        printOurString("Ваша подстрока:\n", newSubString);

        // Считаем количество вхождений и выводим результат
        printOurString("Количество вхождений по разделению по регулярке = "+getCountOfSubstring(newString,newSubString),"");
    }


    // Методы:
        // Считывание строки
        private static String getOurString() {
            Scanner scanner = new Scanner(System.in);
            return scanner.nextLine();
        }

        // Выводит строку с двумя параметрами
        private static void  printOurString(String nameOfString,String str) {
            System.out.println(nameOfString+str);
        }

        // Возвращает количество вхождений подстроки
        private static int getCountOfSubstring(String newString, String newSubString) {

        //1 способ - Подсчет количества по методу find в классе Matcher

            Pattern p = Pattern.compile(newSubString);
            Matcher match = p.matcher(newString);

            int count = 0;   // счетчик вхождений

            while (match.find())
                count++;

            System.out.println("Количество вхождений по методу find из класса Matcher = " + count);

            //2 способ - Подсчет количестпа при помощи разделения по регулярке:

            Pattern p1 = Pattern.compile(newSubString);
            String[] items = p1.split(newString); // разделяем строку по регулярке, которая равна подстроке

            int count1 = -1; //счетчик по разделителю в регулярном выражении
            for (String s : items) count1++;
            return count1;



        }
}