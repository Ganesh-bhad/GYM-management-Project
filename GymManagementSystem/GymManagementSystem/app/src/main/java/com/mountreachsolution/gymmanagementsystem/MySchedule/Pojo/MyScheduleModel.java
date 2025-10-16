package com.mountreachsolution.gymmanagementsystem.MySchedule.Pojo;

public class MyScheduleModel {

    String id,day,workout_title,workout_details;

    public MyScheduleModel(String id, String day, String workout_title, String workout_details) {
        this.id = id;
        this.day = day;
        this.workout_title = workout_title;
        this.workout_details = workout_details;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getWorkout_title() {
        return workout_title;
    }

    public void setWorkout_title(String workout_title) {
        this.workout_title = workout_title;
    }

    public String getWorkout_details() {
        return workout_details;
    }

    public void setWorkout_details(String workout_details) {
        this.workout_details = workout_details;
    }
}
