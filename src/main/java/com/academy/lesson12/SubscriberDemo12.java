package com.academy.lesson12;
import com.academy.lesson11.Gender;
import com.academy.lesson11.Operator;
import com.academy.lesson11.Subscriber;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

public class SubscriberDemo12 {
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

        Subscriber subscriber3 = new Subscriber();
        subscriber3.setId(33L);
        subscriber3.setFirstName("Николай");
        subscriber3.setLastName("Поликарпов");
        subscriber3.setGender(Gender.MALE);
        subscriber3.setAge(32);
        subscriber3.setPhoneNumber("380630025999");
        subscriber3.setOperator(operatorLife);

        Subscriber subscriber4 = new Subscriber();
        subscriber4.setId(24L);
        subscriber4.setFirstName("Илона");
        subscriber4.setLastName("Сидорова");
        subscriber4.setGender(Gender.FEMALE);
        subscriber4.setAge(41);
        subscriber4.setPhoneNumber("380670058111");
        subscriber4.setOperator(operatorKievstar);

        List<Subscriber> subscribers = new ArrayList<>();

        // => на консоль
        subscribers.add(subscriber1);
        subscribers.add(subscriber2);
        subscribers.add(subscriber3);
        subscribers.add(subscriber4);
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

        // Сделаем сортировку по имени
//        subscribers.sort(new ComparatorByAgeImpl());
//        subscribers.sort(new ComparatorByFirstNameImpl());

        // Громоздко через анонимный класс
//        subscribers.sort(new Comparator<Subscriber>() {
//            @Override
//            public int compare(Subscriber o1, Subscriber o2) {
//                return Long.compare(o2.getId(), o1.getId());
//            }
//        });

        // Коротко через лямбды
        subscribers.sort((o1, o2) -> Long.compare(o1.getId(), o2.getId()));

        // Супер-Коротко через ссылку на метод
        subscribers.sort(Comparator.comparingLong(Subscriber::getId));
//        subscribers.sort(new ComparatorComplexImpl());
        subscribers.sort((o1, o2)-> {
            if (o1.getOperator().getName().equals(o2.getOperator().getName())){
                return Integer.compare(o2.getAge(), o1.getAge());
            } else {
                return o1.getOperator().getName().compareTo(o2.getOperator().getName());
            }});

        // Пишем в excel
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Subscribers");
        for (int i = 0; i < subscribers.size(); i++) {
            Subscriber subscriber = subscribers.get(i);

            XSSFRow row = sheet.createRow(i);

            XSSFCell cellId = row.createCell(0);
            cellId.setCellValue(subscriber.getId());

            XSSFCell cellLastName = row.createCell(1);
            cellLastName.setCellValue(subscriber.getLastName());

            XSSFCell cellFirstName = row.createCell(2);
            cellFirstName.setCellValue(subscriber.getFirstName());

            XSSFCell cellGender = row.createCell(3);
            cellGender.setCellValue(subscriber.getGender().toString());

            XSSFCell cellAge = row.createCell(4);
            cellAge.setCellValue(subscriber.getAge());

            XSSFCell cellPhoneNumber = row.createCell(5);
            cellPhoneNumber.setCellValue(subscriber.getPhoneNumber());

            XSSFCell cellOperatorName = row.createCell(6);
            cellOperatorName.setCellValue(subscriber.getOperator().getName());
        }

        try(FileOutputStream out = new FileOutputStream(new File(subscriberExcelDataPath))) {
            workbook.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}