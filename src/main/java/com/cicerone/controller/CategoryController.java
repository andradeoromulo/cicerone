package com.cicerone.controller;

import com.cicerone.dto.CategoryForm;
import com.cicerone.dto.SimpleCategoryDTO;
import com.cicerone.model.Category;
import com.cicerone.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;

@Controller
public class CategoryController {

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/admin/categories")
    public String listCategories(Model model) {
        List<SimpleCategoryDTO> categories = categoryRepository.findAll().stream().map(SimpleCategoryDTO::new).toList();
        model.addAttribute("categories", categories);
        return "admin/categories/list";
    }

    @GetMapping("/admin/categories/new")
    public String newCategoryForm(Model model) {
        model.addAttribute("categoryForm", new CategoryForm());
        return "admin/categories/new";
    }

    @PostMapping("/admin/categories")
    public String newCategory(@Valid CategoryForm categoryForm, BindingResult result) {
        if(result.hasErrors()) {
            return "admin/categories/new";
        }

        Category category = categoryForm.toModel();
        categoryRepository.save(category);

        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/{categoryCode}")
    public String editCategoryForm(@PathVariable("categoryCode") String code, Model model) {
        Category category = categoryRepository.findByCode(code).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Category with code %s not found".formatted(code)));

        model.addAttribute("categoryForm", new CategoryForm(category));
        return "admin/categories/edit";
    }

    @PostMapping("/admin/categories/{categoryCode}")
    public String editCategory(@PathVariable("categoryCode") String code, @Valid CategoryForm categoryForm, BindingResult result) {
        if(result.hasErrors()) {
            return "admin/categories/edit";
        }

        Category category = categoryForm.toModel();
        categoryRepository.save(category);

        return "redirect:/admin/categories";
    }

}
