package com.cicerone.model;

import com.cicerone.util.validation.Validator;

public class Category {

    private String title;
    private String slug;
    private String description;
    private String studyGuide;
    private boolean disabled;
    private Integer order;
    private String iconPath;
    private String colorHexCode;
    private Category parentCategory;

    // Required fields for Category
    public Category(String title, String slug) {
        Validator.isNotBlankString(title);
        Validator.isValidSlug(slug);

        this.title = title;
        this.slug = slug;
        this.disabled = true;
    }

    public Category(String title, String slug, Integer order, String description, boolean disabled, String iconPath, String colorHexCode) {
        this(title, slug);
        this.order = order;
        this.description = description;
        this.disabled = disabled;
        this.iconPath = iconPath;
        this.colorHexCode = colorHexCode;
    }

    // Required fields for Subcategory
    public Category(String title, String slug, Category parentCategory) {
        this(title, slug);

        Validator.isNotNullObject(parentCategory);

        this.parentCategory = parentCategory;
    }

    public Category(String title, String slug, Integer order, String description, boolean disabled, Category parentCategory) {
        this(title, slug, parentCategory);
        this.order = order;
        this.description = description;
        this.disabled = disabled;
    }
}
