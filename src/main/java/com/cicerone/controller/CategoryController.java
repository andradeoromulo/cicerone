package com.cicerone.controller;

import com.cicerone.dto.CategoryForm;
import com.cicerone.dto.SimpleCategoryDTO;
import com.cicerone.model.Category;
import com.cicerone.repository.CategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CategoryController {

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping(value = "/admin/categories")
    public String listCategories(Model model) {
        List<SimpleCategoryDTO> categories = categoryRepository.findAll().stream().map(SimpleCategoryDTO::new).toList();
        model.addAttribute("categories", categories);
        return "admin/listCategories";
    }

    @GetMapping(value = "/admin/categories/new")
    public String newCategoryForm(CategoryForm categoryForm, Model model) {
        model.addAttribute("categoryForm", categoryForm);
        return "admin/newCategory";
    }

    @PostMapping(value = "/admin/categories")
    public String newCategory(@Valid CategoryForm categoryForm, BindingResult result, Model model) {
        if(result.hasErrors()) {
            return newCategoryForm(categoryForm, model);
        }

        Category category = categoryForm.toModel();
        categoryRepository.save(category);

        return "redirect:/admin/categories";
    }

}
