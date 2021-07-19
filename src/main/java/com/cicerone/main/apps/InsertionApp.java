package com.cicerone.main.apps;

import com.cicerone.db.dao.CourseDAO;
import com.cicerone.db.dao.SubcategoryDAO;
import com.cicerone.model.Course;
import com.cicerone.model.Subcategory;
import com.cicerone.util.db.JPAUtil;

import javax.persistence.EntityManager;

public class InsertionApp {

    public static void main(String[] args) {

        EntityManager em = JPAUtil.getEntityManager();
        SubcategoryDAO subcategoryDAO = new SubcategoryDAO(em);
        CourseDAO courseDAO = new CourseDAO(em);

        em.getTransaction().begin();

        Subcategory subcategory = subcategoryDAO.findById(1L);
        Course course = new Course("Java: Reflection API", "java-reflection", 8, "Paulo Silveira", subcategory);
        courseDAO.save(course);

        em.getTransaction().commit();
        em.close();

        System.out.println("\nNovo curso criado com o id: " + course.getId());

    }

}
