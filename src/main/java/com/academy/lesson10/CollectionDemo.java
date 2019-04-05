package com.academy.lesson10;

import com.academy.Abonent;
import com.academy.lesson08.ParseAbonentException;


import java.util.*;

public class CollectionDemo {
    public static void main(String[] args) {
        String[] abonentRawArray = {
                "  heLen iVanova 35 f 0501234567",
                "  Андрей Сидоров 26 m 0501234568",
                "  Peter Lvovich 37 m 0501234569",
        };

        String[] abonentRawArray2 = {
                "  Андрей Сидоров 26 m 0501234568",
                "  heLen iVanova 35 f 0501234567",
                "  Peter Lvovich 37 m 0501234569",
        };

        try {
            Abonent[] abonentArray = parseAbonents(abonentRawArray);
            Abonent[] abonentArray2 = parseAbonents(abonentRawArray2);
            List<Abonent> abonentList = new ArrayList<>();
            // 1 способ
            for (Abonent abonent : abonentArray) {
                abonentList.add(abonent);
               }
            //System.out.println("abonentList:"+abonentList);//вывод в строку

            System.out.println("abonentList в столбец:");
            for (int i = 0; i < abonentList.size(); i++) System.out.println(abonentList.get(i));

            // 2 способ
            List<Abonent> abonentList2 = new ArrayList<>(Arrays.asList(abonentArray2));

            System.out.println("\nabonentList2 в столбец:");
            for (int i = 0; i < abonentList2.size(); i++) System.out.println(abonentList2.get(i));

            //System.out.println("abonentList2:"+abonentList2);//вывод в строку
            System.out.println("\nСравнение abonentList.equals(abonentList2) = "+abonentList.equals(abonentList2));

            assert abonentList.equals(abonentList2);

            // через Set - сразу сортируется
            Set<Abonent> abonentSet = new HashSet<>(abonentList);
            Set<Abonent> abonentSet2 = new HashSet<>(abonentList2);
            //System.out.println("abonentSet2:"+abonentSet2); //вывод в строку
            System.out.println("\nabonentSet2 в столбец:");
            abonentSet2.forEach(System.out::println);
            System.out.println("\nСравнение abonentSet.equals(abonentSet2)= "+abonentSet.equals(abonentSet2));


//            // 3 способ
//            List<Abonent> listTmp = new ArrayList<>(Arrays.asList(abonentArray2));
//            listTmp.add(new Abonent());

        } catch (ParseAbonentException e) {
            e.printStackTrace();
        }
    }

    private static Abonent[] parseAbonents(String[] abonentRawArray) throws ParseAbonentException {
        Abonent[] abonents = new Abonent[abonentRawArray.length];
        for (int i = 0; i < abonentRawArray.length; i++) {
            abonents[i] = AbonentParser.parseAbonent(abonentRawArray[i]);
        }
        return abonents;
    }
}