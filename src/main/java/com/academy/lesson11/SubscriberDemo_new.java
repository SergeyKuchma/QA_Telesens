package com.academy.lesson11;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

public class SubscriberDemo_new {
    private static String subscriberDataPath = "subscriber.txt";
    private static String subscriberDataPath2 = "subscriber-2.txt";
    private static String subscriberMapDataPath = "subscriber-map.txt";
    private static String subscriberExcelDataPath = "subscriber.xlsx";

    public static void main(String[] args) {
        // Operators
        Operator operatorLife = new Operator();
        operatorLife.setId(1L);
        operatorLife.setName("Life");

        Operator operatorKievstar = new Operator();
        operatorKievstar.setId(2L);
        operatorKievstar.setName("Kievstar");

        Operator operatorVodafone = new Operator();
        operatorVodafone.setId(3L);
        operatorVodafone.setName("Vodafone");

        // Subscribers
        Subscriber subscriber1 = new Subscriber();
        subscriber1.setId(1L);
        subscriber1.setFirstName("Иван");
        subscriber1.setLastName("Васильев");
        subscriber1.setGender(Gender.MALE);
        subscriber1.setAge(23);
        subscriber1.setPhoneNumber("380630025465");
        subscriber1.setOperator(operatorLife);

        Subscriber subscriber2 = new Subscriber();
        subscriber2.setId(2L);
        subscriber2.setFirstName("Катя");
        subscriber2.setLastName("Петрова");
        subscriber2.setGender(Gender.FEMALE);
        subscriber2.setAge(34);
        subscriber2.setPhoneNumber("380670058694");
        subscriber2.setOperator(operatorKievstar);

        List<Subscriber> subscribers = new ArrayList<>();

        // => на консоль
        subscribers.add(subscriber1);
        subscribers.add(subscriber2);
        System.out.println(subscribers);

        // => MAP
        System.out.println("=======MAP======");
        Map<Long, Subscriber> subscriberMap = new HashMap<>();
//        subscriberMap.put(1L, subscriber1);
        subscriberMap.put(subscriber1.getId(), subscriber1);
        subscriberMap.put(subscriber2.getId(), subscriber2);

        System.out.println(subscriberMap.get(2L));

        // 1 способ
        System.out.println("1) не рабочий");
        for (int i = 1; i < subscriberMap.size(); i++) {
            System.out.println(subscriberMap.get(i)); // не работает
        }

        // 2 способ
        System.out.println("2) явно  по ключам");
        for (Long key: subscriberMap.keySet()) {
            System.out.println(subscriberMap.get(key)); // норм
        }

        // 2-б способ
        System.out.println("2-б) явно  по ключам");
        Set<Long> keys = subscriberMap.keySet();
        List<Long> keysList = new ArrayList<>(keys);
        for (int i = 0; i < keysList.size(); i++) {
            Long key = keysList.get(i);
            System.out.println(subscriberMap.get(key)); // норм
        }

        // 3 способ
        System.out.println("3) без ключей");
        for (Subscriber subscriber: subscriberMap.values()) {
            System.out.println(subscriber); // норм
        }

        // 4 способ
        System.out.println("4) ключ => значение");
        for (Map.Entry<Long, Subscriber> entry: subscriberMap.entrySet()) {
            System.out.println(entry.getKey() + "=>" + entry.getValue()); // норм
        }

        // 5 способ !!!
        System.out.println("5) только вывод");
        System.out.println(subscriberMap);

        // пишем в файлы
        System.out.println("Пишем в файл");
        try(PrintWriter pw = new PrintWriter(subscriberDataPath);
            PrintWriter pwMap = new PrintWriter(subscriberMapDataPath)) {
            pw.println(subscribers);
            pwMap.println(subscriberMap);
            pw.close(); // нужно вызвать обязательно или поместить в блок try
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // читаем из файла
        System.out.println("Читаем из файла");
        try(FileReader fileReader = new FileReader(subscriberDataPath2);
            BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String row;
            // перебираем все строки
            while((row = bufferedReader.readLine()) != null)
                if (row.isEmpty())
                    System.out.println("---empty---");
                else
                    System.out.println(row);
        } catch (IOException e) {
            e.printStackTrace();
        }


        // ------------- здесь надо сделать сортировку ----------
// 1 вариант
// subscribers.sort(new ComparatorByAgeImpl());

   // 2 вариант
        /* subscribers.sort(new Comparator<Subscriber>() {
            @Override
            public int compare(Subscriber o1, Subscriber o2) {
                return Long.compare(o2.getId(), o1.getId());
           }
        });
        */
        //3 вариант - через ЛЯМДЫ
         subscribers.sort((o1,o2) -> Long.compare(o2.getId(),o1.getId()));

        //4 вариант - СУПЕР-КОРОТКО через ссылку на метод
        subscribers.sort(Comparator.comparing(Subscriber::getId));




        // Пишем в excel
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Subscribers");

        XSSFRow row = sheet.createRow(0);
        XSSFCell cellId = row.createCell(0);
        cellId.setCellValue("Id");

        XSSFCell cellFirstName = row.createCell(1);
        cellFirstName.setCellValue("First Name");

        XSSFCell cellLastName = row.createCell(2);
        cellLastName.setCellValue("Last Name");

        XSSFCell cellGender = row.createCell(3);
        cellGender.setCellValue("Gender");

        XSSFCell cellAge = row.createCell(4);
        cellAge.setCellValue("Age");

        XSSFCell cellPhoneNumber = row.createCell(5);
        cellPhoneNumber.setCellValue("PhoneNumber");

        XSSFCell cellOperator = row.createCell(6);
        cellOperator.setCellValue("Operator");

        int r = 1;
        for (Long key: subscriberMap.keySet()) {//доступ к Map по ключу
            row = sheet.createRow(r);
            cellId = row.createCell(0);
            cellId.setCellValue(subscriberMap.get(key).getId());
            cellFirstName = row.createCell(1);
            cellFirstName.setCellValue(subscriberMap.get(key).getFirstName());
            cellLastName = row.createCell(2);
            cellLastName.setCellValue(subscriberMap.get(key).getLastName());
            cellGender = row.createCell(3);
            cellGender.setCellValue(String.valueOf(subscriberMap.get(key).getGender()));// разобраться ЧТО должен возвращать этот Gender !
            cellAge = row.createCell(4);
            cellAge.setCellValue(subscriberMap.get(key).getAge());
            cellPhoneNumber = row.createCell(5);
            cellPhoneNumber.setCellValue(subscriberMap.get(key).getPhoneNumber());
            cellOperator = row.createCell(6);
            cellOperator.setCellValue(String.valueOf(subscriberMap.get(key).getOperator()));// разобраться ЧТО должен возвращать этот Operator!

          r++;

        }


        try(FileOutputStream out = new FileOutputStream(new File(subscriberExcelDataPath))) {
            workbook.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}