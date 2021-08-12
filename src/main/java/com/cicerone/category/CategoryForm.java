package com.cicerone.category;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class CategoryForm {

    @NotBlank(message = "{category.title.not.blank}")
    @Size(min = 3, message = "{category.title.size}")
    private String title;

    @NotBlank(message = "{category.code.not.blank}")
    @Pattern(regexp = "^[a-z]+[a-z-]*[a-z]+$", message = "{category.code.pattern}")
    private String code;

    private Long id;
    private String description;
    private String studyGuide;
    private boolean disabled;
    private String iconPath;
    private String colorHexCode;

    @Positive(message = "{category.code.positive}")
    private Integer order;

    public CategoryForm() {
    }

    public CategoryForm(Category category) {
        this.title = category.getTitle();
        this.code = category.getCode();
        this.id = category.getId();
        this.description = category.getDescription();
        this.studyGuide = category.getStudyGuide();
        this.disabled = category.isDisabled();
        this.iconPath = category.getIconPath();
        this.colorHexCode = category.getColorHexCode();
        this.order = category.getOrder();
    }

    public Long getId() {
        return id;
    }

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

    public void setId(Long id) {
        this.id = id;
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
        category.setId(id);
        category.setStudyGuide(studyGuide);
        return category;
    }

}
