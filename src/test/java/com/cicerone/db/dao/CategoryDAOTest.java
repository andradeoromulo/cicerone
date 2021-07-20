package com.cicerone.db.dao;

import com.cicerone.model.Category;
import com.cicerone.util.db.JPAUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

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

        Category javaCategory = aCategory("Java", "java", 2, false);
        Category phpCategory = aCategory("PHP", "php", 1, false);
        Category pascalCategory = aCategory("Pascal", "pascal", 0,true);

        List<Category> enabledCategories = categoryDAO.findAllEnabled();

        assertThat(enabledCategories)
                .hasSize(2)
                .containsExactly(phpCategory, javaCategory)
                .doesNotContain(pascalCategory);

    }

    private Category aCategory(String title, String code, int order, boolean disabled) {
        Category category = new Category(title, code);
        category.setOrder(order);
        category.setDisabled(disabled);
        em.persist(category);
        return category;
    }

}