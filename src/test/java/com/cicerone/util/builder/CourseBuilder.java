package com.cicerone.util.builder;

import com.cicerone.model.Category;
import com.cicerone.model.Course;
import com.cicerone.model.Subcategory;

public class CourseBuilder {
    Course course;

    public CourseBuilder(String title, String code, Subcategory subcategory) {
        this.course = new Course(title, code, 8, "Generic Instructor", subcategory);
    }

    public CourseBuilder withTimeToFinishInHours(int totalTime) {
        this.course.setTimeToFinishInHours(totalTime);
        return this;
    }

    public CourseBuilder withInstructor(String instructorName) {
        this.course.setInstructor(instructorName);
        return this;
    }

    public CourseBuilder enabled() {
        this.course.setDisabled(false);
        return this;
    }

    public CourseBuilder disabled() {
        this.course.setDisabled(true);
        return this;
    }

    public Course build() {
        return this.course;
    }

}
