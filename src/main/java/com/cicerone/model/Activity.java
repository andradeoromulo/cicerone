package com.cicerone.model;

import com.cicerone.util.validation.Validator;

import javax.persistence.*;

@MappedSuperclass
public abstract class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String code;
    private boolean disabled;

    @Column(name = "orderPosition")
    private Integer order;

    @ManyToOne
    @JoinColumn(name = "sectionId")
    private Section section;

    @Deprecated
    public Activity(){};

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
