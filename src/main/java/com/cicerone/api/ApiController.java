package com.cicerone.api;

import com.cicerone.category.CategoryApiDTO;
import com.cicerone.category.CategoryRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final CategoryRepository categoryRepository;

    public ApiController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping(value = "/categories", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<CategoryApiDTO>> getAllCategories() {
        List<CategoryApiDTO> categories = categoryRepository.findAllEnabled().stream().map(CategoryApiDTO::new).toList();
        return ResponseEntity.ok(categories);
    }

}
