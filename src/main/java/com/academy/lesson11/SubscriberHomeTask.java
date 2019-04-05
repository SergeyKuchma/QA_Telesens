package com.academy.lesson11;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

public class SubscriberHomeTask {
    private static String pathMaleFirstNames = "D:\\Telesens\\Lesson11\\мужские имена.txt";
    private static String pathMaleLastNames = "D:\\Telesens\\Lesson11\\мужские фамилии.txt";
    private static String pathFemaleFirstNames = "D:\\Telesens\\Lesson11\\женские имена.txt";
    private static String pathFemaleLastNames = "D:\\Telesens\\Lesson11\\женские фамилии.txt";

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

 /*       // => на консоль
        subscribers.add(subscriber1);
        subscribers.add(subscriber2);
        System.out.println(subscribers);

  */      // => MAP
        System.out.println("=======MAP======");
        Map<Long, Subscriber> subscriberMap = new HashMap<>();


//        subscriberMap.put(1L, subscriber1);
//        subscriberMap.put(subscriber1.getId(), subscriber1);
//        subscriberMap.put(subscriber2.getId(), subscriber2);

  //      subscriberMap.put((long) 1, subscriber2);

    //    System.out.println("это после вставки subscriber2 по индексу=1");
      //  System.out.println(subscriberMap);

        List<String> listGenders= new ArrayList<>();
        List<String> listMaleFirstNames= new ArrayList<>();
        List<String> listMaleLastNames= new ArrayList<>();
        List<String> listFemaleFirstNames= new ArrayList<>();
        List<String> listFemaleLastNames= new ArrayList<>();


        // наполняем список Gender случайным образом
        Random random= new Random();
        for (int i = 0; i < 2000; i++)
            {
                if (random.nextInt(2)==1) listGenders.add(i, String.valueOf(Gender.MALE));
                else listGenders.add(i, String.valueOf(Gender.FEMALE));
        }
//        System.out.println("готовый список Gender:"+listGenders);

        //получаем список мужских Имен
        listMaleFirstNames=getListFromFile(pathMaleFirstNames);

        //получаем список мужских Фамилий
        listMaleLastNames=getListFromFile(pathMaleLastNames);

        //получаем список женских Имен
        listFemaleFirstNames=getListFromFile(pathFemaleFirstNames);

        //получаем список женских Фамилий
        listFemaleLastNames=getListFromFile(pathFemaleLastNames);

//заполняем Map случайными данными
        for (int i = 0; i < 2000; i++)
        {
            subscriber1 = new Subscriber(); //создаем нового subscriber
            subscriber1.setId(i);
            subscriber1.setAge(random.nextInt(86)+5);// Age - случайное число от 5 до 90

            String str=getRandomOperator();
            subscriber1.setPhoneNumber(getRandomOperatorPrefix(str)+getRandomPhone()); // заменить эту строку на случаный телефон с учетом оператора



///------------------------------------------------------------
///------------------------------------------------------------
///------------------------------------------------------------

            ///эта строка требует преобразования стринг в Operator!!!!
            //subscriber1.setOperator(str);// заменить эту строку на зависимость от кода оператора

///------------------------------------------------------------
///------------------------------------------------------------
///------------------------------------------------------------



            if (random.nextInt(2)==1) listGenders.add(i, String.valueOf(Gender.MALE));
            else listGenders.add(i, String.valueOf(Gender.FEMALE));



            if (listGenders.get(i).equals("м")) { // для мужчин
                subscriber1.setFirstName(listMaleFirstNames.get(i));
                subscriber1.setLastName(listMaleLastNames.get(i));
                subscriber1.setGender(Gender.MALE);
            } else
                {// для женщин
                subscriber1.setFirstName(listFemaleFirstNames.get(i));
                subscriber1.setLastName(listFemaleLastNames.get(i));
                subscriber1.setGender(Gender.FEMALE);
                }

         //   System.out.println(subscriber1);
//            subscribers.add(subscriber1);


            // --------- Здесь надо создать MAP перед занесением всего в ЭКСЕЛЬ!!!
              subscriberMap.put(subscriber1.getId(), subscriber1);
        } //это конец цикла для 2000шт


   //     System.out.println("Готовый MAP по ключам");
   //     for (Long key: subscriberMap.keySet())
   //         System.out.println(subscriberMap.get(key)); // норм


        // ----------------------------------  Пишем в excel   ----------------------------------------

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

    } // это конец main - НЕ УДАЛИТЬ!!!!



// считывает данные из файла и создает List размером в 2000 записей
    public static List<String> getListFromFile(String pathMaleFirstNames) {
        int countOfLines = 0; //счетчик строк в файле
        List<String> listTemp = new ArrayList<>();

        // читаем из файла
    //    System.out.println("Читаем из файла");
        String row;

        try (FileReader fileReader = new FileReader(pathMaleFirstNames);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            // перебираем все строки
            while ((row = bufferedReader.readLine()) != null && countOfLines < 2000) {
                if (row.isEmpty())
                    System.out.println("---empty---");
                else
                    listTemp.add(row); // наполняем промежуточный List
                countOfLines++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
      //  System.out.println("Количество элементов =" + countOfLines);
      //  System.out.println("Считанные данные:" + listTemp);

        int i = 0;
        while (countOfLines < 2000)//дополняем массив до 2000 шт.
        {
            listTemp.add(listTemp.get(i));
            i++;
            countOfLines++;
        }
        return listTemp;
    }


    //возвращает 7 случайных цифр для телефона для оператора
    public static String getRandomPhone() {
        Random random = new Random();
        String tempStr = "";
        for (int i = 0; i < 7; i++) tempStr += random.nextInt(10);
        //System.out.println("случайные 7 цифр телефона" + tempStr);
        return tempStr;
    }

    //возвращает случайного оператора
    public static String getRandomOperator() {
        Random random = new Random();
        String[] arrOperator = {"operatorLife", "operatorVodafone", "operatorKievstar"};
        return arrOperator[random.nextInt(3)];
    }


    //возвращает префикс для оператора
    public static String getRandomOperatorPrefix(String operatorName) {
        Random random = new Random();
        String tempStr = "";
        String [] arrPrefLife={"38063","38093","38073"};
        String [] arrPrefVodafone={"38050","38066","38095"};
        String [] arrPrefKievstar={"38097","38067","38098"};

        switch (operatorName) {
            case "operatorLife":
                tempStr=arrPrefLife[random.nextInt(3)];//- Life номера с префиксами: 38063*******, 38093*******, 38073*******
                break;
            case "operatorVodafone":
                tempStr=arrPrefVodafone[random.nextInt(3)];//- Vodafone номера с префиксами: 38050*******, 38066*******, 38095*******
                break;
            case "operatorKievstar":
                tempStr=arrPrefKievstar[random.nextInt(3)];//- Kievstar номера с префиксами: 38097*******, 38067*******, 38098*******
                break;
            default:
                break;
        }
        return tempStr;
    }

}