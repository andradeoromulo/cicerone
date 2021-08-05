package com.cicerone.category;

import com.cicerone.subcategory.SubcategoryApiDTO;

import java.util.List;

public record CategoryApiDTO(String code, String title, int order, String colorHexCode,
                             String studyGuide, int courseAmount, List<SubcategoryApiDTO> subcategories) {

    public CategoryApiDTO(Category category) {
        this(category.getCode(), category.getTitle(), category.getOrder(),
                category.getColorHexCode(), category.getStudyGuide(), category.getCourseAmount(),
                category.getSubcategories().stream().map(SubcategoryApiDTO::new).toList());
    }

}
