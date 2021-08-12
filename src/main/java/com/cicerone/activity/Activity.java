package com.cicerone.activity;

import com.cicerone.section.Section;

import javax.persistence.*;

import static org.apache.commons.lang3.Validate.*;

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
        notBlank(title);
        matchesPattern(code, "^[a-z]+[a-z-]*[a-z]+$");
        notNull(section);

        this.title = title;
        this.code = code;
        this.section = section;
        this.disabled = true;
    }

}
