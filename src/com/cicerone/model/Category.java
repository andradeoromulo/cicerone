package com.cicerone.model;

import com.cicerone.utils.Validation;

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
        Validation.isNotBlankString(title);
        Validation.isValidSlug(slug);

        this.title = title;
        this.slug = slug;
        this.disabled = true;
    }

    // Required fields for Subcategory
    public Category(String title, String slug, Category parentCategory) {
        this(title, slug);

        Validation.isNotNullObject(parentCategory);

        this.parentCategory = parentCategory;
    }

}
