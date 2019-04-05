/*
5) Есть два списка email:
		set1 = {"account@gmail.com", "my-mail@yandex.ru", "abc@yahoo.com", "mail@gmail.com"}
		set2 = {"student@gmail.com", "my-mail@yandex.ru", "private-mail@yahoo.com", "mail-for-spam@gmail.com"}

	a) вывести на экран email, которые встречаются в двух списках
	b) вывести на экран email из первого списка, которых нет во втором
	c) вывести на экран email из двух списков, но чтобы не было повторений
 */
package com.academy.lesson10;

import java.util.*;

public class HomeTask10_5 {
    public static void main(String[] args) {
        String[] strArr1={"account@gmail.com", "my-mail@yandex.ru", "abc@yahoo.com", "mail@gmail.com"};
        String[] strArr2={"student@gmail.com", "my-mail@yandex.ru", "private-mail@yahoo.com", "mail-for-spam@gmail.com"};

        Set<String> set1 = new HashSet<>(Arrays.asList(strArr1));
        Set<String> set2 = new HashSet<>(Arrays.asList(strArr2));
        Set<String> set3 = new TreeSet<>(); //для сортированного списка
        System.out.println("--------- a) ---------");
        System.out.println("---------Исходные sets-----");
        System.out.println("Set1:"+set1);
        System.out.println("Set2:"+set2);

        //a) вывести на экран email, которые встречаются в двух списках
        System.out.println("\nСписок emails, которые встречаются в двух списках:");
        set1.retainAll(set2);
        set1.forEach(System.out::println);

        //b) вывести на экран email из первого списка, которых нет во втором
        set1 = new HashSet<>(Arrays.asList(strArr1));
        set2 = new HashSet<>(Arrays.asList(strArr2));
        System.out.println("\n--------- b) ---------");
        System.out.println("---------Исходные sets-----");
        System.out.println("Set1:"+set1);
        System.out.println("Set2:"+set2);
        System.out.println("\nСписок emails из первого списка, которых нет во втором:");
        set1.removeAll(set2);
        System.out.println(set1);

        //c) вывести на экран email из двух списков, но чтобы не было повторений
        set1 = new HashSet<>(Arrays.asList(strArr1));
        set2 = new HashSet<>(Arrays.asList(strArr2));
        System.out.println("\n--------- с) ---------");
        System.out.println("---------Исходные sets-----");
        System.out.println("Set1:"+set1);
        System.out.println("Set2:"+set2);
        System.out.println("\nСписок всех emails отсортированный и без повторений:");
        set3.addAll(set1);
        set3.addAll(set2);
        System.out.println(set3);
    }
}
