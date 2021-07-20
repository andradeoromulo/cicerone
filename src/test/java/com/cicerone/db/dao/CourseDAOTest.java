package com.cicerone.db.dao;

import com.cicerone.model.Category;
import com.cicerone.model.Course;
import com.cicerone.model.Subcategory;
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

        Category javaCategory = aCategory("Java", "java", false);
        Subcategory persistenceSubcategory = aSubcategory("Java Persistence", "java-persistence", javaCategory, false);
        Course jdbcCourse = aCourse("JDBC", "jdbc", persistenceSubcategory, false);

        Course fetchedCourse = courseDAO.findByCode(jdbcCourse.getCode());

        assertThat(fetchedCourse).isEqualTo(jdbcCourse);

    }

    @Test
    public void setAllAsEnabled__should_set_all_courses_as_enabled() {

        Category javaCategory = aCategory("Java", "java", false);
        Subcategory persistenceSubcategory = aSubcategory("Java Persistence", "java-persistence", javaCategory, false);

        Course jdbcCourse = aCourse("JDBC", "jdbc", persistenceSubcategory, true);
        Course jpaCourse = aCourse("JPA", "jpa", persistenceSubcategory, true);
        Course springDataCourse = aCourse("Spring Data", "spring-data", persistenceSubcategory, true);

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

        Category javaCategory = aCategory("Java", "java", false);
        Subcategory persistenceSubcategory = aSubcategory("Java Persistence", "java-persistence", javaCategory, false);

        Course jdbcCourse = aCourse("JDBC", "jdbc", persistenceSubcategory, false);
        Course jpaCourse = aCourse("JPA", "jpa", persistenceSubcategory, false);
        Course springDataCourse = aCourse("Spring Data", "spring-data", persistenceSubcategory, true);

        List<Course> enabledCourses = courseDAO.findAllEnabled();

        assertThat(enabledCourses)
                .hasSize(2)
                .containsExactlyInAnyOrder(jdbcCourse, jpaCourse)
                .doesNotContain(springDataCourse);

    }

    private Course aCourse(String title, String code, Subcategory subcategory, boolean disabled) {
        Course course = new Course(title, code, 8, "João Silva", subcategory);
        course.setDisabled(disabled);
        em.persist(course);
        return course;
    }

    private Subcategory aSubcategory(String title, String code, Category parentCategory, boolean disabled) {
        Subcategory subcategory = new Subcategory(title, code, parentCategory);
        subcategory.setDisabled(disabled);
        em.persist(subcategory);
        return subcategory;
    }

    private Category aCategory(String title, String code, boolean disabled) {
        Category category = new Category(title, code);
        category.setDisabled(disabled);
        em.persist(category);
        return category;
    }

}