package com.example.backend.domain.service_Implementation;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.backend.domain.domain_repository.IProjectDomainRepository;
import com.example.backend.domain.model.create_model.ProjectCreateModel;
import com.example.backend.domain.model.model.Project;
import com.example.backend.domain.model.search_model.ProjectSearchModel;
import com.example.backend.domain.model.update_model.ProjectUpdateModel;
import com.example.backend.domain.service.IProjectService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ProjectServiceImpl implements IProjectService {

    private final IProjectDomainRepository projectDomainRepository;

    @Override
    public List<Project> getAllProjects() {
        return projectDomainRepository.findAll();
    }

    @Override
    public Page<Project> getAllProjectsWithPaginationAndSorting(PageRequest pageRequest) {
        return projectDomainRepository.findAll(pageRequest);
    }

    @Override
    public Project getProjectById(Long id) {
        return projectDomainRepository.findById(id);
    }

    @Override
    public Project createProject(ProjectCreateModel projectCreateModel) {
        return projectDomainRepository.save(projectCreateModel);
    }

    @Override
    public Project updateProject(ProjectUpdateModel projectUpdateModel) {
        return projectDomainRepository.update(projectUpdateModel);

    }

    @Override
    public void deleteProjectById(Long id) {
        projectDomainRepository.deleteById(id);
    }

    @Override
    public Page<Project> searchProjects(ProjectSearchModel projectSearchModel) {
        return projectDomainRepository.searchProjects(projectSearchModel);
    }

    @Override
    public List<String> findDistinctStartingLetters() {
        return projectDomainRepository.findDistinctStartingLetters();

    }

    @Override
    public List<Project> findProjectsByClientId(Long clientId) {
        return projectDomainRepository.findProjectsByClientId(clientId);
    }
}
