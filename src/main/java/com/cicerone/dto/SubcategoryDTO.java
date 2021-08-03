package com.cicerone.dto;

import com.cicerone.model.Subcategory;

import java.util.List;

public record SubcategoryDTO(String code, String title, List<CourseDTO> courses) {

    public SubcategoryDTO(Subcategory subcategory) {
        this(subcategory.getCode(), subcategory.getTitle(),
                subcategory.getCourses().stream().map(CourseDTO::new).toList());
    }

}
