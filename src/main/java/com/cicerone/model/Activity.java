package com.cicerone.model;

import com.cicerone.util.validation.Validator;

public abstract class Activity {

    private String title;
    private String code;
    private boolean disabled;
    private Integer order;
    private Section section;

    // Required fields only
    public Activity(String title, String code, Section section) {
        Validator.isNotBlankString(title);
        Validator.isValidCode(code);
        Validator.isNotNullObject(section);

        this.title = title;
        this.code = code;
        this.section = section;
        this.disabled = true;
    }

}
