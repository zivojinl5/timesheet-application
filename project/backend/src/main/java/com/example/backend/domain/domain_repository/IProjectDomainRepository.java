package com.example.backend.domain.domain_repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.example.backend.domain.model.create_model.ProjectCreateModel;
import com.example.backend.domain.model.model.Project;
import com.example.backend.domain.model.search_model.ProjectSearchModel;
import com.example.backend.domain.model.update_model.ProjectUpdateModel;

public interface IProjectDomainRepository {
Page<Project> searchProjects(ProjectSearchModel projectSearchModel);

    List<String> findDistinctStartingLetters();

    List<Project> findProjectsByClientId(Long clientId);
    Page<Project> findAll(PageRequest pageRequest);

    List<Project> findAll();

    Project findById(Long id);

    Project save(ProjectCreateModel projectCreateModel);

    Project update(ProjectUpdateModel project);

    void deleteById(Long id);

    
}