/* Задание 1:
        Напишите программу, которая:
        - считывает строку
        - выводит исходную строку
        - выводит количество и список слов, которые начинаются на букву 'd'
*/
package com.academy.lesson03;

import java.util.Scanner;

public class Task1 {
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

    // Вывод количества слов с началом на "d" и их списка
     private static void printCountOfWords(String str) {
         int count = 0;   // обнуляем счетчик

         String[] strArr = str.split(" "); //создаем массив из слов
         System.out.println("Все слова начинающиеся на 'd':");

         for (int i = 0; i < strArr.length; i++)

             //if(strArr[i].charAt(0) == 'd') {    //можно использовать "charAt(0)" - обращение к первой букве
               if(strArr[i].startsWith("d")){      //а можно метод "startsWith"
                  System.out.println(strArr[i]);   //выводим массив слов, начинающихся с "d"
                 count++;
               }
         System.out.println("Количество слов начинающихся на 'd'= "+count);
     }
}
