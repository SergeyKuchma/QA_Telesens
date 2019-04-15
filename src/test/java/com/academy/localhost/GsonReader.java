package com.academy.localhost;

import com.google.gson.Gson;
import java.io.*;

public class GsonReader {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(
                new FileReader(new File("./person.json")))) {
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }
            Gson gson = new Gson();
            Person person = gson.fromJson(json.toString(), Person.class);

            System.out.println(person);
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }
}