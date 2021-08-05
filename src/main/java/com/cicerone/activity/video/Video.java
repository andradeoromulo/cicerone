package com.cicerone.activity.video;

import com.cicerone.activity.Activity;
import com.cicerone.section.Section;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Video extends Activity {

    private Integer durationInMinutes;

    @Column(columnDefinition = "TEXT")
    private String transcription;

    @Deprecated
    public Video(){
        super();
    };

    // Required Fields Only
    public Video(String title, String code, Section section) {
        super(title, code, section);
    }

}
