/*
4) наполнить БД телефонной сети, случайными тестовыми данными:
	a) Наполнить таблицу абонентов (200):
		- имена фамилии взять в соответстсвующих файлах со списко имен/фамилий (женских/мужских);
		- возраст генерировать случайно (от 5 до 90, можно использовать Гауссово распределение для этого диапазона)

 */
package com.academy.lesson13;

import com.academy.lesson11.Gender;
import com.academy.lesson11.Operator;
import com.academy.lesson11.Subscriber;


import java.io.*;
import java.sql.*;
import java.util.*;

public class SubscriberAndJDBCD_v4 {
    private static long startTime = System.currentTimeMillis();
    private static final int COUNT_SUBSCRIBERS=20;
    public static String JDBC_URL="jdbc:mysql://localhost:3306/newschema?user=root&password=root&serverTimezone=UTC&useSSL=false";
    private static String pathMaleFirstNames = "D:\\Telesens\\Lesson11\\мужские имена.txt";
    private static String pathMaleLastNames = "D:\\Telesens\\Lesson11\\мужские фамилии.txt";
    private static String pathFemaleFirstNames = "D:\\Telesens\\Lesson11\\женские имена.txt";
    private static String pathFemaleLastNames = "D:\\Telesens\\Lesson11\\женские фамилии.txt";

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

      //List для наполнения из xls
        List<Subscriber> subscriberList= new ArrayList<>();

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
        for (int i = 0; i < COUNT_SUBSCRIBERS; i++)
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
            subscriberMap.put(subscriber.getId(), subscriber);//это запись в Map
            subscriberList.add(subscriber);//это запись в List
        } //это конец цикла для 200шт

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

        System.out.println("Отсортированный список в виде List:\n" + subscriberList);

        //***********************************************************************************************

// БД
        try {
// Инициализация драйвера
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(JDBC_URL);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO abonent(first_name, last_name, gender, age) VALUES(?, ?, ?, ?)");

            // Добавляем данные Абонентов в таблицу abonent
            for (Subscriber subscriber1: subscriberList) { //Subscriber subscriber : subscriberList
                preparedStatement.setString(1, subscriber1.getFirstName());
                preparedStatement.setString(2, subscriber1.getLastName());

                if (subscriber1.getGender().equals(Gender.MALE))
                    preparedStatement.setString(3, "m");
                else
                    preparedStatement.setString(3, "f");

                preparedStatement.setInt(4, subscriber1.getAge());

                preparedStatement.execute();
            }
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM abonent");

            //здесь надо подсчитать сколько строк в БД
        //    ResultSet resultSet1 = statement.executeQuery("SELECT COUNT(*) FROM (newschema.abonent)");

            int countRow=0; //счетчик для строк

            while(resultSet.next()) {
                long id = resultSet.getLong("abonent_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String gender = resultSet.getString("gender");
                String age = resultSet.getString("age");

                System.out.println(String.format("ID = %d, First Name = %s, Last Name = %s, Gender = %s, Age = %s ",
                        id, firstName, lastName, gender, age));
                countRow++;
  //              System.out.println("Количество строк в БД = " + resultSet1.getInt(1));
            }

             System.out.println("Количество строк в БД = " + countRow);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        float spendTime=System.currentTimeMillis()-startTime;
        System.out.println("программа выполнялась "+spendTime/1000+ " секунд");
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
            while ((row = bufferedReader.readLine()) != null && countOfLines < COUNT_SUBSCRIBERS) {
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
        while (countOfLines < COUNT_SUBSCRIBERS)//дополняем массив до 2000 шт.
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

}