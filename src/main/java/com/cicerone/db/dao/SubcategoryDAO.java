package com.cicerone.db.dao;

import com.cicerone.model.Subcategory;

import javax.persistence.EntityManager;
import java.util.List;

public class SubcategoryDAO {

    private EntityManager em;

    public SubcategoryDAO(EntityManager em) {
        this.em = em;
    }

    public Subcategory findById(Long id) {
        return em.find(Subcategory.class, id);
    }

    public List<Subcategory> findAllEnabled() {

        String jpql = "SELECT s FROM Subcategory s WHERE s.disabled = false ORDER BY s.order";

        return em.createQuery(jpql, Subcategory.class)
                .getResultList();

    }

}