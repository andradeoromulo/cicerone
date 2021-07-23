package com.cicerone.main.apps;

import com.cicerone.db.dao.CourseDAO;
import com.cicerone.util.db.JPAUtil;

import javax.persistence.EntityManager;

public class UpdateApp {

    public static void main(String[] args) {

        EntityManager em = JPAUtil.getEntityManager();
        CourseDAO courseDAO = new CourseDAO(em);

        em.getTransaction().begin();

        int modifiedLines = courseDAO.updateAllAsEnabled();

        em.getTransaction().commit();
        em.close();

        System.out.println("\nAtualização realização com sucesso: " + modifiedLines + " linhas modificadas");

    }

}
