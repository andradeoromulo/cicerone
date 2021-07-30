package com.cicerone.db.dao;

import com.cicerone.model.Category;
import com.cicerone.util.builder.CategoryBuilder;
import com.cicerone.util.db.JPAUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CategoryDAOTest {

    private EntityManager em;
    private CategoryDAO categoryDAO;

    @BeforeEach
    public void setUp() {
        em = JPAUtil.getEntityManager();
        categoryDAO = new CategoryDAO(em);
        em.getTransaction().begin();
    }

    @AfterEach
    public void tearDown() {
        em.getTransaction().rollback();
    }

    @Test
    public void findAllEnabled__should_fetch_in_order_enabled_categories_only() {

        Category javaCategory = aCategory("Java", "java", 2);
        Category phpCategory = aCategory("PHP", "php", 1);
        Category pascalCategory = aDisabledCategory("Pascal", "pascal", 0);

        List<Category> enabledCategories = categoryDAO.findAllEnabled();

        assertThat(enabledCategories)
                .hasSize(2)
                .containsExactly(phpCategory, javaCategory)
                .doesNotContain(pascalCategory);

    }

    private Category aCategory(String title, String code, int order) {
        Category category = new CategoryBuilder(title, code).enabled().orderedAs(order).build();
        em.persist(category);
        return category;
    }

    private Category aDisabledCategory(String title, String code, int order) {
        Category category = new CategoryBuilder(title, code).disabled().orderedAs(order).build();
        em.persist(category);
        return category;
    }

}