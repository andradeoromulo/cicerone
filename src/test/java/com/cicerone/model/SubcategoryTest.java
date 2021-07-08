package com.cicerone.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class SubcategoryTest {

    @ParameterizedTest
    @CsvSource({
            ", java*&-intro,null",
            ", java-intro,java",
            "Introdução a Java,java-intro,null",
            "Introdução a Java,java-101,java",
            "Introdução a Java,-java-intro,java",
            "Introdução a Java,java-intro-,java"
    })
    public void Subcategory__should_throw_exception_in_case_of_invalid_arguments(String title, String slug, String categoryReference) {

        Category category = new Category("Java", "java");

        assertThrows(IllegalArgumentException.class, () -> {
            new Subcategory(title, slug, categoryReference.equals("java") ? category : null);
        });

    }

    @Test
    public void getTimeToFinishInHours__should_retrieve_total_time_to_finish_based_on_its_courses() {

        Category category = new Category("Java", "java");
        Subcategory subcategory = new Subcategory("Introdução a Java", "java-intro", category);

        assertEquals(0, subcategory.getTimeToFinishInHours());

        Course course1 = new Course("Java e Orientação a Objetos", "java-oo", 6, "João Santos", subcategory);
        Course course2 = new Course("I/O em Java", "java-io", 8, "João Santos", subcategory);
        Course course3 = new Course("Collections em Java", "java-collections", 6, "João Santos", subcategory);

        assertEquals(20, subcategory.getTimeToFinishInHours());
    }

    @Test
    public void getCourseAmount__should_retrieve_course_amount_based_on_its_courses() {

        Category category = new Category("Java", "java");
        Subcategory subcategory = new Subcategory("Introdução a Java", "java-intro", category);

        assertEquals(0, subcategory.getCourseAmount());

        Course course1 = new Course("Java e Orientação a Objetos", "java-oo", 6, "João Santos", subcategory);
        Course course2 = new Course("I/O em Java", "java-io", 8, "João Santos", subcategory);
        Course course3 = new Course("Collections em Java", "java-collections", 6, "João Santos", subcategory);

        assertEquals(3, subcategory.getCourseAmount());
    }

}