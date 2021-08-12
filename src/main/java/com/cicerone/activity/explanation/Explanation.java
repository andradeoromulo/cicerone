package com.cicerone.activity.explanation;

import com.cicerone.activity.Activity;
import com.cicerone.section.Section;

import javax.persistence.Column;
import javax.persistence.Entity;

import static org.apache.commons.lang3.Validate.notBlank;

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

        notBlank(text);

        this.text = text;
    }

}
