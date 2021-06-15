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
    public Course(String title, String slug, String instructor) {
        this.setTitle(title);
        this.setSlug(slug);
        this.setInstructor(instructor);
        this.setPrivated(true);
    }

    public void setTitle(String title) {
        Validation.isNotBlankString(title, "Course.title");
        this.title = title;
    }

    public void setSlug(String slug) {
        Validation.isValidSlug(slug, "Course.slug");
        this.slug = slug;
    }

    public void setEstimatedTimeToFinish(Integer estimatedTimeToFinish) {
        Validation.isIntegerBetweenRange(estimatedTimeToFinish, 1, 20, "Course.estimatedTimeToFinish");
        this.estimatedTimeToFinish = estimatedTimeToFinish;
    }

    public void setPrivated(boolean privated) {
        this.privated = privated;
    }

    public void setTargetAudience(String targetAudience) {
        this.targetAudience = targetAudience;
    }

    public void setInstructor(String instructor) {
        Validation.isNotBlankString(instructor, "Course.instructor");
        this.instructor = instructor;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public void setWorkingSkills(String workingSkills) {
        this.workingSkills = workingSkills;
    }
}