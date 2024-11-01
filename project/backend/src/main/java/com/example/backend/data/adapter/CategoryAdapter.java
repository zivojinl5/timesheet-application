package com.example.backend.data.adapter;

import com.example.backend.data.entity.CategoryEntity;
import com.example.backend.data.repository.ICategoryJPARepository;
import com.example.backend.domain.domain_repository.ICategoryDomainRepository;
import com.example.backend.domain.model.model.Category;
import com.example.backend.mapper.GenericMapper;

import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@AllArgsConstructor
@Repository
public class CategoryAdapter implements ICategoryDomainRepository {

    private final ICategoryJPARepository categoryJPARepository;
    private final GenericMapper genericMapper;

    @Override
    public Page<Category> findAll(PageRequest pageRequest) {
        Page<CategoryEntity> categoryEntityPage = categoryJPARepository
                .findAll(pageRequest);
        Page<Category> categoryPage = genericMapper.mapPage(categoryEntityPage, Category.class);
        return categoryPage;

    }

    @Override
    public List<Category> findAll() {
        List<CategoryEntity> categoryEntities = categoryJPARepository.findAll();
        List<Category> categories = genericMapper.mapList(categoryEntities, Category.class);
        return categories;
    }

    @Override
    public Category findById(Long id) {
        CategoryEntity categoryEntity = categoryJPARepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));
        Category category = genericMapper.mapType(categoryEntity, Category.class);
        return category;

    }

    @Override
    public Category save(Category category) {
        CategoryEntity categoryEntity = genericMapper.mapType(category, CategoryEntity.class);
        CategoryEntity createdCategoryEntity = categoryJPARepository.save(categoryEntity);
        Category createdCategory = genericMapper.mapType(createdCategoryEntity, Category.class);
        return createdCategory;
    }

    @Override
    public Category update(Category category) {
        CategoryEntity categoryEntity = genericMapper.mapType(category, CategoryEntity.class);
        CategoryEntity updatedEntity = categoryJPARepository.save(categoryEntity);
        Category updatedCategory = genericMapper.mapTypeForPatch(updatedEntity, Category.class);
        return updatedCategory;
    }

    @Override
    public void deleteById(Long id) {
        categoryJPARepository.deleteById(id);

    }

}
