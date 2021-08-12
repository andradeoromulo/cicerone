package com.cicerone.subcategory;

import com.cicerone.course.CourseApiDTO;

import java.util.List;

public record SubcategoryApiDTO(String code, String title, List<CourseApiDTO> courses) {

    public SubcategoryApiDTO(Subcategory subcategory) {
        this(subcategory.getCode(), subcategory.getTitle(),
                subcategory.getCourses().stream().map(CourseApiDTO::new).toList());
    }

}
