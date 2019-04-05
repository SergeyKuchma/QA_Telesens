/*
d) Прочитать subscribers.xlsx в коллекцию Map<Long, Subscriber> и сохранить в текстовый файл: subscribers.txt
		(путь к файлу взять из 'java-part.properties')

e) Прочитать subscribers.xlsx в коллекцию List<Subscriber> отсортировать сразу по 4 полям:

f) Сохранить результат сортировки в файл 'sort-subscribers.txt'
		(путь к файлу взять из 'java-part.properties')


Для Lesson 12:
6) Отсортировать коллекцию лист List<Subscriber>, используя лямбда-выражения:
	a) по полю id
	b) по полямlastName и firstName
	c) по полю age по убыванию

 */
package com.academy.lesson12;

import com.academy.lesson11.Gender;
import com.academy.lesson11.Operator;
import com.academy.lesson11.Subscriber;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.*;

public class SubscriberHomeTask_6 {
    private static String pathMaleFirstNames = "D:\\Telesens\\Lesson11\\мужские имена.txt";
    private static String pathMaleLastNames = "D:\\Telesens\\Lesson11\\мужские фамилии.txt";
    private static String pathFemaleFirstNames = "D:\\Telesens\\Lesson11\\женские имена.txt";
    private static String pathFemaleLastNames = "D:\\Telesens\\Lesson11\\женские фамилии.txt";

    private static String subscriberDataPath = "subscriber.txt";
    private static String subscribersDataPath = "subscribers.txt";
    private static String subscriberDataPath2 = "subscriber-2.txt";

    private static String subscriberMapDataPath = "subscriber-map.txt";
    private static String subscriberExcelDataPath = "subscriber.xlsx";
    private static String subscriberSortTxtPath;
    private static String subscribersArcPath;

    private static String [] arrLifePrefix={"38063","38093","38073"};
    private static String [] arrKievstarPrefix={"38097","38067","38098"};
    private static String [] arrVodafonePrefix={"38050","38066","38095"};

    public static void main(String[] args) {
        // Читаем пути к файлам
        Properties prop = new Properties();

        File file = new File("d:/Java/Program/javapart/java-part.properties");
        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file)) {
                prop.load(fis);

                // Считываем путь к файлу c мужскими именами
                pathMaleFirstNames = prop.getProperty("male.firstnames");
                // Считываем путь к файлу c мужскими фамилиями
                pathMaleLastNames = prop.getProperty("male.lastnames");
                // Считываем путь к файлу c женскими именами
                pathFemaleFirstNames = prop.getProperty("female.firstnames");
                // Считываем путь к файлу c женскими фамилиями
                pathFemaleLastNames = prop.getProperty("female.lastnames");
                // Начальный возраст
                Integer ageFrom = Integer.parseInt(prop.getProperty("age.from"));
                // Конечный возраст
                Integer ageTo = Integer.parseInt(prop.getProperty("age.to"));
                // Получаем путь к Excel файлу для записи данных абонентов
                subscriberExcelDataPath = prop.getProperty("subscriber.exc");
                // Количество Абонентов
                Integer сountSubscribers = Integer.parseInt(prop.getProperty("subscribers.count"));
                // Считываем путь к текстовому файлу
                subscriberDataPath = prop.getProperty("subscriber.txt");
                // Считываем путь к текстовому файлу, в который будет записам отсортированный List
                subscriberSortTxtPath = prop.getProperty("subscriber.sort.txt");
                // Получаем путь к архиву subscribers.zip
                subscribersArcPath = prop.getProperty("subscriber.arc");

            } catch (Exception e) {
               e.printStackTrace();
                System.out.println(e.getMessage());
            }
        } else
            System.out.println("Файл java-part.properties отсутствует в директории: D:\\Java\\Program\\javapart\\java-part.properties");



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

      //List для наполнения из xls
        List<Subscriber> subscriberList= new ArrayList<>();


        // ----------------------------------  читаем из excel   ----------------------------------------

        try(XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(subscriberExcelDataPath))){
            XSSFSheet sheet = workbook.getSheet("Subscribers");
            for (int r=1; r<=sheet.getLastRowNum(); r++){
                subscriber=new Subscriber();

                XSSFRow row = sheet.getRow(r);
                subscriber.setId((long) row.getCell(0).getNumericCellValue());
                subscriber.setFirstName(row.getCell(1).getStringCellValue());
                subscriber.setLastName(row.getCell(2).getStringCellValue());

                //Считываем Пол как String
                String strGender=row.getCell(3).getStringCellValue();
                if (strGender.equals("м")) subscriber.setGender(Gender.MALE);
                else subscriber.setGender(Gender.FEMALE); //если не="м", то ставим Ж! - т.е. нет проверки на ошибку

                subscriber.setAge((int)row.getCell(4).getNumericCellValue());
                subscriber.setPhoneNumber(row.getCell(5).getStringCellValue());

                //Считываем оператора как String
                String strOperatorName=row.getCell(6).getStringCellValue();
                if (strOperatorName.equals("Vodafone")) subscriber.setOperator(operatorVodafone);
                else if (strOperatorName.equals("Kievstar")) subscriber.setOperator(operatorKievstar);
                    else subscriber.setOperator(operatorLife); // в противном случае все будут Life

                subscriberMap.put((long)(r-1),subscriber); //это если записывать в MAP
                subscriberList.add(subscriber);//это запись в List
            }
        } catch (Exception e){
            e.printStackTrace();
        }

      //  System.out.println("Считанные данные из XLS в виде List:" + subscriberList);
      //  System.out.println("Считанные данные из XLS в виде MAP:" + subscriberMap);


        // Пишем subscriberMap в текстовый файл
        try (PrintWriter priWMap = new PrintWriter(subscribersDataPath)) {
            priWMap.println(subscriberMap);
        } catch (Exception e) {
              e.printStackTrace();
            System.out.println(e.getMessage());
        }
        System.out.println("Считанные данные из XLS в виде MAP были записаны в " + subscribersDataPath);

//*******************************************************************************************************

/*
        Для Lesson 12:
        6) Отсортировать коллекцию лист List<Subscriber>, используя лямбда-выражения:
        a) по полю id
        b) по полямlastName и firstName
        c) по полю age по убыванию
*/
//*******************************************************************************************************
        subscriberList.sort((o1, o2)-> {
            if (o1.getId()==(o2.getId()))
                    //когда по ID они равны, то смотрим на LastName
                    if (o1.getLastName().equals(o2.getLastName())) //это если LastName равны
                        if (o1.getFirstName().equals(o2.getFirstName()))// если по Имени они равны, то смотрим на Age
                            return Integer.compare(o2.getAge(), o1.getAge());  // сортировка по Age в обратном порядке
                        else return o1.getFirstName().compareTo(o2.getFirstName());// если по Имени они не равны
                    else return o1.getLastName().compareTo(o2.getLastName());// если по Фамилии они не равны
            else //это если Id не одинаковый, то сортируем по нему:
                return Long.compare(o1.getId(), o2.getId());
            });

          System.out.println("Отсортированный список из XLS в виде List:\n" + subscriberList);

  //***********************************************************************************************
/*
          // Пишем отсортированный List в файл txt
        try (PrintWriter priWMap = new PrintWriter(subscriberSortTxtPath)) {
            priWMap.println(subscriberList);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        System.out.println("Отсортированный список из XLS в виде List был записан в :" + subscriberSortTxtPath);
*/

    } // это конец main

}