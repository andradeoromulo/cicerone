package com.cicerone.category;

import com.cicerone.subcategory.Subcategory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.apache.commons.lang3.Validate.matchesPattern;
import static org.apache.commons.lang3.Validate.notBlank;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String code;
    private String description;
    private String studyGuide;
    private boolean disabled;
    private String iconPath;
    private String colorHexCode;

    @Column(name = "orderPosition")
    private Integer order;

    @OneToMany(mappedBy = "parentCategory")
    private List<Subcategory> subcategories;

    @Deprecated
    public Category(){};

    // Required fields for Category
    public Category(String title, String code) {
        notBlank(title);
        matchesPattern(code, "^[a-z]+[a-z-]*[a-z]+$");

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

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getStudyGuide() {
        return studyGuide;
    }

    public void setStudyGuide(String studyGuide) {
        this.studyGuide = studyGuide;
    }
}
