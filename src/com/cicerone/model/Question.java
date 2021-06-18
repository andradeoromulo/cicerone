package com.cicerone.model;

import com.cicerone.utils.Validation;

public class Question extends Activity {

    private String text;
    private QuestionType type;

    public Question(String title, String slug, Section section, String text, QuestionType type) {
        super(title, slug, section);

        Validation.isNotBlankString(text);
        Validation.isNotNullObject(type);

        this.text = text;
        this.type = type;
    }

}
