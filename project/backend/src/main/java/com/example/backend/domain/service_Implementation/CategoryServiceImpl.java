package com.example.backend.domain.service_Implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.backend.domain.domain_repository.ICategoryDomainRepository;
import com.example.backend.domain.model.model.Category;
import com.example.backend.domain.service.ICategoryService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private final ICategoryDomainRepository categoryDomainRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryDomainRepository.findAll();

    }

    @Override
    public Page<Category> getAllCategoriesWithPaginationAndSorting(PageRequest pageRequest) {
        return categoryDomainRepository.findAll(pageRequest);
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryDomainRepository.findById(id);
    }

    @Override
    public Category createCategory(Category category) {
        return categoryDomainRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category) {
        return categoryDomainRepository.update(category);
    }

    @Override
    public void deleteCategoryById(Long id) {
        categoryDomainRepository.deleteById(id);
    }

}
