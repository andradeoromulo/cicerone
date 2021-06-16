package model;

import utils.Validation;

public class Subcategory {

    private String title;
    private String slug;
    private String description;
    private String studyGuide;
    private boolean enabled;
    private Integer order;
    private String iconPath;
    private String colorHexCode;
    private model.Category parentCategory;

    // Required fields for Subcategory
    public Subcategory(String title, String slug, String description, model.Category parentCategory) {
        Validation.isNotBlankString(title, "Subcategory.title");
        Validation.isValidSlug(slug, "Subcategory.slug");

        this.title = title;
        this.slug = slug;
        this.parentCategory = parentCategory;
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