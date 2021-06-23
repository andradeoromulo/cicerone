package com.cicerone.model;

import com.cicerone.util.validation.Validator;

public class Course {

    private String title;
    private String slug;
    private Integer timeToFinishInHours;
    private boolean disabled;
    private String targetAudience;
    private String instructor;
    private String program;
    private String skills;
    private Category category;

    // Required fields only
    public Course(String title, String slug, Integer timeToFinishInHours, String instructor, Category category) {
        Validator.isNotBlankString(title);
        Validator.isValidSlug(slug);
        Validator.isIntegerBetweenRange(timeToFinishInHours, 1, 20);
        Validator.isNotBlankString(instructor);
        Validator.isNotNullObject(category);

        this.title = title;
        this.slug = slug;
        this.timeToFinishInHours = timeToFinishInHours;
        this.instructor = instructor;
        this.category = category;
        this.disabled = true;
    }

    public Course(String title, String slug, Integer timeToFinishInHours, boolean disabled, String targetAudience, String instructor, String program, String skills, Category category) {
        this(title, slug, timeToFinishInHours, instructor, category);
        this.disabled = disabled;
        this.targetAudience = targetAudience;
        this.program = program;
        this.skills = skills;
    }
}