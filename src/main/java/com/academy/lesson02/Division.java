//Lesson 2. Задание 7. Создайте программу, выводящую на экран результат деления q на w с остатком.

package com.academy.lesson02;

import java.util.Scanner;

public class Division {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите q:");
        int q = scanner.nextInt();       // чтение целого числа
        System.out.print("Введите w:");
        int w = scanner.nextInt();       // чтение целого числа

        // вывод вычислений
        System.out.println("число q="+q);
        System.out.println("число w="+w);
        System.out.println(String.format("результат целочисленного деления q/w =%d:",q/w));
        System.out.println(String.format("остаток от деления q/w =%d:",q%w));
    }
}