package com.academy.lesson08.ex;
import java.util.Scanner;


public class Date {
    private int day;
    private int month;
    private int year;

    Scanner scanner = new Scanner(System.in);

    public Date() { //инициализируем поля по умолчанию значениями
        this.day = 1;
        this.month = 1;
        this.year = 1970;
    }

    public Date(int day, int month, int year) {
        setYear(year);
        setMonth(month);
        setDay(day);

    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    private int getCountOfDay()
    {int countOfDay = 0; //максимальное кол-во дней в месяце

        switch(getMonth()) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                countOfDay = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                countOfDay = 30;
                break;
            case 2:
                if (((getYear() % 4 == 0) && !(getYear() % 100 == 0)) || (getYear() % 400 == 0))
                    countOfDay = 29;
                else
                    countOfDay = 28;
                break;
            default:
                System.out.println("Несуществующий месяц");
                break;
        }


        return countOfDay;
    }




    public void setDay(int day) {
        
            while(!(day >= 1 && day <= getCountOfDay())){ // цикл пока НЕ подходит в границы для месяца

                System.out.println("День месяца должен находиться в диапазоне от 1 до "+getCountOfDay()+ " а вы ввели "+day);
                System.out.print("Введите корректный день месяца:");
                day = scanner.nextInt();
            }
         //   День месяца в корректном диапазоне, с учетом месяца и высокостного года
            this.day = day;
        }


    public void setMonth(int month) {
        while(!(month>=1 && month<=12)){ // цикл пока НЕ подходит в границы для месяца [1-12]
            System.out.println("Месяц должен находиться в диапазоне от 1 до 12, а вы ввели "+month);
            System.out.print("Введите корректно номер месяца в формате [XX]:");
            month = scanner.nextInt();
            }
        this.month = month;
        }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return String.format("%02d/%02d/%d", day, month, year);
        // return day + "/" + month + "/" + year;
    }

    // withXXX
    public Date withDay(int day) {
        setDay(day);
      //  this.day = day;
        return this;
    }

    public Date withMonth(int month) {
        setMonth(month);
        //this.month = month;
        return this;
    }

    public Date withYear(int year) {
        setYear(year);
        //this.year = year;
        return this;
    }

}
