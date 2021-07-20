package com.cicerone.db.dao;

import com.cicerone.model.Category;
import com.cicerone.model.Subcategory;
import com.cicerone.util.db.JPAUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SubcategoryDAOTest {

    private EntityManager em;
    private SubcategoryDAO subcategoryDAO;

    @BeforeEach
    public void setUp() {
        em = JPAUtil.getEntityManager();
        subcategoryDAO = new SubcategoryDAO(em);
        em.getTransaction().begin();
    }

    @AfterEach
    public void tearDown() {
        em.getTransaction().rollback();
    }

    @Test
    public void findAllEnabled__should_fetch_in_order_enabled_subcategories_only() {

        Category javaCategory = aCategory("Java", "java", false);

        Subcategory persistenceSubcategory = aSubcategory("Java Persistence", "java-persistence", javaCategory, 2, false);
        Subcategory ooSubcategory = aSubcategory("Java OO", "java-oo", javaCategory, 1, false);
        Subcategory webSubcategory = aSubcategory("Java Web", "java-web", javaCategory, 0, true);

        List<Subcategory> enabledSubcategories = subcategoryDAO.findAllEnabled();

        assertThat(enabledSubcategories)
                .hasSize(2)
                .containsExactly(ooSubcategory, persistenceSubcategory)
                .doesNotContain(webSubcategory);

    }

    private Subcategory aSubcategory(String title, String code, Category parentCategory, int order, boolean disabled) {
        Subcategory subcategory = new Subcategory(title, code, parentCategory);
        subcategory.setDisabled(disabled);
        subcategory.setOrder(order);
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