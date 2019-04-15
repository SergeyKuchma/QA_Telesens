//Запись в xml
package com.academy.localhost;

import com.thoughtworks.xstream.XStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class XmlWriter {
    public static void main(String[] args) {

        Person person = new Person("Peter", 28);
        XStream xStream = new XStream();
        xStream.processAnnotations(Person.class);

        try (Writer writer = new FileWriter(new File("./person.xml"))) {
            String xml = xStream.toXML(person);
            writer.write(xml);
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }
}

