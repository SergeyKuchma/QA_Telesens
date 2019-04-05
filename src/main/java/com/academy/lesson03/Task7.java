/* Задание 7:
 Реализовать программу, которая определяет, является ли строка палиндромом.
Палиндром - это строка, которая одинаково читается одинаково в обоих направлениях
(пример: "мадам" - палиндром, "адам" - не палиндром).

*/
package com.academy.lesson03;

import java.util.Scanner;

public class Task7 {
    public static void main(String[] args) {

     // Получаем строку
        String newString = getOurString();

    // Выводим исходную строку
        printOurString("\nВаша строка:\n", newString);

     // Выводим перевернутую строку
        printOurString("Ваша перевернутая строка:\n", getReverseString(newString));

    // Выводим результат сравнения строк - палиндром или нет?
        printOurString("\n", getPalindrome(newString));

    }
    // Методы:
    //Сравниваем строку с перевернутой
    private static String getPalindrome(String newString) {
    if(getReverseString(newString).equals(newString)) //сравниваем строку с перевернутой
        return "Ваша строка ЯВЛЯЕТСЯ палиндромом";//возвращаем строку с ответом: это палиндром
    else return "Ваша строка НЕ ЯВЛЯЕТСЯ палиндромом";//возвращаем строку с ответом: это НЕ палиндром
    }

    //Переворачиваем строку методом reverse класса StringBuilder
    private static String getReverseString(String newString) {
        StringBuilder newStr = new StringBuilder(newString);
        return newStr.reverse().toString(); //переворачиваем и возвращаем тип String
    }

    // Считывание строки
    private static String getOurString() {
        System.out.println("Введите вашу строку:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    // Вывод строки с двумя параметрами
    private static void printOurString(String nameOfString,String str) {
        System.out.println(nameOfString+str);
    }
}