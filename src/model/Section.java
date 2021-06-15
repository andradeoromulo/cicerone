package model;

import utils.Validation;

public class Section {

    private String title;
    private String slug;
    private Integer order;
    private boolean disabled;
    private boolean exam;
    private Course course;

    // Required fields only
    public Section(String title, String slug, Course course) {
        Validation.isNotBlankString(title, "Section.title");
        Validation.isValidSlug(slug, "Section.slug");

        this.title = title;
        this.slug = slug;
        this.course = course;
        this.disabled = true;
    }

    public void setTitle(String title) {
        Validation.isNotBlankString(title, "Section.title");
        this.title = title;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public boolean isExam() {
        return exam;
    }

    public void setExam(boolean exam) {
        this.exam = exam;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
