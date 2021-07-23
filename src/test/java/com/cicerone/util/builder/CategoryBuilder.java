package com.cicerone.util.builder;

import com.cicerone.model.Category;

public class CategoryBuilder {
    private String title;
    private String code;
    private int order;
    private boolean disabled;

    public CategoryBuilder(String title, String code) {
        this.title = title;
        this.code = code;
    }

    public CategoryBuilder orderedAs(int order) {
        this.order = order;
        return this;
    }

    public CategoryBuilder enabled() {
        this.disabled = false;
        return this;
    }

    public CategoryBuilder disabled() {
        this.disabled = true;
        return this;
    }

    public Category build() {
        Category category = new Category(title, code);
        category.setOrder(order);
        if(disabled == false) category.setDisabled(false);
        return category;
    }

}
