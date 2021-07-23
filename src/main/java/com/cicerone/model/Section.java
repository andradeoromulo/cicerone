package com.cicerone.model;

import com.cicerone.util.validation.Validator;

import javax.persistence.*;

@Entity
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String code;
    private boolean disabled;
    private boolean exam;

    @Column(name = "orderPosition")
    private Integer order;

    @ManyToOne
    @JoinColumn(name = "courseId")
    private Course course;

    @Deprecated
    public Section(){};

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
