package com.cicerone.model;

import com.cicerone.util.validation.Validator;

public class Section {

    private String title;
    private String code;
    private Integer order;
    private boolean disabled;
    private boolean exam;
    private Course course;

    // Required fields only
    public Section(String title, String code, Course course) {
        Validator.isNotBlankString(title);
        Validator.isValidCode(code);
        Validator.isNotNullObject(course);

        this.title = title;
        this.code = code;
        this.course = course;
        this.disabled = true;
    }

}
