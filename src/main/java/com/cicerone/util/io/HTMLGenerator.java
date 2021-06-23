package com.cicerone.util.io;

import com.cicerone.model.Category;
import com.cicerone.model.Course;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class HTMLGenerator {

    public static void generate() {

        try(PrintWriter writer = new PrintWriter(new FileWriter("src/main/resources/html/categories.html", false))) {

            CSVDataLoader.load();

            String htmlCode = "";

            htmlCode += """
                        <html lang="pt-br">
                            <head>
                                <meta charset="utf-8">
                                <title>Cicerone | Categorias </title>
                            </head>
                            <body>
                                <h1>Categorias</h1>
                        """;

            htmlCode += """
                                <ul>
                        """;

            for (Category category : CSVDataLoader.categories.values()) {

                Map<Category, List<Course>> coursesBySub = CSVDataLoader.subcategories.values().stream()
                        .filter(sub -> sub.getParentCategorySlug().equals(category.getSlug()))
                        .collect(Collectors.toMap(
                                sub -> sub,
                                sub -> CSVDataLoader.courses.values().stream().filter(course ->
                                        course.getCategorySlug().equals(sub.getSlug())).collect(Collectors.toList())
                        ));

                var totalCourseAmount = coursesBySub.values().stream().mapToInt(List::size).sum();
                var totalTimetoFinish = coursesBySub.values().stream().mapToInt(courses ->
                        courses.stream().mapToInt(Course::getTimeToFinishInHours).sum()).sum();

                htmlCode += """
                                        <li>
                                            <h2>%s</h2>
                                            <p>Descrição: %s</p>
                                            <p>Ícone: %s</p>
                                            <p>Cor: %s</p>
                                            <p>Total de cursos: %d</p>
                                            <p>Total de horas: %d</p>
                                            <ul>
                            """.formatted(category.getTitle(), category.getDescription(), category.getIconPath(), category.getColorHexCode(), totalCourseAmount, totalTimetoFinish);

                for(Category subcategory : coursesBySub.keySet()) {

                    htmlCode += """
                                                    <li>
                                                        <h3>%s</h3>
                                """.formatted(subcategory.getTitle());

                    if(!subcategory.getDescription().isEmpty()) {
                        htmlCode += """
                                                            <p>Descrição: %s</p>
                                    """.formatted(subcategory.getDescription());
                    }

                    if(coursesBySub.get(subcategory).size() > 0) {

                        String coursesNames = coursesBySub.get(subcategory).stream().map(course -> course.getTitle()).collect(Collectors.joining(", "));
                        htmlCode += """
                                                            <p>Cursos: %s</p>
                                    """.formatted(coursesNames);

                    }

                    htmlCode += """
                                                    </li>
                                """;

                }

                htmlCode += """
                                            </ul>
                                        </li>
                            """;
            }

            htmlCode += """
                                </ul>
                            </body>
                        </html>
                        """;

            writer.println(htmlCode);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
