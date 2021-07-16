package com.cicerone.model;

import com.cicerone.util.validation.Validator;

import java.util.*;

public class Category {

    private Long id;
    private String title;
    private String code;
    private String description;
    private String studyGuide;
    private boolean disabled;
    private Integer order;
    private String iconPath;
    private String colorHexCode;
    private List<Subcategory> subcategories;

    // Required fields for Category
    public Category(String title, String code) {
        Validator.isNotBlankString(title);
        Validator.isValidCode(code);

        this.title = title;
        this.code = code;
        this.disabled = true;
        this.subcategories = new ArrayList<>();
    }

    public Category(String title, String code, Integer order, String description, boolean disabled, String iconPath, String colorHexCode) {
        this(title, code);
        this.order = order;
        this.description = description;
        this.disabled = disabled;
        this.iconPath = iconPath;
        this.colorHexCode = colorHexCode;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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

    public String getCode() {
        return code;
    }

    public Integer getOrder() {
        return order;
    }

    public boolean isDisabled() {
        return disabled;
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
