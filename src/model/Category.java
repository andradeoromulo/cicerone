package model;

import utils.Validation;

public class Category {

    private String title;
    private String slug;
    private String description;
    private String studyGuide;
    private boolean enabled;
    private Integer order;
    private String iconPath;
    private String colorHexCode;

    // Required fields for Category
    public Category(String title, String slug) {
        Validation.isNotBlankString(title, "Category.title");
        Validation.isValidSlug(slug, "Category.slug");

        this.title = title;
        this.slug = slug;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStudyGuide(String studyGuide) {
        this.studyGuide = studyGuide;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public void setColorHexCode(String colorHexCode) {
        this.colorHexCode = colorHexCode;
    }
}
