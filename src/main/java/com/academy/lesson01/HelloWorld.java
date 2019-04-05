package com.academy.lesson01;
import java.util.Scanner;

public class HelloWorld {
    public static void main(String[] args) {
        //test1
        System.out.print("1 - Hello World!\n");
        System.out.println("2 - Hello World!");
        System.out.printf("3 - %s %s","Hello\t","World!");
        System.out.println(
                String.format("%nЭлемент с атрибутом %s=%d недоступен","id", 23));

        //test2
        System.out.println("*");
        System.out.println("**");
        System.out.println("***");
        System.out.println("****");
        System.out.println("*****");
        System.out.println("******");

        //test3
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nВведите n1:");
        int n1 = scanner.nextInt();       // чтение целого числа
        System.out.print("Введите n2:");
        int n2 = scanner.nextInt();       // чтение целого числа
        System.out.print("Введите n3:");
        int n3 = scanner.nextInt();       // чтение целого числа
        // ... использование введенных данных
        int sum = n1+n2+n3;
        System.out.println(
                String.format("%nСумма ваших чисел: n1=%d, n2=%d, n3=%d составляет = %d",n1,n2,n3, sum));

    }
}
