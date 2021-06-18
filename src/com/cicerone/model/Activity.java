package com.cicerone.model;

import com.cicerone.utils.Validation;

public abstract class Activity {

    private String title;
    private String slug;
    private boolean disabled;
    private Integer order;
    private Section section;

    // Required fields only
    public Activity(String title, String slug, Section section) {
        Validation.isNotBlankString(title);
        Validation.isValidSlug(slug);
        Validation.isNotNullObject(section);

        this.title = title;
        this.slug = slug;
        this.section = section;
        this.disabled = true;
    }

}
