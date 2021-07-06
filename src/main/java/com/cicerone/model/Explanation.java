package com.cicerone.model;

import com.cicerone.util.validation.Validator;

public class Explanation extends Activity {

    private String text;

    public Explanation(String title, String slug, String text, Section section) {
        super(title, slug, section);

        Validator.isNotBlankString(text);

        this.text = text;
    }

}