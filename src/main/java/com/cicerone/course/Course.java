package com.cicerone.course;

import com.cicerone.subcategory.Subcategory;

import javax.persistence.*;

import static org.apache.commons.lang3.Validate.*;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String code;
    private Integer timeToFinishInHours;
    private boolean disabled;
    private String targetAudience;
    private String instructor;

    @Column(columnDefinition = "TEXT")
    private String skills;

    @Column(columnDefinition = "TEXT")
    private String program;

    @ManyToOne
    @JoinColumn(name = "subcategoryId")
    private Subcategory subcategory;

    @Deprecated
    public Course(){};

    // Required fields only
    public Course(String title, String code, int timeToFinishInHours, String instructor, Subcategory subcategory) {
        notBlank(title);
        matchesPattern(code, "^[a-z]+[a-z-]*[a-z]+$");
        exclusiveBetween(1, 20, timeToFinishInHours);
        notBlank(instructor);
        notNull(subcategory);

        this.title = title;
        this.code = code;
        this.timeToFinishInHours = timeToFinishInHours;
        this.instructor = instructor;
        this.subcategory = subcategory;
        this.disabled = true;
    }

    public Course(String title, String code, Integer timeToFinishInHours, boolean disabled, String targetAudience, String instructor, String program, String skills, Subcategory subcategory) {
        this(title, code, timeToFinishInHours, instructor, subcategory);
        this.disabled = disabled;
        this.targetAudience = targetAudience;
        this.program = program;
        this.skills = skills;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSubcategoryId() {
        return subcategory.getId();
    }

    public String getSubcategoryCode() { return subcategory.getCode(); }

    public Integer getTimeToFinishInHours() {
        return timeToFinishInHours;
    }

    public String getTitle() {
        return title;
    }

    public String getCode() {
        return code;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public String getTargetAudience() {
        return targetAudience;
    }

    public String getInstructor() {
        return instructor;
    }

    public String getProgram() {
        return program;
    }

    public String getSkills() {
        return skills;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public void setTimeToFinishInHours(Integer timeToFinishInHours) {
        this.timeToFinishInHours = timeToFinishInHours;
    }
}

