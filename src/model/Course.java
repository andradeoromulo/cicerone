package model;

import utils.Validation;

public class Course {

    private String title;
    private String slug;
    private Integer estimatedTimeToFinish;
    private boolean privated;
    private String targetAudience;
    private String instructor;
    private String program;
    private String workingSkills;

    // Required fields only
    public Course(String title, String slug, Integer estimatedTimeToFinish, String instructor) {
        Validation.isNotBlankString(title, "Course.title");
        Validation.isValidSlug(slug, "Course.slug");
        Validation.isIntegerBetweenRange(estimatedTimeToFinish, 1, 20, "Course.estimatedTimeToFinish");
        Validation.isNotBlankString(instructor, "Course.instructor");

        this.title = title;
        this.slug = slug;
        this.estimatedTimeToFinish = estimatedTimeToFinish;
        this.instructor = instructor;
        this.privated = true;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Integer getEstimatedTimeToFinish() {
        return estimatedTimeToFinish;
    }

    public void setEstimatedTimeToFinish(Integer estimatedTimeToFinish) {
        this.estimatedTimeToFinish = estimatedTimeToFinish;
    }

    public boolean isPrivated() {
        return privated;
    }

    public void setPrivated(boolean privated) {
        this.privated = privated;
    }

    public String getTargetAudience() {
        return targetAudience;
    }

    public void setTargetAudience(String targetAudience) {
        this.targetAudience = targetAudience;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getWorkingSkills() {
        return workingSkills;
    }

    public void setWorkingSkills(String workingSkills) {
        this.workingSkills = workingSkills;
    }
}