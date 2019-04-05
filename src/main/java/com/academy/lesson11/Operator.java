package com.academy.lesson11;

public class Operator {
    private long id;
    private String name;
    private String[] arrPrefix;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrefix(String[] arrPrefix) {
        this.arrPrefix = arrPrefix;}

    public String getPrefix(int id) {
        return arrPrefix[id];}

    @Override
    public String toString() {
        return name;
    }


}