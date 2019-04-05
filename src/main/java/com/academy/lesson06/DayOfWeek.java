/*
1)  Перечисление для представления дней недели
		Реализовать перечисление "День недели": Понедельник, Вторник, ...
		В перечислении "День недели" добавить функции получения дня "позавчера" и "послезавтра".
		Протестировать перечисление в функции main() тестового класса.
 */
package com.academy.lesson06;

public enum DayOfWeek {
    SUNDAY("Воскресенье"),
    MONDAY ("Понедельник"),
    TUESDAY("Вторник"),
    WEDNESDAY("Среда"),
    THURSDAY("Четверг"),
    FRIDAY("Пятница"),
    SATURDAY("Суббота");

    private String currentDay;

    DayOfWeek(String currentDay){
        this.currentDay = currentDay;
    }

    String getDayOfWeek() {
        return currentDay;
    }


    DayOfWeek next(){
        DayOfWeek day = values()[(ordinal() + 1) % values().length];
        return day;
    }

    @Override
    public String toString() {
        return name() + " " + ordinal();
    }

    boolean isWeekend() {
        switch(this) {
            case SATURDAY:
            case SUNDAY:
            return true;
            default:
                return false;
        }
    }



    static void printAll() {
        for (DayOfWeek d : values())
            System.out.println(d.getDayOfWeek());//выводит русские названия дней
    }
}
