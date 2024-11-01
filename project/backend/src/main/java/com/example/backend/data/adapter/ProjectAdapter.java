package com.example.backend.data.adapter;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.example.backend.data.entity.ClientEntity;
import com.example.backend.data.entity.ProjectEntity;
import com.example.backend.data.entity.UserEntity;
import com.example.backend.data.repository.IClientJPARepository;
import com.example.backend.data.repository.IProjectJPARepository;
import com.example.backend.data.repository.IUserJPARepository;
import com.example.backend.domain.domain_repository.IProjectDomainRepository;
import com.example.backend.domain.model.create_model.ProjectCreateModel;
import com.example.backend.domain.model.model.Project;
import com.example.backend.domain.model.search_model.ProjectSearchModel;
import com.example.backend.domain.model.update_model.ProjectUpdateModel;
import com.example.backend.mapper.GenericMapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Repository
public class ProjectAdapter implements IProjectDomainRepository {
    private final IProjectJPARepository projectJPARepository;
    private final IClientJPARepository clientJPARepository;
    private final IUserJPARepository userJPARepository;
    private final GenericMapper genericMapper;

    @Override
    public List<String> findDistinctStartingLetters() {
        return projectJPARepository.findDistinctStartingLetters();
    }

    @Override
    public List<Project> findProjectsByClientId(Long clientId) {
        List<ProjectEntity> projectEntities = projectJPARepository.findProjectsByClientId(clientId);
        List<Project> projects = genericMapper.mapList(projectEntities, Project.class);

        return projects;
    }

    @Override
    public Page<Project> searchProjects(ProjectSearchModel projectSearchModel) {
        PageRequest pageRequest = projectSearchModel.getPageRequest();
        Page<ProjectEntity> projectEntityPage = projectJPARepository.searchProjects(projectSearchModel, pageRequest);
        Page<Project> projectPage = genericMapper.mapPage(projectEntityPage, Project.class);
        return projectPage;
    }

    @Override
    public Page<Project> findAll(PageRequest pageRequest) {
        Page<ProjectEntity> projectEntityPage = projectJPARepository.findAll(pageRequest);
        return genericMapper.mapPage(projectEntityPage, Project.class);
    }

    @Override
    public List<Project> findAll() {
        List<ProjectEntity> projectEntities = projectJPARepository.findAll();
        List<Project> projects = genericMapper.mapList(projectEntities, Project.class);
        return projects;
    }

    @Override
    public Project findById(Long id) {
        ProjectEntity projectEntity = projectJPARepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));
        Project project = genericMapper.mapType(projectEntity, Project.class);
        return project;
    }

    @Override
    public Project save(ProjectCreateModel projectCreateModel) {
        ProjectEntity projectEntity = genericMapper.mapType(projectCreateModel, ProjectEntity.class);
        ClientEntity clientEntity = clientJPARepository.findById(projectCreateModel.getClientId())
                .orElseThrow(() -> new IllegalArgumentException("Client not found"));
        UserEntity userEntity = userJPARepository.findById(projectCreateModel.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        projectEntity.setCustomer(clientEntity);
        projectEntity.setLead(userEntity);
        ProjectEntity createdProjectEntity = projectJPARepository.save(projectEntity);
        Project createdProject = genericMapper.mapType(createdProjectEntity, Project.class);
        return createdProject;
    }

    @Override
    public Project update(ProjectUpdateModel projectUpdateModel) {
        ProjectEntity existingProjectEntity = projectJPARepository.findById(projectUpdateModel.getId())
                .orElseThrow(() -> new IllegalArgumentException("No project with that id."));
        existingProjectEntity = genericMapper.mapTypeForPatch(projectUpdateModel, ProjectEntity.class);

        if (projectUpdateModel.getUserId() != null) {
            UserEntity userEntity = userJPARepository.findById(projectUpdateModel.getUserId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
            existingProjectEntity.setLead(userEntity);
        }
        ProjectEntity updatedEntity = projectJPARepository.save(existingProjectEntity);
        Project updatedProject = genericMapper.mapType(updatedEntity, Project.class);
        return updatedProject;
    }

    @Override
    public void deleteById(Long id) {
        projectJPARepository.deleteById(id);
    }

}