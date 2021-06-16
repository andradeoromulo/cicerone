package model;

import utils.Validation;

public class Course {

    private String title;
    private String slug;
    private Integer timeToFinishInHours;
    private boolean privated;
    private String targetAudience;
    private String instructor;
    private String program;
    private String workingSkills;
    private Subcategory subcategory;

    // Required fields only
    public Course(String title, String slug, Integer timeToFinishInHours, String instructor, Subcategory subcategory) {
        Validation.isNotBlankString(title, "Course.title");
        Validation.isValidSlug(slug, "Course.slug");
        Validation.isIntegerBetweenRange(timeToFinishInHours, 1, 20, "Course.estimatedTimeToFinish");
        Validation.isNotBlankString(instructor, "Course.instructor");
        Validation.isNotNullObject(subcategory, "Course.subcategory");

        this.title = title;
        this.slug = slug;
        this.timeToFinishInHours = timeToFinishInHours;
        this.instructor = instructor;
        this.subcategory = subcategory;
        this.privated = true;
    }

    public void setTargetAudience(String targetAudience) {
        this.targetAudience = targetAudience;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public void setWorkingSkills(String workingSkills) {
        this.workingSkills = workingSkills;
    }

    public void setPrivated(boolean privated) {
        this.privated = privated;
    }
}