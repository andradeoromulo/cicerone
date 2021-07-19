package com.cicerone.db.dao;

import com.cicerone.model.Subcategory;

import javax.persistence.EntityManager;

public class SubcategoryDAO {

    private EntityManager em;

    public SubcategoryDAO(EntityManager em) {
        this.em = em;
    }

    public Subcategory findById(Long id) {
        return em.find(Subcategory.class, id);
    }
}