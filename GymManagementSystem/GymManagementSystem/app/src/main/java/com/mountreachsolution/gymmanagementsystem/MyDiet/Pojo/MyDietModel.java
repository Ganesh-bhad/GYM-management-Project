package com.mountreachsolution.gymmanagementsystem.MyDiet.Pojo;

public class MyDietModel {

    String day,meal_time,what_to_eat;

    public MyDietModel(String day, String meal_time, String what_to_eat) {
        this.day = day;
        this.meal_time = meal_time;
        this.what_to_eat = what_to_eat;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMeal_time() {
        return meal_time;
    }

    public void setMeal_time(String meal_time) {
        this.meal_time = meal_time;
    }

    public String getWhat_to_eat() {
        return what_to_eat;
    }

    public void setWhat_to_eat(String what_to_eat) {
        this.what_to_eat = what_to_eat;
    }
}
