package com.cicerone.util.builder;

import com.cicerone.model.Category;
import com.cicerone.model.Subcategory;

public class SubcategoryBuilder {
    private String title;
    private String code;
    private Category parentCategory;
    private int order;
    private boolean disabled;

    public SubcategoryBuilder(String title, String code, Category parentCategory) {
        this.title = title;
        this.code = code;
        this.parentCategory = parentCategory;
    }

    public SubcategoryBuilder orderedAs(int order) {
        this.order = order;
        return this;
    }

    public SubcategoryBuilder enabled() {
        this.disabled = false;
        return this;
    }

    public SubcategoryBuilder disabled() {
        this.disabled = true;
        return this;
    }

    public Subcategory build() {
        Subcategory subcategory = new Subcategory(title, code, parentCategory);
        subcategory.setOrder(order);
        if(disabled == false) subcategory.setDisabled(false);
        return subcategory;
    }

}
