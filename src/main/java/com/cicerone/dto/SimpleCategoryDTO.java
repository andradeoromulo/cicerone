package com.cicerone.dto;

import com.cicerone.model.Category;

public record SimpleCategoryDTO(String code, String title, boolean disabled) {

    public SimpleCategoryDTO(Category category) {
        this(category.getCode(), category.getTitle(), category.isDisabled());
    }

}
