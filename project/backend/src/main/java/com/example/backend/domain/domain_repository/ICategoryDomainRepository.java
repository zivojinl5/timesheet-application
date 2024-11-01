package com.example.backend.domain.domain_repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.example.backend.domain.model.model.Category;

public interface ICategoryDomainRepository {

    Page<Category> findAll(PageRequest pageRequest);

    List<Category> findAll();

    Category findById(Long id);

    Category save(Category category);

    Category update(Category category);

    void deleteById(Long id);

}