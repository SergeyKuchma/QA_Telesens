/*
4) Представить данные о пользователях в виде ассоциативного массива (Map) (login / пароль) с предположением,
что все логины пользователей разные.
Написать метод:
- print(Map<String, String> accounts, length) // который выводит данные о пользователях с длиной пароля длиннее length
 */
package com.academy.lesson10;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HomeTask10_4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, String> accounts = new HashMap<>();
        accounts.put("test1","test1");//(Map) (login / пароль)
        accounts.put("test2","test22");
        accounts.put("test3","test333");
        accounts.put("test4","test4444");
        System.out.println("--------полный список MAP-------------");
        System.out.println(accounts);

        System.out.print("Введите длину пароля для отбора:");
        int length = scanner.nextInt();
        print(accounts, length);

    }
    public static void print(Map<String, String> accounts, int length) {
        System.out.println("\nВывод Accounts с длиной пароля длиннее, чем "+length+" :");
        for (String key : accounts.keySet()) {
            if (accounts.get(key).length() > length)
                System.out.println("Map("+key+" => "+accounts.get(key)+")"); //
        }
    }
}
