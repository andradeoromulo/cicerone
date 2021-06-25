package com.cicerone.util.io;

import com.cicerone.model.*;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HTMLGenerator {

    private String sourceDirPath;
    private String targetDirPath;

    private List<Category> categories;
    private List<Category> subcategories;
    private List<Course> courses;

    public HTMLGenerator(String sourceDirPath, String targetDirPath) {
        this.sourceDirPath = sourceDirPath;
        this.targetDirPath = targetDirPath;
    }

    public void generate() {

        this.fetchData();

        String htmlItens = "";
        String htmlSubItens = "";

        try(PrintWriter writer = new PrintWriter( new FileWriter(this.targetDirPath.concat("/categories.html"), false))) {

            for (Category category : categories) {

                Map<Category, List<Course>> coursesBySub = mapCoursesToSubcategories(category);

                for(Category subcategory : coursesBySub.keySet()) {

                    htmlSubItens += """
                                 \t\t\t\t\t<li>
                                 \t\t\t\t\t\t<h3>%s</h3>
                                 """.formatted(subcategory.getTitle());

                    if(!subcategory.getDescription().isEmpty()) {
                        htmlSubItens += "\t\t\t\t\t\t<p>Descrição: %s</p>".formatted(subcategory.getDescription());
                    }

                    if(coursesBySub.get(subcategory).size() > 0) {

                        String coursesNames = coursesBySub.get(subcategory).stream().map(course -> course.getTitle()).collect(Collectors.joining(", "));
                        htmlSubItens += "\t\t\t\t\t\t<p>Cursos: %s</p>".formatted(coursesNames);

                    }

                    htmlSubItens += "\t\t\t\t\t</li>";

                }

                htmlItens += """
                             \t\t\t<li>
                             \t\t\t\t<h2>%s</h2>
                             \t\t\t\t<p>Descrição: %s</p>
                             \t\t\t\t<p>Ícone: %s</p>
                             \t\t\t\t<p>Cor: %s</p>
                             \t\t\t\t<p>Total de cursos: %d</p>
                             \t\t\t\t<p>Total de horas: %d</p>
                             \t\t\t\t<ul>
                             %s
                             \t\t\t\t</ul>
                             \t\t\t</li>
                             """.formatted(category.getTitle(), category.getDescription(), category.getIconPath(),
                        category.getColorHexCode(), category.getCourseAmount(), category.getTimeToFinishInHours(),
                        htmlSubItens);
            }

            String htmlTemplate = """
                                  <html lang="pt-br">
                                  \t<head>
                                  \t\t<meta charset="utf-8">
                                  \t\t<title>Cicerone | Categorias </title>
                                  \t</head>
                                  \t<body>
                                  \t\t<h1>Categorias</h1>
                                  \t\t<ul>
                                  %s
                                  \t\t</ul>
                                  \t</body>
                                  </html>
                                  """.formatted(htmlItens);

            writer.println(htmlTemplate);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void fetchData() {

        this.categories = CSVDataLoader.load(this.sourceDirPath.concat("/categories.csv"), (properties) -> {

            String title = properties[0];
            String slug = properties[1].trim();
            int order = properties[2].isEmpty() ? 0 : Integer.parseInt(properties[2]);
            String description = properties[3];
            boolean disabled = !properties[4].equals("ATIVA");
            String iconPath = properties[5];
            String colorHexCode = properties[6];

            return new Category(title, slug, order, description, disabled, iconPath, colorHexCode);
        });

        Map<String, Category> categoriesMap = categories.stream().collect(Collectors.toMap(Category::getSlug, Function.identity()));

        this.subcategories = CSVDataLoader.load(this.sourceDirPath.concat("/subcategories.csv"), (properties) -> {

            String title = properties[0];
            String slug = properties[1].trim();
            int order = properties[2].isEmpty() ? 0 : Integer.parseInt(properties[2]);
            String description = properties[3];
            boolean disabled = !properties[4].equals("ATIVA");
            Category parentCategory = categoriesMap.get(properties[5]);

            return new Category(title, slug, order, description, disabled, parentCategory);
        });

        Map<String, Category> subcategoriesMap = subcategories.stream().collect(Collectors.toMap(Category::getSlug, Function.identity()));

        this.courses = CSVDataLoader.load(this.sourceDirPath.concat("/courses.csv"), (properties) -> {

            String title = properties[0];
            String slug = properties[1].trim();
            int timeToFinishInHours = Integer.parseInt(properties[2]);
            boolean disabled = !properties[3].equals("PÚBLICA");
            String targetAudience = properties[4];
            String instructor = properties[5];
            String program = properties[6];
            String skills = properties[7];
            Category category = subcategoriesMap.get(properties[8]);

            return new Course(title, slug, timeToFinishInHours, disabled, targetAudience, instructor, program, skills, category);
        });

    }

    private Map<Category, List<Course>> mapCoursesToSubcategories(Category category) {

        Map<Category, List<Course>> coursesBySub = subcategories.stream()
                .filter(sub -> sub.getParentCategorySlug().equals(category.getSlug()))
                .collect(Collectors.toMap(
                        Function.identity(),
                        sub -> courses.stream().filter(course -> course.getCategorySlug().equals(sub.getSlug())).collect(Collectors.toList())
                ));

        category.setCourseAmount(coursesBySub.values().stream().mapToInt(List::size).sum());

        category.setTimeToFinishInHours(coursesBySub.values().stream().mapToInt(subCourses ->
                subCourses.stream().mapToInt(Course::getTimeToFinishInHours).sum()).sum());

        return coursesBySub;

    }

}
