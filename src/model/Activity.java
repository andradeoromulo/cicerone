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
        this.setTitle(title);
        this.setSlug(slug);
        this.setPrivated(true);
    }

    public void setTitle(String title) {
        Validation.isNotBlankString(title, "Activity.title");
        this.title = title;
    }

    public void setSlug(String slug) {
        Validation.isValidSlug(slug, "Activity.slug");
        this.slug = slug;
    }

    public void setPrivated(boolean privated) {
        this.privated = privated;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public void setSection(Section section) {
        this.section = section;
    }
}
