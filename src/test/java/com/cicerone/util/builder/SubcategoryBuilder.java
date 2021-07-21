package com.cicerone.util.builder;

import com.cicerone.model.Category;
import com.cicerone.model.Subcategory;

public class SubcategoryBuilder {
    Subcategory subcategory;

    public SubcategoryBuilder(String title, String code, Category parentCategory) {
        this.subcategory = new Subcategory(title, code, parentCategory);
    }

    public SubcategoryBuilder orderedAs(int order) {
        this.subcategory.setOrder(order);
        return this;
    }

    public SubcategoryBuilder enabled() {
        this.subcategory.setDisabled(false);
        return this;
    }

    public SubcategoryBuilder disabled() {
        this.subcategory.setDisabled(true);
        return this;
    }

    public Subcategory build() {
        return this.subcategory;
    }

}
