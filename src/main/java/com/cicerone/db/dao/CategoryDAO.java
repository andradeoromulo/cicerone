package com.cicerone.db.dao;

import com.cicerone.model.Category;

import javax.persistence.EntityManager;
import java.util.List;

public class CategoryDAO {

    private EntityManager em;

    public CategoryDAO(EntityManager em) {
        this.em = em;
    }

    public Category findById(Long id) {
        return em.find(Category.class, id);
    }

    public List<Category> findAllEnabled() {

        String jpql = "SELECT c FROM Category c WHERE c.disabled = false ORDER BY c.order";

        return em.createQuery(jpql, Category.class)
                .getResultList();

    }

}