/*Lesson 2. Задание 13. Нарисовать треугольник заданной размерности.
*
**
***
****
*****
******
*/

package com.academy.lesson02;

import java.util.Scanner;

public class Triangles {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int i,j;
        System.out.print("Введите размерность треугольника:");
        int m = scanner.nextInt();
        for (i = 0; i < m; i++){
            System.out.println();
            for (j = 0; j < m; j++)
                if (j<=i)
                System.out.print("*");
        }
    }
}
