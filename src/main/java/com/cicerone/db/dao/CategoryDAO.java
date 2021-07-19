package com.cicerone.db.dao;

import com.cicerone.model.Category;

import javax.persistence.EntityManager;
import java.util.Optional;

public class CategoryDAO {

    private EntityManager em;

    public CategoryDAO(EntityManager em) {
        this.em = em;
    }

    public Category findById(Long id) {
        return em.find(Category.class, id);
    }

}