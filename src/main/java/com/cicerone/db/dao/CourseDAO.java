package com.cicerone.db.dao;

import com.cicerone.model.Course;

import javax.persistence.EntityManager;

public class CourseDAO {

    private EntityManager em;

    public CourseDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Course course) {
        em.persist(course);
    }

}