package com.cicerone.util.io;

import com.cicerone.model.Category;
import com.cicerone.model.Course;

import java.io.*;
import java.util.*;

public final class CSVDataLoader {

    public static Map<String, Category> categories = new HashMap<>();
    public static Map<String, Category> subcategories = new HashMap<>();
    public static Map<String, Course> courses = new HashMap<>();

    private CSVDataLoader() {
    }

    public static void load() {
        loadCategories();
        loadSubcategories();
        loadCourses();
    }

    private static void loadCategories() {

        try (Scanner scanner = new Scanner(new FileReader("src/main/resources/csv/categories.csv"))) {

            scanner.nextLine();

            while (scanner.hasNext()) {

                String[] properties = scanner.nextLine().split(",");

                String title = properties[0];
                String slug = properties[1].trim();
                int order = properties[2].isEmpty() ? 0 : Integer.parseInt(properties[2]);
                String description = properties[3];
                boolean disabled = !properties[4].equals("ATIVA");
                String iconPath = properties[5];
                String colorHexCode = properties[6];

                Category category = new Category(title, slug, order, description, disabled, iconPath, colorHexCode);
                categories.put(slug, category);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void loadSubcategories() {

        try (Scanner scanner = new Scanner(new FileReader("src/main/resources/csv/subcategories.csv"))) {

            scanner.nextLine();

            while (scanner.hasNext()) {

                String[] properties = scanner.nextLine().split(",");

                String title = properties[0];
                String slug = properties[1].trim();
                int order = properties[2].isEmpty() ? 0 : Integer.parseInt(properties[2]);
                String description = properties[3];
                boolean disabled = !properties[4].equals("ATIVA");
                Category parentCategory = categories.get(properties[5]);

                Category subcategory = new Category(title, slug, order, description, disabled, parentCategory);
                subcategories.put(slug, subcategory);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void loadCourses() {

        try (Scanner scanner = new Scanner(new FileReader("src/main/resources/csv/courses.csv"))) {

            scanner.nextLine();

            while (scanner.hasNext()) {

                String[] properties = scanner.nextLine().split(",");

                String title = properties[0];
                String slug = properties[1].trim();
                int timeToFinishInHours = Integer.parseInt(properties[2]);
                boolean disabled = !properties[3].equals("PÚBLICA");
                String targetAudience = properties[4];
                String instructor = properties[5];
                String program = properties[6];
                String skills = properties[7];
                Category category = subcategories.get(properties[8]);

                Course course = new Course(title, slug, timeToFinishInHours, disabled, targetAudience, instructor, program, skills, category);
                courses.put(slug, course);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
