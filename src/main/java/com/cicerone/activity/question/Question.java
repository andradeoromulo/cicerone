package com.cicerone.activity.question;

import com.cicerone.activity.Activity;
import com.cicerone.section.Section;

import javax.persistence.Column;
import javax.persistence.Entity;

import static org.apache.commons.lang3.Validate.notBlank;
import static org.apache.commons.lang3.Validate.notNull;

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

        notBlank(text);
        notNull(type);

        this.text = text;
        this.type = type;
    }

}
