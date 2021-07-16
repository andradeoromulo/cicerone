package com.cicerone.main.apps;

import com.cicerone.db.dao.CourseDAO;
import com.cicerone.db.dao.SubcategoryDAO;
import com.cicerone.db.factory.ConnectionFactory;
import com.cicerone.model.Course;
import com.cicerone.model.Subcategory;

import java.sql.*;

public class CourseInsertionApp {

    public static void main(String[] args) {

        SubcategoryDAO subcategoryDAO = new SubcategoryDAO();
        Subcategory subcategory = subcategoryDAO.findByCode("java").orElseThrow(() -> new RuntimeException("Subcategory not found"));

        Course course = new Course("Java: Reflection API", "java-reflection", 8, "Paulo Silveira", subcategory);
        CourseDAO courseDAO = new CourseDAO();
        courseDAO.save(course);

        System.out.println("\nNovo curso criado com o id: " + course.getId());

    }

}
