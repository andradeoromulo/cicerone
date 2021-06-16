package model;

import utils.Validation;

public abstract class Activity {

    private String title;
    private String slug;
    private boolean privated;
    private Integer order;
    private Section section;

    // Required fields only
    public Activity(String title, String slug, Section section) {
        Validation.isNotBlankString(title, "Activity.title");
        Validation.isValidSlug(slug, "Activity.slug");
        Validation.isNotNullObject(section, "Activity.section");

        this.title = title;
        this.slug = slug;
        this.section = section;
        this.privated = true;
    }

    public void setPrivated(boolean privated) {
        this.privated = privated;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

}
