/*
ДЗ 10 Задание 2
2) List
	a) создать список целых чисел
	b) наполнить список случайными целыми числами от 1 до 100 (размер списка 20 элементов)
	c) вывести список на экран
	d) вывести минимальное, максимально значения
	e) вывести сумму чисел на экран
	f) найти наиболее часто встречающееся число, вывести это число и кол-во повторений на экран
		(если чисел несколько с одинаковой частотой повторений, то вывести минимальное из них)
*/
package com.academy.lesson10;

import java.util.*;

public class HomeTask10_2 {
    public static void main(String[] args) {

//  a) создать список целых чисел
        List<Integer> list = new ArrayList<>();
//	b) наполнить список случайными целыми числами от 1 до 100 (размер списка 20 элементов)
        Random random= new Random();
        for (int i = 0; i < 20; i++)
            list.add(random.nextInt(101));
//  c) вывести список на экран
        System.out.println("Список List:\n" + list);

//  d) вывести минимальное, максимально значения

        System.out.println("Минимальное значение = " + Collections.min(list));
        System.out.println("Максимальное значение = " + Collections.max(list));

//  e) вывести сумму чисел на экран
        int sum=0;
        for (int i = 0; i < list.size(); i++)
        sum+=list.get(i);
        System.out.println("Сумма чисел = " + sum);

//  f) найти наиболее часто встречающееся число, вывести это число и кол-во повторений на экран
// (если чисел несколько с одинаковой частотой повторений, то вывести минимальное из них)

        Collections.sort(list);//сортируем по возрастанию, чтобы вывести минимальное из чисел
        System.out.println("Список после сортировки:\n" + list);
        Collections.frequency(list,list);
        int iCurrent=0;
        int freCurrent=0;
        for (int i = 0; i < list.size(); i++)//ищем максимальную частоту и запонимаем его индекс
            if (freCurrent<Collections.frequency(list,list.get(i))){
                freCurrent=Collections.frequency(list,list.get(i));
                iCurrent=i;}

        if (freCurrent>1) System.out.println(String.format("Максимальная частота повторений = %d для числа = %d",freCurrent,list.get(iCurrent)));
            else System.out.println("В вашем списке цифры не содержат повторений");
    }
}
