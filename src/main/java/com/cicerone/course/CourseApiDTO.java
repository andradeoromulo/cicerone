package com.cicerone.course;

public record CourseApiDTO(String code, String title, int timeToFinishInHours, String skills) {

    public CourseApiDTO(Course course) {
       this(course.getCode(), course.getTitle(), course.getTimeToFinishInHours(), course.getSkills());
    }

}
