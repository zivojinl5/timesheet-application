package com.example.backend.web.controller;

import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.backend.domain.model.model.Category;
import com.example.backend.domain.service.ICategoryService;
import com.example.backend.mapper.GenericMapper;
import com.example.backend.web.dto.create_dto.CategoryCreateDTO;
import com.example.backend.web.dto.dto.CategoryDTO;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/categories")
@CrossOrigin
public class CategoryController {
    private final ICategoryService categoryService;
    private final GenericMapper genericMapper;

    @GetMapping("/pagination/{pageNumber}/{pageSize}/{sortingProperty}/{sortingDirection}")
    public ResponseEntity<Page<CategoryDTO>> getAllCategoriesWithPaginationAndSorting(@PathVariable int pageNumber,
            @PathVariable int pageSize, @PathVariable String sortingProperty, @PathVariable String sortingDirection) {
        Direction direction = sortingDirection.equals("ASC") ? Direction.ASC : Direction.DESC;
        Sort sort = Sort.by(direction, sortingProperty);
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);

        Page<Category> categoryPage = categoryService.getAllCategoriesWithPaginationAndSorting(pageRequest);
        Page<CategoryDTO> categoryDTOPage = genericMapper.mapPage(categoryPage, CategoryDTO.class);
        ResponseEntity<Page<CategoryDTO>> responseEntity = ResponseEntity.ok(categoryDTOPage);
        return responseEntity;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        List<CategoryDTO> categoryDTOs = genericMapper.mapList(categories, CategoryDTO.class);
        ResponseEntity<List<CategoryDTO>> responseEntity = ResponseEntity.ok(categoryDTOs);
        return responseEntity;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable("id") Long id) {
        Category foundCategory = categoryService.getCategoryById(id);
        CategoryDTO foundCategoryDTO = genericMapper.mapType(foundCategory, CategoryDTO.class);
        ResponseEntity<CategoryDTO> responseEntity = ResponseEntity.ok(foundCategoryDTO);
        return responseEntity;
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryCreateDTO categoryCreateDTO) {
        Category category = genericMapper.mapType(categoryCreateDTO, Category.class);
        Category createdCategory = categoryService.createCategory(category);
        CategoryDTO createdCategoryDTO = genericMapper.mapType(createdCategory, CategoryDTO.class);
        ResponseEntity<CategoryDTO> responseEntity = ResponseEntity.ok(createdCategoryDTO);
        return responseEntity;
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable("id") Long id,
            @RequestBody CategoryDTO details) {
        details.setId(id);
        Category category = genericMapper.mapTypeForPatch(details, Category.class);
        Category updatedCategory = categoryService.updateCategory(category);

        CategoryDTO updatedCategoryDTO = genericMapper.mapType(updatedCategory, CategoryDTO.class);
        ResponseEntity<CategoryDTO> responseEntity = ResponseEntity.ok(updatedCategoryDTO);
        return responseEntity;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategoryById(@PathVariable("id") Long id) {
        categoryService.deleteCategoryById(id);
        ResponseEntity<String> responseEntity = ResponseEntity.ok("Category deleted");
        return responseEntity;
    }

}
