package com.cicerone.db.dao;

import com.cicerone.course.Course;

import javax.persistence.EntityManager;
import java.util.List;

public class CourseDAO {

    private EntityManager em;

    public CourseDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Course course) {
        em.persist(course);
    }

    public Course findByCode(String code) {

        String jpql = "SELECT c FROM Course c WHERE c.code = :code";

        return em.createQuery(jpql, Course.class)
                .setParameter("code", code)
                .getSingleResult();

    }

    public void delete(Course course) {
        course = em.merge(course);
        em.remove(course);
    }

    public int updateAllAsEnabled() {

        String jpql = "UPDATE Course c SET c.disabled = false";

        return em.createQuery(jpql)
                .executeUpdate();

    }

    public List<Course> findAllEnabled() {

        String jpql = "SELECT c FROM Course c WHERE c.disabled = false";

        return em.createQuery(jpql, Course.class)
                .getResultList();

    }

}