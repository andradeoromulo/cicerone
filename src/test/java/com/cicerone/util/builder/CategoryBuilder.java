package com.cicerone.util.builder;

import com.cicerone.model.Category;

public class CategoryBuilder {
    Category category;

    public CategoryBuilder(String title, String code) {
        this.category = new Category(title, code);
    }

    public CategoryBuilder orderedAs(int order) {
        this.category.setOrder(order);
        return this;
    }

    public CategoryBuilder enabled() {
        this.category.setDisabled(false);
        return this;
    }

    public CategoryBuilder disabled() {
        this.category.setDisabled(true);
        return this;
    }

    public Category build() {
        return this.category;
    }

}
