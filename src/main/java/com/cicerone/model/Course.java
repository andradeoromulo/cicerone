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
    private Subcategory subcategory;

    // Required fields only
    public Course(String title, String slug, Integer timeToFinishInHours, String instructor, Subcategory subcategory) {
        Validator.isNotBlankString(title);
        Validator.isValidSlug(slug);
        Validator.isIntegerBetweenRange(timeToFinishInHours, 1, 20);
        Validator.isNotBlankString(instructor);
        Validator.isNotNullObject(subcategory);

        this.title = title;
        this.slug = slug;
        this.timeToFinishInHours = timeToFinishInHours;
        this.instructor = instructor;
        this.subcategory = subcategory;
        this.disabled = true;

        subcategory.addCourse(this);
    }

    public Course(String title, String slug, Integer timeToFinishInHours, boolean disabled, String targetAudience, String instructor, String program, String skills, Subcategory subcategory) {
        this(title, slug, timeToFinishInHours, instructor, subcategory);
        this.disabled = disabled;
        this.targetAudience = targetAudience;
        this.program = program;
        this.skills = skills;
    }

    public String getSubcategorySlug() {
        return this.subcategory.getSlug();
    }

    public Integer getTimeToFinishInHours() {
        return timeToFinishInHours;
    }

    public String getTitle() {
        return title;
    }
}