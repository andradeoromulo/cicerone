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
    private Category parentCategory;

    // Required fields for Category
    public Category(String title, String slug) {
        Validation.isNotBlankString(title, "Category.title");
        Validation.isValidSlug(slug, "Category.slug");

        this.title = title;
        this.slug = slug;
    }

    // Required fields for Subcategory
    public Category(String title, String slug, String description, Category parentCategory) {
        Validation.isNotBlankString(title, "Category.title");
        Validation.isValidSlug(slug, "Category.slug");

        this.title = title;
        this.slug = slug;
        this.parentCategory = parentCategory;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStudyGuide() {
        return studyGuide;
    }

    public void setStudyGuide(String studyGuide) {
        this.studyGuide = studyGuide;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public String getColorHexCode() {
        return colorHexCode;
    }

    public void setColorHexCode(String colorHexCode) {
        this.colorHexCode = colorHexCode;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }
}
