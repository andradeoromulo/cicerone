package com.cicerone.activity.explanation;

import com.cicerone.activity.Activity;
import com.cicerone.section.Section;
import com.cicerone.util.validation.Validator;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Explanation extends Activity {

    @Column(columnDefinition = "TEXT", name = "explanationText")
    private String text;

    @Deprecated
    public Explanation(){
        super();
    };

    public Explanation(String title, String code, String text, Section section) {
        super(title, code, section);

        Validator.isNotBlankString(text);

        this.text = text;
    }

}
