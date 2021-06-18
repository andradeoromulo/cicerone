package com.cicerone.model;

import com.cicerone.utils.Validation;

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
    public Course(String title, String slug, Integer timeToFinishInHours, String instructor, Category subcategory) {
        Validation.isNotBlankString(title);
        Validation.isValidSlug(slug);
        Validation.isIntegerBetweenRange(timeToFinishInHours, 1, 20);
        Validation.isNotBlankString(instructor);
        Validation.isNotNullObject(subcategory);

        this.title = title;
        this.slug = slug;
        this.timeToFinishInHours = timeToFinishInHours;
        this.instructor = instructor;
        this.category = subcategory;
        this.disabled = true;
    }

}