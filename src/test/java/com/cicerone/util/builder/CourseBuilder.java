package com.cicerone.util.builder;

import com.cicerone.model.Course;
import com.cicerone.model.Subcategory;

public class CourseBuilder {
    private String title;
    private String code;
    private Subcategory subcategory;
    private boolean disabled;
    private int timeToFinishInHours;
    private String instructor;

    public CourseBuilder(String title, String code, Subcategory subcategory) {
       this.title = title;
       this.code = code;
       this.subcategory = subcategory;
       this.timeToFinishInHours = 8;
       this.instructor = "João da Silva";
    }

    public CourseBuilder withTimeToFinishInHours(int totalTime) {
        this.timeToFinishInHours = totalTime;
        return this;
    }

    public CourseBuilder withInstructor(String instructorName) {
        this.instructor = instructorName;
        return this;
    }

    public CourseBuilder enabled() {
        this.disabled = false;
        return this;
    }

    public CourseBuilder disabled() {
        this.disabled = true;
        return this;
    }

    public Course build() {
        Course course = new Course(title, code, timeToFinishInHours, instructor, subcategory);
        if(disabled == false) course.setDisabled(false);
        return course;
    }

}
