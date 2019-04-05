/* Задание 5:
 Реализовать программу, которая:
	- считывает строку
	- меняет местами порядок символов (напр. "abcd" => "dcba")
	- выводит исходную строку
	- выводит перевернутую строку

*/
package com.academy.lesson03;

import java.util.Scanner;

public class Task5 {
    public static void main(String[] args) {

     // Получаем строку
        String newString = getOurString();

    // Выводим исходную строку
        printOurString("Ваша строка:\n", newString);

     // Выводим перевернутую строку
        printOurString("Ваша перевернутая строка:\n", getReverseString(newString));
    }

    // Методы:
    private static String getReverseString(String newString) {
        StringBuilder newStr = new StringBuilder(newString);
        return newStr.reverse().toString(); //переворачиваем методом reverse класса StringBuilder и возвращаем тип String
    }

    // Считывание строки
    private static String getOurString() {
        System.out.println("Введите вашу строку:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    // Выводит строку с двумя параметрами
    private static void printOurString(String nameOfString,String str) {
        System.out.println(nameOfString+str);
    }
}