package com.cicerone.dto;

import com.cicerone.model.Category;

import java.util.List;

public record CategoryDTO(String code, String title, int order, String colorHexCode,
          String studyGuide, int courseAmount, List<SubcategoryDTO> subcategories) {

    public CategoryDTO(Category category) {
        this(category.getCode(), category.getTitle(), category.getOrder(),
                category.getColorHexCode(), category.getStudyGuide(), category.getCourseAmount(),
                category.getSubcategories().stream().map(SubcategoryDTO::new).toList());
    }

}
