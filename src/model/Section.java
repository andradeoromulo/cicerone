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
        this.setTitle(title);
        this.setSlug(slug);
        this.setCourse(course);
        this.setDisabled(true);
    }

    public void setTitle(String title) {
        Validation.isNotBlankString(title, "Section.title");
        this.title = title;
    }

    public void setSlug(String slug) {
        Validation.isValidSlug(slug, "Section.slug");
        this.slug = slug;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public void setExam(boolean exam) {
        this.exam = exam;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
