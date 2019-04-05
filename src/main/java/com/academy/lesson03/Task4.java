/* Задание 4:
 Реализовать программу, которая:
	- считывает с клавиатуры строку
	- удаляет из строки все цифры
	- выводит исходную строку
	- выводит преобразованную строку
	- выводит список удаленных символов

*/
package com.academy.lesson03;

import java.util.Scanner;

public class Task4 {
    public static void main(String[] args) {

     // Получаем строку
        String newString = getOurString();

    // Выводим исходную строку
        printOurString("Ваша строка:\n", newString);

     // Выводим преобразованную строку - удаляем все цифры
        printOurString("Ваша строка без цифр:\n", deleteOfSymbols(newString,"[0-9]",""));

     // Выводим список удаленных символов - т.е. все удаленные цифры, разделяя их пробелом
        printOurString("Список удаленных символов:\n", deleteOfSymbols(newString,"[^0-9]"," "));
    }

    // Методы:
    // Считывание строки
    private static String getOurString() {
        System.out.println("Введите вашу строку:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    //Удаляет лишние символы
    private static String deleteOfSymbols(String newString,String extraSymbol, String replaceSymbol) {
        String str = newString.replaceAll(extraSymbol,replaceSymbol); // исключаем все, что требуется
        return str.replaceAll(" {2,}", " ").trim(); //удаляем все лишние пробелы
    }

    // Выводит строку с двумя параметрами
    private static void printOurString(String nameOfString,String str) {
        System.out.println(nameOfString+str);
    }
}