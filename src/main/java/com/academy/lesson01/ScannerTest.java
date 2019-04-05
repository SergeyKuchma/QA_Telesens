package com.academy.lesson01;

import java.util.Scanner;

public class ScannerTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();       // чтение строки
        double d = scanner.nextDouble(); // чтение вещественного числа
        int i = scanner.nextInt();       // чтение целого числа
        // ... испольование введенных данных
    }
}
