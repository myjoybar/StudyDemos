package com.example.joybar.myaskunagjia.demo.RXJava;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joybar on 1/7/16.
 */
public class Student {

    public String name;
    List<Course> listCourse = new ArrayList<>();

    public List<Course> getListCourse() {
        return listCourse;
    }

    public void setListCourse(List<Course> listCourse) {
        this.listCourse = listCourse;
    }

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




}
