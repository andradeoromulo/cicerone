package com.cicerone.dto;

import com.cicerone.model.Category;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class CategoryForm {

    @NotBlank(message = "{category.title.notblank}")
    private String title;

    @NotBlank(message = "{category.code.notblank}")
    @Pattern(regexp = "^[a-z]+[a-z-]*[a-z]+$", message = "{category.code.pattern}")
    private String code;

    private String description;
    private String studyGuide;
    private boolean disabled;
    private String iconPath;
    private String colorHexCode;
    private Integer order;

    public String getTitle() {
        return title;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getStudyGuide() {
        return studyGuide;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public String getIconPath() {
        return iconPath;
    }

    public String getColorHexCode() {
        return colorHexCode;
    }

    public Integer getOrder() {
        return order;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStudyGuide(String studyGuide) {
        this.studyGuide = studyGuide;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public void setColorHexCode(String colorHexCode) {
        this.colorHexCode = colorHexCode;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Category toModel() {
        Category category = new Category(title, code, order, description, disabled, iconPath, colorHexCode);
        category.setStudyGuide(studyGuide);
        return category;
    }
}
