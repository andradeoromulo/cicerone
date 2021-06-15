package model;

import utils.Validation;

public abstract class Activity {

    private String title;
    private String slug;
    private boolean privated;
    private Integer order;
    private Section section;

    // Required fields only
    public Activity(String title, String slug) {
        Validation.isNotBlankString(title, "Activity.title");
        Validation.isValidSlug(slug, "Activity.slug");

        this.title = title;
        this.slug = slug;
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

    public boolean isPrivated() {
        return privated;
    }

    public void setPrivated(boolean privated) {
        this.privated = privated;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }
}
