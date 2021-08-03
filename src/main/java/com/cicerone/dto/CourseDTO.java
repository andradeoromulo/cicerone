package com.cicerone.dto;

import com.cicerone.model.Course;

public record CourseDTO(String code, String title, int timeToFinishInHours, String skills) {

    public CourseDTO(Course course) {
       this(course.getCode(), course.getTitle(), course.getTimeToFinishInHours(), course.getSkills());
    }

}
