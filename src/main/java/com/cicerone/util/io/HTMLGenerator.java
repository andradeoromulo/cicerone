package com.cicerone.util.io;

import com.cicerone.model.*;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class HTMLGenerator {

    private HTMLGenerator() {
    }

    public static void generate(String sourceDirPath, String targetDirPath) {

        List<Category> categories = fetchData(sourceDirPath, targetDirPath);

        String htmlItens = "";

        try(PrintWriter writer = new PrintWriter( new FileWriter(targetDirPath.concat("/categories.html"), false))) {

            for(Category category : categories) {

                String htmlSubItens = "";

                for(Subcategory subcategory : category.getSubcategories()) {

                    htmlSubItens += """
                                 \t\t\t\t\t<li>
                                 \t\t\t\t\t\t<h3>%s</h3>
                                 """.formatted(subcategory.getTitle());

                    if(!subcategory.getDescription().isEmpty()) {
                        htmlSubItens += "\t\t\t\t\t\t<p>Descrição: %s</p>".formatted(subcategory.getDescription());
                    }

                    if(subcategory.getCourses().size() > 0) {

                        String coursesNames = subcategory.getCourses().stream().map(course -> course.getTitle()).collect(Collectors.joining(", "));
                        htmlSubItens += "\n\t\t\t\t\t\t<p>Cursos: %s</p>".formatted(coursesNames);

                    }

                    htmlSubItens += "\n\t\t\t\t\t</li>\n";

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
            throw new RuntimeException(String.format("Not possible to complete HTML Generation: %s.", e.getMessage()));
        }

    }

    private static List<Category> fetchData(String sourceDirPath, String targetDirPath) {

        List<Category> categories = CSVDataLoader.load(sourceDirPath.concat("/categories.csv"), (properties) -> {

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

        List<Subcategory> subcategories = CSVDataLoader.load(sourceDirPath.concat("/subcategories.csv"), (properties) -> {

            String title = properties[0];
            String slug = properties[1].trim();
            int order = properties[2].isEmpty() ? 0 : Integer.parseInt(properties[2]);
            String description = properties[3];
            boolean disabled = !properties[4].equals("ATIVA");
            Category parentCategory = categoriesMap.get(properties[5]);

            return new Subcategory(title, slug, order, description, disabled, parentCategory);
        });

        Map<String, Subcategory> subcategoriesMap = subcategories.stream().collect(Collectors.toMap(Subcategory::getSlug, Function.identity()));

        List<Course> courses = CSVDataLoader.load(sourceDirPath.concat("/courses.csv"), (properties) -> {

            String title = properties[0];
            String slug = properties[1].trim();
            int timeToFinishInHours = Integer.parseInt(properties[2]);
            boolean disabled = !properties[3].equals("PÚBLICA");
            String targetAudience = properties[4];
            String instructor = properties[5];
            String program = properties[6];
            String skills = properties[7];
            Subcategory subcategory = subcategoriesMap.get(properties[8]);

            return new Course(title, slug, timeToFinishInHours, disabled, targetAudience, instructor, program, skills, subcategory);
        });

        return categories;

    }

}
