package com.cicerone.section;

import com.cicerone.course.Course;

import javax.persistence.*;

import static org.apache.commons.lang3.Validate.*;

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
        notBlank(title);
        matchesPattern(code, "^[a-z]+[a-z-]*[a-z]+$");
        notNull(course);

        this.title = title;
        this.code = code;
        this.course = course;
        this.disabled = true;
    }

}
