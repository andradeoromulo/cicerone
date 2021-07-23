package com.cicerone.model;

import com.cicerone.util.validation.Validator;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Question extends Activity {

    private QuestionType type;

    @Column(columnDefinition = "TEXT", name = "questionText")
    private String text;

    @Deprecated
    public Question(){
        super();
    };

    public Question(String title, String code, Section section, String text, QuestionType type) {
        super(title, code, section);

        Validator.isNotBlankString(text);
        Validator.isNotNullObject(type);

        this.text = text;
        this.type = type;
    }

}
