/*Lesson 2. Задание 14. Нарисовать треугольник заданной размерности.
      *
     **
    ***
   ****
  *****
 ******
 */

package com.academy.lesson02;

//import java.util.Arrays;
import java.util.Scanner;

public class Task14 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int i;
        System.out.print("Введите размерность треугольника:");
        int m = scanner.nextInt();

        String[] arr = new String[m];
        java.util.Arrays.fill(arr, " "); //заполняем строку пробелами

        for (i = 0; i < m; i++) { // цикл для количества строк

         //заполняем справа налево строку нужным кол-вом звездочек
         java.util.Arrays.fill(arr, m-1-i,m-i,"*");

            for (String s : arr)
            System.out.print(s); //выводим построчно готовую строку

            System.out.println(); //перевод на новую строку
         }
    }

}
