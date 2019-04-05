package com.academy.lesson13;

import java.sql.*;

public class JDBCDemo {
    public static String JDBC_URL="jdbc:mysql://localhost:3306/newschema?user=root&password=root&serverTimezone=UTC&useSSL=false";
    public static void main(String[] args) {
        System.out.println("JDBC");

        try {
// Инициализация драйвера
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(JDBC_URL);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO abonent VALUES(?, ?, ?, ?, ?)");
            preparedStatement.setLong(1, 4L);
            preparedStatement.setString(2, "Наталья");
            preparedStatement.setString(3, "Ильинская");
            preparedStatement.setString(4, "f");
            preparedStatement.setInt(5, 27);
            preparedStatement.execute();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM abonent");

            // вставляем строку в БД
            // statement.execute("INSERT INTO abonent VALUES(3,'Николай', 'Ильченко','m',99)");
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
}
