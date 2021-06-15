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
        this.setTitle(title);
        this.setSlug(slug);
    }

    // Required fields for Subcategory
    public Category(String title, String slug, String description, Category parentCategory) {
        this.setTitle(title);
        this.setSlug(slug);
        this.setParentCategory(parentCategory);
    }

    public void setTitle(String title) {
        Validation.isNotBlankString(title, "Category.title");
        this.title = title;
    }

    public void setSlug(String slug) {
        Validation.isValidSlug(slug, "Category.slug");
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

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public boolean isSubcategory() {
        return this.parentCategory != null;
    }

}
