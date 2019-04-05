/*
2) Написать Java приложение, которое наполнит файл subscribers.xlsx, случайными данными:
	(путь к файлу взять из 'java-part.properties')
	a) Наполнить таблицу абонентов excel(2000 строк):
		- имена фамилии взять в соответстсвующих файлах (см. 'java-part.properties') со списком имен/фамилий (женских/мужских);
		- возраст генерировать случайно от 5 до 90 (можно использовать Гауссово распределение для этого диапазона)
			(диапазон брать из файла 'java-part.properties')

	b)  Телефонные номера для каждого оператора со следующими префиксами:
		- Life номера с префиксами: 38063*******, 38093*******, 38073*******
		- Kievstar номера с префиксами: 38097*******, 38067*******, 38098*******
		- Vodafone номера с префиксами: 38050*******, 38066*******, 38095*******

	Результат subscribers.xlsx должен выглядеть так:
		   1 | Васильев  | Иван | м | 23 | 380630025465 | Life
		   2 | Петрова   | Катя | ж | 34 | 380670058694 | Kievstar
		...
		2000 | Борисов   | Коля | м | 48 | 380500025465 | Vodafone
		Всего 2000 случайных строк

	с) Реализовать класс Subscriber с приватными полями:
		- private Long id;
		- private String firstName;
		- private String lastName;
		- private Gender gender; // создать перечисление Gender для мужского и женского пола
		- private int age;
		- private String phoneNumber;
		- private String operator;

 */
package com.academy.lesson11;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

public class SubscriberHomeTask_v2 {
    private static String pathMaleFirstNames = "D:\\Telesens\\Lesson11\\мужские имена.txt";
    private static String pathMaleLastNames = "D:\\Telesens\\Lesson11\\мужские фамилии.txt";
    private static String pathFemaleFirstNames = "D:\\Telesens\\Lesson11\\женские имена.txt";
    private static String pathFemaleLastNames = "D:\\Telesens\\Lesson11\\женские фамилии.txt";

    private static String subscriberDataPath = "subscriber.txt";
    private static String subscriberDataPath2 = "subscriber-2.txt";

    private static String subscriberMapDataPath = "subscriber-map.txt";
    private static String subscriberExcelDataPath = "subscriber.xlsx";

    private static String [] arrLifePrefix={"38063","38093","38073"};
    private static String [] arrKievstarPrefix={"38097","38067","38098"};
    private static String [] arrVodafonePrefix={"38050","38066","38095"};

    public static void main(String[] args) {
        // Operators
        Operator operatorLife = new Operator();
        operatorLife.setId(1L);
        operatorLife.setName("Life");
        operatorLife.setPrefix(arrLifePrefix);


        Operator operatorKievstar = new Operator();
        operatorKievstar.setId(2L);
        operatorKievstar.setName("Kievstar");
        operatorKievstar.setPrefix(arrKievstarPrefix);

        Operator operatorVodafone = new Operator();
        operatorVodafone.setId(3L);
        operatorVodafone.setName("Vodafone");
        operatorVodafone.setPrefix(arrVodafonePrefix);

        // Subscribers
        Subscriber subscriber = new Subscriber();

      // => MAP
        Map<Long, Subscriber> subscriberMap = new HashMap<>();

        List<String> listMaleFirstNames= new ArrayList<>();
        List<String> listMaleLastNames= new ArrayList<>();
        List<String> listFemaleFirstNames= new ArrayList<>();
        List<String> listFemaleLastNames= new ArrayList<>();

      //получаем список мужских Имен
        listMaleFirstNames=getListFromFile(pathMaleFirstNames);

        //получаем список мужских Фамилий
        listMaleLastNames=getListFromFile(pathMaleLastNames);

        //получаем список женских Имен
        listFemaleFirstNames=getListFromFile(pathFemaleFirstNames);

        //получаем список женских Фамилий
        listFemaleLastNames=getListFromFile(pathFemaleLastNames);


//заполняем Map случайными данными
        Random random= new Random();
        for (int i = 0; i < 2000; i++)
        {
            subscriber = new Subscriber(); //создаем нового subscriber
            subscriber.setId(i);
            subscriber.setAge(random.nextInt(86)+5);// Age - случайное число от 5 до 90

            int idOperatorCurrent=random.nextInt(3);// выбираем случайным образом оператора
            if (idOperatorCurrent==0) { // для Киевстар
                subscriber.setOperator(operatorKievstar);
                //выбираем случайным образом префикс для конкретного оператора и добавляем 7 случайных цифр
                subscriber.setPhoneNumber(operatorKievstar.getPrefix(random.nextInt(arrKievstarPrefix.length))+getRandomPhone());
            } else if(idOperatorCurrent==1){ //для Лайф
                subscriber.setOperator(operatorLife);
                subscriber.setPhoneNumber(operatorLife.getPrefix(random.nextInt(arrLifePrefix.length))+getRandomPhone());
            }
            else {
                subscriber.setOperator(operatorVodafone);//для Водафона
                subscriber.setPhoneNumber(operatorVodafone.getPrefix(random.nextInt(arrVodafonePrefix.length))+getRandomPhone());
        }

    //заполняем Gender и соответсвенно ФИО мужчин или женщин
            if (random.nextInt(2)==1) { // для мужчин
                subscriber.setFirstName(listMaleFirstNames.get(i));
                subscriber.setLastName(listMaleLastNames.get(i));
                subscriber.setGender(Gender.MALE);
            } else
                {// для женщин
                subscriber.setFirstName(listFemaleFirstNames.get(i));
                subscriber.setLastName(listFemaleLastNames.get(i));
                subscriber.setGender(Gender.FEMALE);
                }

            // --------- Наполняем MAP для занесения его в ЭКСЕЛЬ ----------
              subscriberMap.put(subscriber.getId(), subscriber);
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
        System.out.println("Данные добавлены в "+subscriberExcelDataPath);
    } // это конец main



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


    //возвращает 7 случайных цифр для телефона
    public static String getRandomPhone() {
        Random random = new Random();
        String tempStr = "";
        for (int i = 0; i < 7; i++) tempStr += random.nextInt(10);
        return tempStr;
    }

    /* эта функция больше не используется, т.к. я добавил в класс Operator этот метод
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
    */

}