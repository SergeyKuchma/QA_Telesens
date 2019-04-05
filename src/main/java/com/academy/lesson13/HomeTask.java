package com.academy.lesson13;

import com.academy.lesson11.Subscriber;
import com.academy.lesson11.Gender;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HomeTask {
    private static long startTime = System.currentTimeMillis();
    private static final int COUNT_SUBSCRIBERS=20;
    public static String JDBC_URL="jdbc:mysql://localhost:3306/newschema?user=root&password=root&serverTimezone=UTC&useSSL=false";
    private static final String MAN_FIRST_NAMES_PATH="D:\\Telesens\\Lesson11\\мужские имена.txt";
    private static final String WOMAN_FIRST_NAMES_PATH="D:\\Telesens\\Lesson11\\женские имена.txt";
    private static final String MAN_LAST_NAMES_PATH="D:\\Telesens\\Lesson11\\мужские фамилии.txt";
    private static final String WOMAN_LAST_NAMES_PATH="D:\\Telesens\\Lesson11\\женские фамилии.txt";
    private static List<String> manFirstNames = new ArrayList<>();
       private static List<String> womanFirstNames = new ArrayList<>();
       private static List<String> manLastNames = new ArrayList<>();
       private static List<String> womanLastNames = new ArrayList<>();

       public static void main(String[] args) {
           // Подготавливаем данные
           manFirstNames = prepareData(MAN_FIRST_NAMES_PATH);
           womanFirstNames = prepareData(WOMAN_FIRST_NAMES_PATH);
           manLastNames = prepareData(MAN_LAST_NAMES_PATH);
           womanLastNames = prepareData(WOMAN_LAST_NAMES_PATH);

           Random random = new Random();

           //цикл наполнения БД данными
           for (int n = 1; n <= COUNT_SUBSCRIBERS; n++) {
               Subscriber subscriber = generateNextSubscriber(random);
               insertSubscriberToDB(subscriber);
           }
           float spendTime=System.currentTimeMillis()-startTime;
           System.out.println("программа выполнялась "+spendTime/1000+ " секунд");
       } //конец main


    //считывание данных из файла и запись их в List
       private static List<String> prepareData(String path) {
           List<String> result = new ArrayList<>();
           try(BufferedReader bfr = new BufferedReader(new FileReader(path))) {
               String row;
               while ((row = bfr.readLine()) != null) {
                   result.add(row);
               }
           } catch (IOException e) {
               e.printStackTrace();
           }
           return result;
       }


       // TODO - запись по 1 строке в БД
       private static void insertSubscriberToDB(Subscriber subscriber) {
          // System.out.print(subscriber);
           // БД
           try {
// Инициализация драйвера
               Class.forName("com.mysql.cj.jdbc.Driver");
               Connection connection = DriverManager.getConnection(JDBC_URL);

               PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO abonent(first_name, last_name, gender, age) VALUES(?, ?, ?, ?)");

               // Добавляем данные Абонента в таблицу abonent
               //for (Subscriber subscriber1: subscriber) { //Subscriber subscriber : subscriberList

                   preparedStatement.setString(1, subscriber.getFirstName());
                   preparedStatement.setString(2, subscriber.getLastName());

                   if (subscriber.getGender().equals(Gender.MALE))
                       preparedStatement.setString(3, "m");
                   else
                       preparedStatement.setString(3, "f");

                   preparedStatement.setInt(4, subscriber.getAge());

                   preparedStatement.execute();
               //}
               Statement statement = connection.createStatement();
               ResultSet resultSet = statement.executeQuery("SELECT * FROM abonent");


               while(resultSet.next()) {
                   long id = resultSet.getLong("abonent_id");
                   String firstName = resultSet.getString("first_name");
                   String lastName = resultSet.getString("last_name");
                   String gender = resultSet.getString("gender");
                   String age = resultSet.getString("age");

                   System.out.println(String.format("ID = %d, First Name = %s, Last Name = %s, Gender = %s, Age = %s ",
                           id, firstName, lastName, gender, age));
               }


           } catch (ClassNotFoundException | SQLException e) {
               e.printStackTrace();
           }
       }

       // создание
       private static Subscriber generateNextSubscriber(Random random) {

           Subscriber subscriber = new Subscriber();
           // Наполняем
           String firstName;
           String lastName;
           int nextIndex;
           if (random.nextBoolean()) {
               // допустим мужчина
               nextIndex = random.nextInt(manFirstNames.size());
               firstName = manFirstNames.get(nextIndex);
               nextIndex = random.nextInt(manLastNames.size());
               lastName = manLastNames.get(nextIndex);
               subscriber.setGender(Gender.MALE);
           } else {
               // женщина
               nextIndex = random.nextInt(womanFirstNames.size());
               firstName = womanFirstNames.get(nextIndex);
               nextIndex = random.nextInt(womanLastNames.size());
               lastName = womanLastNames.get(nextIndex);
               subscriber.setGender(Gender.FEMALE);
           }
           subscriber.setFirstName(firstName);
           subscriber.setLastName(lastName);
           // ?? Гауссово
           subscriber.setAge(random.nextInt(86)+5);
           return subscriber;
       }


   }