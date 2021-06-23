package com.cicerone.model;

import com.cicerone.util.validation.Validator;

public class Question extends Activity {

    private String text;
    private QuestionType type;

    public Question(String title, String slug, Section section, String text, QuestionType type) {
        super(title, slug, section);

        Validator.isNotBlankString(text);
        Validator.isNotNullObject(type);

        this.text = text;
        this.type = type;
    }

}
