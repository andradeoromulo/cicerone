package com.cicerone.model;

import com.cicerone.util.validation.Validator;

import javax.persistence.*;
import java.util.*;

@Entity
public class Subcategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String code;
    private String description;
    private String studyGuide;
    private boolean disabled;
    private String iconPath;
    private String colorHexCode;

    @Column(name = "orderPosition")
    private Integer order;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category parentCategory;

    @OneToMany(mappedBy = "subcategory")
    private List<Course> courses;

    @Deprecated
    public Subcategory(){};

    // Required fields for Subcategory
    public Subcategory(String title, String code, Category parentCategory) {
        Validator.isNotBlankString(title);
        Validator.isValidCode(code);
        Validator.isNotNullObject(parentCategory);

        this.title = title;
        this.code = code;
        this.disabled = true;
        this.parentCategory = parentCategory;
        this.courses = new ArrayList<>();

        parentCategory.addSubcategory(this);
    }

    public Subcategory(String title, String code, Integer order, String description, boolean disabled, Category parentCategory) {
        this(title, code, parentCategory);
        this.order = order;
        this.description = description;
        this.disabled = disabled;
    }

    public Integer getCourseAmount() {
        return courses.size();
    }

    public Integer getTimeToFinishInHours() {
        return courses.stream().mapToInt(Course::getTimeToFinishInHours).sum();
    }

    public List<Course> getCourses() {
        return Collections.unmodifiableList(courses);
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCode() {
        return code;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public Object getOrder() {
        return order;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}
