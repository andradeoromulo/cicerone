package com.cicerone.db.dao;

import com.cicerone.model.Category;
import com.cicerone.model.Course;
import com.cicerone.model.Subcategory;
import com.cicerone.util.builder.CategoryBuilder;
import com.cicerone.util.builder.CourseBuilder;
import com.cicerone.util.builder.SubcategoryBuilder;
import com.cicerone.util.db.JPAUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CourseDAOTest {

    private EntityManager em;
    private CourseDAO courseDAO;

    @BeforeEach
    public void setUp() {
        em = JPAUtil.getEntityManager();
        courseDAO = new CourseDAO(em);
        em.getTransaction().begin();
    }

    @AfterEach
    public void tearDown() {
        em.getTransaction().rollback();
    }

    @Test
    public void findByCode__should_fetch__course_by_code_if_it_exists() {

        Category javaCategory = aCategory("Java", "java");
        Subcategory persistenceSubcategory = aSubcategory("Java Persistence", "java-persistence", javaCategory);
        Course jdbcCourse = aCourse("JDBC", "jdbc", persistenceSubcategory);

        Course fetchedCourse = courseDAO.findByCode(jdbcCourse.getCode());

        assertThat(fetchedCourse).isEqualTo(jdbcCourse);

    }

    @Test
    public void setAllAsEnabled__should_set_all_courses_as_enabled() {

        Category javaCategory = aCategory("Java", "java");
        Subcategory persistenceSubcategory = aSubcategory("Java Persistence", "java-persistence", javaCategory);

        Course jdbcCourse = aDisabledCourse("JDBC", "jdbc", persistenceSubcategory);
        Course jpaCourse = aDisabledCourse("JPA", "jpa", persistenceSubcategory);
        Course springDataCourse = aDisabledCourse("Spring Data", "spring-data", persistenceSubcategory);

        List<Course> enabledCourses = courseDAO.findAllEnabled();

        assertThat(enabledCourses).isEmpty();

        courseDAO.setAllAsEnabled();

        enabledCourses = courseDAO.findAllEnabled();

        assertThat(enabledCourses)
                .hasSize(3)
                .containsExactlyInAnyOrder(jdbcCourse, jpaCourse, springDataCourse);

    }

    @Test
    public void findAllEnabled__should_fetch_enabled_courses_only() {

        Category javaCategory = aCategory("Java", "java");
        Subcategory persistenceSubcategory = aSubcategory("Java Persistence", "java-persistence", javaCategory);

        Course jdbcCourse = aCourse("JDBC", "jdbc", persistenceSubcategory);
        Course jpaCourse = aCourse("JPA", "jpa", persistenceSubcategory);
        Course springDataCourse = aDisabledCourse("Spring Data", "spring-data", persistenceSubcategory);

        List<Course> enabledCourses = courseDAO.findAllEnabled();

        assertThat(enabledCourses)
                .hasSize(2)
                .containsExactlyInAnyOrder(jdbcCourse, jpaCourse)
                .doesNotContain(springDataCourse);

    }

    private Course aCourse(String title, String code, Subcategory subcategory) {
        Course course = new CourseBuilder(title, code, subcategory).enabled().build();
        em.persist(course);
        return course;
    }

    private Course aDisabledCourse(String title, String code, Subcategory subcategory) {
        Course course = new CourseBuilder(title, code, subcategory).disabled().build();
        em.persist(course);
        return course;
    }

    private Subcategory aSubcategory(String title, String code, Category parentCategory) {
        Subcategory subcategory = new SubcategoryBuilder(title, code, parentCategory).enabled().build();
        em.persist(subcategory);
        return subcategory;
    }

    private Category aCategory(String title, String code) {
        Category category = new CategoryBuilder(title, code).enabled().build();
        em.persist(category);
        return category;
    }

}