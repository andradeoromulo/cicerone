package com.cicerone.category;

import com.cicerone.course.Course;
import com.cicerone.subcategory.Subcategory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CategoryTest {

    @ParameterizedTest
    @CsvSource({
            ", java*&-intro",
            ", java-intro",
            "Introdução a Java,java-101",
            "Introdução a Java,-java-intro",
            "Introdução a Java,java-intro-"
    })
    public void Category__should_throw_exception_in_case_of_invalid_arguments(String title, String code) {

        assertThrows(IllegalArgumentException.class, () -> new Category(title, code));

    }

    @Test
    public void getTimeToFinishInHours__should_retrieve_total_time_to_finish_based_on_its_courses() {

        Category category = new Category("Java", "java");

        assertEquals(0, category.getTimeToFinishInHours());

        Subcategory subcategory1 = new Subcategory("Introdução a Java", "java-intro", category);
        category.addSubcategory(subcategory1);

        Course course1 = new Course("Java e Orientação a Objetos", "java-oo", 6, "João Santos", subcategory1);
        subcategory1.addCourse(course1);
        Course course2 = new Course("I/O em Java", "java-io", 8, "João Santos", subcategory1);
        subcategory1.addCourse(course2);

        Subcategory subcategory2 = new Subcategory("Java Intermediário", "java-intermediario", category);
        category.addSubcategory(subcategory2);

        Course course3 = new Course("Collections em Java", "java-collections", 6, "João Santos", subcategory2);
        subcategory2.addCourse(course3);

        assertEquals(20, category.getTimeToFinishInHours());
    }

    @Test
    public void getCourseAmount__should_retrieve_course_amount_based_on_its_courses() {

        Category category = new Category("Java", "java");

        assertEquals(0, category.getCourseAmount());

        Subcategory subcategory1 = new Subcategory("Introdução a Java", "java-intro", category);
        category.addSubcategory(subcategory1);

        Course course1 = new Course("Java e Orientação a Objetos", "java-oo", 6, "João Santos", subcategory1);
        subcategory1.addCourse(course1);
        Course course2 = new Course("I/O em Java", "java-io", 8, "João Santos", subcategory1);
        subcategory1.addCourse(course2);

        Subcategory subcategory2 = new Subcategory("Java Intermediário", "java-intermediario", category);
        category.addSubcategory(subcategory2);

        Course course3 = new Course("Collections em Java", "java-collections", 6, "João Santos", subcategory2);
        subcategory2.addCourse(course3);

        assertEquals(3, category.getCourseAmount());
    }

}