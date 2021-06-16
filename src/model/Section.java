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
        Validation.isNotNullObject(course, "Section.course");

        this.title = title;
        this.slug = slug;
        this.course = course;
        this.disabled = true;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public void setExam(boolean exam) {
        this.exam = exam;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
}
