/* Задание 2:
        Напишите программу, которая:
        - считывает строку
	    - выводит исходную строку
	    - выводит количество и список, которые заканчиваются буквами 'ED'
*/
package com.academy.lesson03;

import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
    // Получаем строку
        String newString = getOurString();

    // Выводим строку
        printOurString(newString);

    // Выводим количество слов с началом на "d" и их список
        printCountOfWords(newString);
    }

    // Методы:
    // Считывание строки
    private static String getOurString() {
        System.out.println("Введите вашу строку:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    // Вывод введенной строки
    private static void  printOurString(String str) {
        System.out.println("Ваша исходная строка:\n" + str);
    }

    // Вывод количества слов, которые заканчиваются буквами 'ED'
     private static void printCountOfWords(String str) {
         int count = 0;   // обнуляем счетчик

         String[] strArr = str.split(" "); //создаем массив из слов
         System.out.println("Все слова, которые заканчиваются буквами 'ED':");

         for (int i = 0; i < strArr.length; i++)

               if(strArr[i].endsWith("ED")){      //а можно метод "endsWith"
                  System.out.println(strArr[i]);   //выводим массив слов с "ED"
                 count++;
               }
         System.out.println("Количество слов, которые заканчиваются буквами 'ED'= "+count);
     }
}