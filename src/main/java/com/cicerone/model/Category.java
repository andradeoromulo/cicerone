package com.cicerone.model;

import com.cicerone.util.validation.Validator;

import java.util.*;
import java.util.stream.Collectors;

public class Category {

    private String title;
    private String slug;
    private String description;
    private String studyGuide;
    private boolean disabled;
    private Integer order;
    private String iconPath;
    private String colorHexCode;
    private List<Subcategory> subcategories;

    // Required fields for Category
    public Category(String title, String slug) {
        Validator.isNotBlankString(title);
        Validator.isValidSlug(slug);

        this.title = title;
        this.slug = slug;
        this.disabled = true;
        this.subcategories = new ArrayList<>();
    }

    public Category(String title, String slug, Integer order, String description, boolean disabled, String iconPath, String colorHexCode) {
        this(title, slug);
        this.order = order;
        this.description = description;
        this.disabled = disabled;
        this.iconPath = iconPath;
        this.colorHexCode = colorHexCode;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getIconPath() {
        return iconPath;
    }

    public String getColorHexCode() {
        return colorHexCode;
    }

    public String getSlug() {
        return slug;
    }

    public Integer getCourseAmount() {
        return subcategories.stream().mapToInt(Subcategory::getCourseAmount).sum();
    }

    public Integer getTimeToFinishInHours() {
        return subcategories.stream().mapToInt(Subcategory::getTimeToFinishInHours).sum();
    }

    public List<Subcategory> getSubcategories() {
        return Collections.unmodifiableList(this.subcategories);
    }

    public void addSubcategory(Subcategory subcategory) {
        this.subcategories.add(subcategory);
    }

}
