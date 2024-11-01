package com.example.backend.domain.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.example.backend.domain.model.create_model.ProjectCreateModel;
import com.example.backend.domain.model.model.Project;
import com.example.backend.domain.model.search_model.ProjectSearchModel;
import com.example.backend.domain.model.update_model.ProjectUpdateModel;

public interface IProjectService {

    List<Project> getAllProjects();

    Page<Project> getAllProjectsWithPaginationAndSorting(PageRequest pageRequest);

    Project getProjectById(Long id);

    Project createProject(ProjectCreateModel projectCreateModel);

    Project updateProject(ProjectUpdateModel projectUpdateModel);

    void deleteProjectById(Long id);

    Page<Project> searchProjects(ProjectSearchModel projectSearchModel);

    List<String> findDistinctStartingLetters();

    List<Project> findProjectsByClientId(Long clientId);

}
