/*
1) Дан массив имен: {"Peter", "Helen", "Andry", "Abdel", "Kate", "Veronica", "Leonid", "Alex", "max}
	a) Создать список List из элементов массива и вывести на экран
	b) Добавить 3 новых имени в список (Philip, Joseph, Leon) и вывести весь список на экран
	c) Удалить из списка все имена: Veronica, Leonid и вывести на экран
	d) Поменять имя Helen на Elizabet
	e) Отсортировать имена по алфавиту и вывести на экран
	f) Отсортировать имена в обратном порядке и вывести имена на экран
	g) Перевести все первые символы в верхний регистр
	h) Вывести все имена, начинающиеся на букву A
	i) Проверить, содержит ли список имя Andry
	j) Удалить из списка все имена кроме Kate и Helen
 */
package com.academy.lesson10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HomeTask10_1 {
    public static void main(String[] args) {
        String[] stringArray = {"Peter", "Helen", "Andry", "Abdel", "Kate", "Veronica", "Leonid", "Alex", "max" };
        // List<String> list = new ArrayList<>(Arrays.asList("Peter", "Helen", "Andry", "Abdel", "Kate", "Veronica", "Leonid", "Alex", "max"));

        //a) Создать список List из элементов массива и вывести на экран
        List<String> list = new ArrayList<>(Arrays.asList(stringArray));
        System.out.println("Список List:\n" + list);

        //b) Добавить 3 новых имени в список (Philip, Joseph, Leon) и вывести весь список на экран
        list.addAll(Arrays.asList("Philip", "Joseph", "Leon"));
        System.out.println("\nСписок List после добавления (Philip, Joseph, Leon):\n" + list);

        //c) Удалить из списка все имена: Veronica, Leonid и вывести на экран
        list.removeAll(Arrays.asList("Veronica", "Leonid"));
        System.out.println("\nСписок List после удаления из списка всех имен: Veronica, Leonid:\n" + list);

        //d) Поменять имя Helen на Elizabet
        for (int i = 0; i < list.size(); i++)
            if (list.get(i) == "Helen") {
                list.set(i, "Elizabet"); //в цикле ищем и заменяем
            }
        System.out.println("\nСписок List после замены всех 'Helen' на 'Elizabet':\n" + list);

        //e) Отсортировать имена по алфавиту и вывести на экран
        Collections.sort(list);
        System.out.println("\nСписок после сортировки:\n" + list);

        //f) Отсортировать имена в обратном порядке и вывести имена на экран
        Collections.reverse(list);
        System.out.println("\nСписок после обратной сортировки:\n" + list);

        //g) Перевести все первые символы в верхний регистр
        for (int i = 0; i < list.size(); i++)
            list.set(i, list.get(i).substring(0, 1).toUpperCase() + list.get(i).substring(1));
        System.out.println("\nСписок после Uppercase для перевых символов:\n" + list);

        //h) Вывести все имена, начинающиеся на букву A
        System.out.println("\nВсе имена, начинающиеся на A: ");
        for (int i = 0; i < list.size(); i++)
            if (list.get(i).substring(0, 1).equals("A"))
                 System.out.println(list.get(i));

        //i) Проверить, содержит ли список имя Andry
        if (list.contains("Andry")) System.out.println("\nВаш список содержит имя 'Andry'");
        else System.out.println("\nВаш список НЕ содержит имя 'Andry'");

        //j) Удалить из списка все имена кроме Kate и Helen
        List<String> list1 = new ArrayList<>(Arrays.asList("Peter", "Helen", "Andry", "Abdel", "Kate", "Veronica", "Leonid", "Alex", "max"));
        System.out.println("\nИсходный список List:\n" + list1);
        list1.retainAll(Arrays.asList("Kate","Helen"));
        System.out.println("\nСписок List после удаления из списка всех имен кроме (Kate и Helen):\n" + list1);


    }
}
