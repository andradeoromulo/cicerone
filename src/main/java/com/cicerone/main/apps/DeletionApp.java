package com.cicerone.main.apps;

import com.cicerone.db.dao.CourseDAO;
import com.cicerone.model.Course;
import com.cicerone.util.db.JPAUtil;

import javax.persistence.EntityManager;

public class DeletionApp {

    public static void main(String[] args) {

        EntityManager em = JPAUtil.getEntityManager();
        CourseDAO courseDAO = new CourseDAO(em);

        em.getTransaction().begin();

        Course course = courseDAO.findByCode("java-reflection");
        courseDAO.delete(course);

        em.getTransaction().commit();
        em.close();

        System.out.println("\nCurso excluído com sucesso");

    }

}
