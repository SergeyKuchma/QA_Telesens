package com.academy.lesson03;

import java.util.Arrays;

public class SampleDemo {
    public static void main(String[] args) {
        String[] tracks={"track_01", "track_03", "track_21", "track_15", "track_12","track_99", "track_11", "track_10"};


        System.out.println("Ваш массив:"+Arrays.toString(tracks));
        System.out.println("Выборка треков >= 10 И <= 15:\n=>");

        for (int i = 0; i < tracks.length; i++)
            {
             int n = Integer.parseInt(tracks[i].replaceAll("[^0-9]", ""));//исключаем все, что не цифры
                if (n >= 10 && n <= 15)
                    System.out.println(tracks[i]);
            }
    //Задание стр.36
        System.out.println("\nЗадание стр.36\n");

        String[] allUsers = {"Andry", "Abdel", "Nataly", "Alyona", "Nataly", "Alyona", "Andry", "Abdel"};
        String[] firstPartOfUsers = new String[4];
        String[] secondPartOfUsers = new String[4];

        System.out.println("allUsers: "+Arrays.toString(allUsers));

        firstPartOfUsers = Arrays.copyOfRange(allUsers, 0, 4);
        System.out.println("firstPartOfUsers: "+Arrays.toString(firstPartOfUsers));

        secondPartOfUsers = Arrays.copyOfRange(allUsers, 4, 8);
        System.out.println("secondPartOfUsers: "+Arrays.toString(secondPartOfUsers));

        System.out.println("Сравнение массивов = "+Arrays.equals(firstPartOfUsers, secondPartOfUsers));

    }

}
