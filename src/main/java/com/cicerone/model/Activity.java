package com.cicerone.model;

import com.cicerone.util.validation.Validator;

public abstract class Activity {

    private String title;
    private String slug;
    private boolean disabled;
    private Integer order;
    private Section section;

    // Required fields only
    public Activity(String title, String slug, Section section) {
        Validator.isNotBlankString(title);
        Validator.isValidSlug(slug);
        Validator.isNotNullObject(section);

        this.title = title;
        this.slug = slug;
        this.section = section;
        this.disabled = true;
    }

}
