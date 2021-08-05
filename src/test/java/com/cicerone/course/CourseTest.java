package com.cicerone.course;

import com.cicerone.category.Category;
import com.cicerone.subcategory.Subcategory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertThrows;

class CourseTest {

    @ParameterizedTest
    @CsvSource({
            ", java*&-intro,40,,null",
            ", java-oo,6,João Santos,java-intro",
            "Java e Orientação a Objetos, java-oo,35,João Santos,java-intro",
            "Java e Orientação a Objetos, java-oo,6,,java-intro",
            "Java e Orientação a Objetos,java-oo,6,João Santos,null",
            "Introduction to Java,-java-101-oo,6,João Santos,java-intro",
            "Introduction to Java,-java-oo,6,João Santos,java-intro",
            "Introduction to Java,java-oo-,6,João Santos,java-intro"
    })
    public void Course__should_throw_exception_in_case_of_invalid_arguments(String title, String code, int timeToFinishInHours, String instructor, String subcategoryReference) {

        Category category = new Category("Java", "java");
        Subcategory subcategory = new Subcategory("Introdução a Java", "java-intro", category);

        assertThrows(IllegalArgumentException.class, () -> {
            new Course(title, code, timeToFinishInHours, instructor, subcategoryReference.equals("java-intro") ? subcategory : null);
        });

    }

}