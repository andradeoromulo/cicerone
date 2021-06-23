package com.cicerone.model;

import com.cicerone.util.validation.Validator;

public class Section {

    private String title;
    private String slug;
    private Integer order;
    private boolean disabled;
    private boolean exam;
    private Course course;

    // Required fields only
    public Section(String title, String slug, Course course) {
        Validator.isNotBlankString(title);
        Validator.isValidSlug(slug);
        Validator.isNotNullObject(course);

        this.title = title;
        this.slug = slug;
        this.course = course;
        this.disabled = true;
    }

}
