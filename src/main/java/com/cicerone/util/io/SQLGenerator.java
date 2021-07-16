package com.cicerone.util.io;

import com.cicerone.model.Category;
import com.cicerone.model.Course;
import com.cicerone.model.Subcategory;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class SQLGenerator {

    private SQLGenerator() {
    }

    public static void generate(String sourceDirPath, String targetDirPath) {

        List<Category> categories = fetchData(sourceDirPath, targetDirPath);

        String sql = "";

        try(PrintWriter writer = new PrintWriter( new FileWriter(targetDirPath.concat("/data-load.sql"), false))) {

            for(Category category : categories) {

                sql += """
                       \n-- Category %s
                       INSERT INTO Category (title, code, order_position, description, disabled, icon_path, color_hex_code)
                       \tVALUES ("%s", "%s", %d, "%s", %b, "%s", "%s");
                       """.formatted(category.getTitle(), category.getTitle(), category.getCode(), category.getOrder(),
                        category.getDescription(), category.isDisabled(), category.getIconPath(), category.getColorHexCode());

                for(Subcategory subcategory : category.getSubcategories()) {

                    sql += """
                           \nINSERT INTO Subcategory (title, code, order_position, description, disabled, category_id)
                           \tSELECT "%s", "%s", %d, "%s", %b, id FROM Category c WHERE c.code = "%s";
                           """.formatted(subcategory.getTitle(), subcategory.getCode(), subcategory.getOrder(),
                            subcategory.getDescription(), subcategory.isDisabled(), category.getCode());

                    if(subcategory.getCourses().size() > 0) {
                        for(Course course : subcategory.getCourses()) {
                            sql += """
                                   \nINSERT INTO Course (title, code, time_to_finish_in_hours, disabled, target_audience, instructor, program, skills, subcategory_id)
                                   \tSELECT "%s", "%s", %d, %b, "%s", "%s", "%s", "%s", id FROM Subcategory s WHERE s.code = "%s";
                                   """.formatted(course.getTitle(), course.getCode(), course.getTimeToFinishInHours(), course.isDisabled(),
                                    course.getTargetAudience(), course.getInstructor(), course.getProgram(), course.getSkills(), subcategory.getCode());
                        }
                    }
                }
            }

            writer.println(sql);

        } catch (IOException e) {
            throw new RuntimeException(String.format("Not possible to complete SQL Generation: %s.", e.getMessage()));
        }

    }

    private static List<Category> fetchData(String sourceDirPath, String targetDirPath) {

        List<Category> categories = CSVDataLoader.load(sourceDirPath.concat("/categories.csv"), (properties) -> {

            String title = properties[0];
            String code = properties[1].trim();
            int order = properties[2].isEmpty() ? 0 : Integer.parseInt(properties[2]);
            String description = properties[3];
            boolean disabled = !properties[4].equals("ATIVA");
            String iconPath = properties[5];
            String colorHexCode = properties[6];

            return new Category(title, code, order, description, disabled, iconPath, colorHexCode);
        });

        Map<String, Category> categoriesMap = categories.stream().collect(Collectors.toMap(Category::getCode, Function.identity()));

        List<Subcategory> subcategories = CSVDataLoader.load(sourceDirPath.concat("/subcategories.csv"), (properties) -> {

            String title = properties[0];
            String code = properties[1].trim();
            int order = properties[2].isEmpty() ? 0 : Integer.parseInt(properties[2]);
            String description = properties[3];
            boolean disabled = !properties[4].equals("ATIVA");
            Category parentCategory = categoriesMap.get(properties[5]);

            return new Subcategory(title, code, order, description, disabled, parentCategory);
        });

        Map<String, Subcategory> subcategoriesMap = subcategories.stream().collect(Collectors.toMap(Subcategory::getCode, Function.identity()));

        List<Course> courses = CSVDataLoader.load(sourceDirPath.concat("/courses.csv"), (properties) -> {

            String title = properties[0];
            String code = properties[1].trim();
            int timeToFinishInHours = Integer.parseInt(properties[2]);
            boolean disabled = !properties[3].equals("PÚBLICA");
            String targetAudience = properties[4];
            String instructor = properties[5];
            String program = properties[6];
            String skills = properties[7];
            Subcategory subcategory = subcategoriesMap.get(properties[8]);

            return new Course(title, code, timeToFinishInHours, disabled, targetAudience, instructor, program, skills, subcategory);
        });

        return categories;

    }

}
