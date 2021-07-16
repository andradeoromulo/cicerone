package com.cicerone.main.apps;

import com.cicerone.db.dao.CourseDAO;
import com.cicerone.model.Course;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ReportApp {

    public static void main(String[] args) {

        try (PrintWriter writer = new PrintWriter(new FileWriter(("src/main/resources/html/courses.html"), false))) {

            CourseDAO courseDAO = new CourseDAO();
            List<Course> courses = courseDAO.findAllEnabled();

            String courseList = "";

            for (Course course : courses) {
                courseList += """
                        \t\t\t<li>
                        \t\t\t\t<h2>%d: %s</h2> 
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
                    \t\t<title>Cicerone | Cursos Ativos </title>
                    \t</head>
                    \t<body>
                    \t\t<h1>Cursos Ativos</h1>
                    \t\t<ul>
                    %s
                    \t\t</ul>
                    \t</body>
                    </html>
                    """.formatted(courseList);

            writer.println(htmlTemplate);

            System.out.println("\nArquivo gerado com sucesso e disponível em 'src/main/resources/html/courses.html'");

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

}

