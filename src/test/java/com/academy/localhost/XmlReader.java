//Чтение из xml
package com.academy.localhost;

import com.thoughtworks.xstream.XStream;
import java.io.*;

public class XmlReader {
    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(new FileReader(
        new File("./person.xml")))) {
            StringBuilder xml = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                xml.append(line);
            }

            XStream xStream = new XStream();
            xStream.processAnnotations(Person.class);
            Person person = (Person)xStream.fromXML(xml.toString());

            System.out.println(person);
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }
}

