package com.cicerone.model;

import com.cicerone.util.validation.Validator;

import java.util.*;

public class Subcategory {

    private String title;
    private String slug;
    private String description;
    private String studyGuide;
    private boolean disabled;
    private Integer order;
    private String iconPath;
    private String colorHexCode;
    private Category parentCategory;
    private List<Course> courses;

    // Required fields for Subcategory
    public Subcategory(String title, String slug, Category parentCategory) {
        Validator.isNotBlankString(title);
        Validator.isValidSlug(slug);
        Validator.isNotNullObject(parentCategory);

        this.title = title;
        this.slug = slug;
        this.disabled = true;
        this.parentCategory = parentCategory;
        this.courses = new ArrayList<>();

        parentCategory.addSubcategory(this);
    }

    public Subcategory(String title, String slug, Integer order, String description, boolean disabled, Category parentCategory) {
        this(title, slug, parentCategory);
        this.order = order;
        this.description = description;
        this.disabled = disabled;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getSlug() {
        return slug;
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
}
