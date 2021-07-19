package com.cicerone.main.apps;

import com.cicerone.db.dao.CategoryDAO;
import com.cicerone.db.dao.CourseDAO;
import com.cicerone.db.dao.SubcategoryDAO;
import com.cicerone.model.Category;
import com.cicerone.model.Course;
import com.cicerone.model.Subcategory;
import com.cicerone.util.db.JPAUtil;

import javax.persistence.EntityManager;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ReportApp {

    public static void main(String[] args) {

        EntityManager em = JPAUtil.getEntityManager();
        CategoryDAO categoryDAO = new CategoryDAO(em);
        SubcategoryDAO subcategoryDAO = new SubcategoryDAO(em);
        CourseDAO courseDAO = new CourseDAO(em);

        em.getTransaction().begin();

        List<Category> categories = categoryDAO.findAllEnabled();
        List<Subcategory> subcategories = subcategoryDAO.findAllEnabled();
        List<Course> courses = courseDAO.findAllEnabled();

        em.getTransaction().commit();

        try (PrintWriter writer = new PrintWriter(new FileWriter(("src/main/resources/html/courses.html"), false))) {

            String categoryList = "";

            for (Category category : categories) {
                categoryList += """
                        \t\t\t<li>
                        \t\t\t\t<h3>%d: %s</h3> 
                        \t\t\t\t<p>Duração total em horas: %dh</p>
                        \t\t\t</li>
                        """.formatted(category.getId(), category.getTitle(), category.getTimeToFinishInHours());
            }

            String subcategoryList = "";

            for (Subcategory subcategory : subcategories) {
                subcategoryList += """
                        \t\t\t<li>
                        \t\t\t\t<h3>%d: %s</h3> 
                        \t\t\t\t<p>Duração total em horas: %dh</p>
                        \t\t\t</li>
                        """.formatted(subcategory.getId(), subcategory.getTitle(), subcategory.getTimeToFinishInHours());
            }

            String courseList = "";

            for (Course course : courses) {
                courseList += """
                        \t\t\t<li>
                        \t\t\t\t<h3>%d: %s</h3> 
                        \t\t\t\t<p>Duração total em horas: %dh</p>
                        \t\t\t\t<p>Subcategoria: %s (%d)</p>
                        \t\t\t</li>
                        """.formatted(course.getId(), course.getTitle(), course.getTimeToFinishInHours(),
                        course.getSubcategoryCode(), course.getSubcategoryId());
            }

            String htmlTemplate = """
                    <html lang="pt-br">
                    \t<head>
                    \t\t<meta charset="utf-8">
                    \t\t<title>Cicerone </title>
                    \t</head>
                    \t<body>
                    \t\t<h2>Categorias Ativas</h2>
                    \t\t<ul>
                    %s
                    \t\t</ul>
                    \t\t<h2>Subcategorias Ativas</h2>
                    \t\t<ul>
                    %s
                    \t\t</ul>
                    \t\t<h2>Cursos Ativos</h2>
                    \t\t<ul>
                    %s
                    \t\t</ul>
                    \t</body>
                    </html>
                    """.formatted(categoryList, subcategoryList, courseList);

            writer.println(htmlTemplate);

            System.out.println("\nArquivo gerado com sucesso e disponível em 'src/main/resources/html/courses.html'");

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        } finally {
            em.close();
        }

    }

}

