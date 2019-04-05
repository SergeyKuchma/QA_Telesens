//Lesson 2. Задание 1. Написать программу ввода с клавиатуры последовательно 3 чисел.
//                     Вывести минимальное максимальное и средние значения

package com.academy.lesson02;
import java.util.Scanner;

public class MinMaxAvg {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int i,j,k,m=3;
        int[] arr = new int[m];

        System.out.println();

        //чтение чисел сразу в массив
        for (i = 0; i < m; i++) {
            System.out.print(String.format("Введите число[%d]:",i+1));
            arr[i] = scanner.nextInt();       // чтение целого числа в массив
                                    }

         //сортировка массива в порядке возрастания
        for (i = 0; i < m; i++)
              for (j = 0; j < m-1-i; j++)
                       if ( arr[j] > arr[j+1]) {
                            k = arr[j];
                            arr[j] = arr[j+1];
                            arr[j+1] = k;
                        }

        System.out.println("\nМинимальное число=" + arr[0]);
        System.out.println("Среднее число=" + arr[1]);
        System.out.println("Максимальное число=" + arr[2]);
    }
}
