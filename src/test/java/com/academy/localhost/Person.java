package com.academy.localhost;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("person")
public class Person {
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private char gender;
    private String name;



    public Person(String name, int age) {
        this.name = name;
        this.age = age;    }

    public Person(int id, String firstName, String lastName, int age, char gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.name = name;
    }

    @Override
public String toString() {
        return "Person{" +"name='" + name + '\'' +", age=" + age +'}';
    }
}
