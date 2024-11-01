package com.example.backend.web.controller;

import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.backend.domain.model.create_model.ProjectCreateModel;
import com.example.backend.domain.model.model.Project;
import com.example.backend.domain.model.search_model.ProjectSearchModel;
import com.example.backend.domain.model.update_model.ProjectUpdateModel;
import com.example.backend.domain.service.IProjectService;
import com.example.backend.mapper.GenericMapper;
import com.example.backend.web.dto.create_dto.ProjectCreateDTO;
import com.example.backend.web.dto.dto.ProjectDTO;
import com.example.backend.web.dto.update_dto.ProjectUpdateDTO;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/projects")
@CrossOrigin
public class ProjectController {
    private final IProjectService projectService;
    private final GenericMapper genericMapper;

    @GetMapping("/startingLetters")
    public List<String> getStartingLetters() {
        return projectService.findDistinctStartingLetters();
    }

    @GetMapping("/search/{startingLetter}/{pageNumber}/{pageSize}/{sortingProperty}/{sortingDirection}")
    public ResponseEntity<Page<ProjectDTO>> searchProjects(@PathVariable String startingLetter,
            @PathVariable int pageNumber, @PathVariable int pageSize, @PathVariable String sortingProperty,
            @PathVariable String sortingDirection, @RequestParam(required = false) String searchString) {

        Direction direction = sortingDirection.equals("ASC") ? Direction.ASC : Direction.DESC;
        Sort sort = Sort.by(direction, sortingProperty);
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);

        ProjectSearchModel projectSearchModel = new ProjectSearchModel();
        projectSearchModel.setPageRequest(pageRequest);
        projectSearchModel.setStartingLetter(startingLetter);
        projectSearchModel.setSearchString(searchString);

        Page<Project> projectPage = projectService.searchProjects(projectSearchModel);
        Page<ProjectDTO> ProjectDTOPage = genericMapper.mapPage(projectPage, ProjectDTO.class);
        ResponseEntity<Page<ProjectDTO>> responseEntity = ResponseEntity.ok(ProjectDTOPage);
        return responseEntity;
    }

    @GetMapping("/pagination/{pageNumber}/{pageSize}/{sortingProperty}/{sortingDirection}")
    public ResponseEntity<Page<ProjectDTO>> getAllClientsWithPaginationAndSorting(@PathVariable int pageNumber,
            @PathVariable int pageSize, @PathVariable String sortingProperty, @PathVariable String sortingDirection) {
        Direction direction = sortingDirection.equals("ASC") ? Direction.ASC : Direction.DESC;
        Sort sort = Sort.by(direction, sortingProperty);
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);

        Page<Project> projectPage = projectService.getAllProjectsWithPaginationAndSorting(pageRequest);
        Page<ProjectDTO> ProjectDTOPage = genericMapper.mapPage(projectPage, ProjectDTO.class);
        ResponseEntity<Page<ProjectDTO>> responseEntity = ResponseEntity.ok(ProjectDTOPage);
        return responseEntity;
    }

    @GetMapping
    public ResponseEntity<List<ProjectDTO>> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        List<ProjectDTO> projectDTOs = genericMapper.mapList(projects, ProjectDTO.class);
        ResponseEntity<List<ProjectDTO>> responseEntity = ResponseEntity.ok(projectDTOs);
        return responseEntity;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO> getProjectById(@PathVariable("id") Long id) {
        Project foundProject = projectService.getProjectById(id);
        ProjectDTO foundProjectDTO = genericMapper.mapType(foundProject, ProjectDTO.class);
        ResponseEntity<ProjectDTO> responseEntity = ResponseEntity.ok(foundProjectDTO);
        return responseEntity;
    }

    @PostMapping
    public ResponseEntity<ProjectDTO> createProject(@RequestBody ProjectCreateDTO projectCreateDTO) {
        ProjectCreateModel projectCreateModel = genericMapper.mapType(projectCreateDTO, ProjectCreateModel.class);
        Project createdProject = projectService.createProject(projectCreateModel);
        ProjectDTO createdProjectDTO = genericMapper.mapType(createdProject, ProjectDTO.class);
        return ResponseEntity.ok(createdProjectDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProjectDTO> updateProject(@PathVariable("id") Long id,
            @RequestBody ProjectUpdateDTO details) {
        details.setId(id);
        ProjectUpdateModel projectUpdateModel = genericMapper.mapTypeForPatch(details, ProjectUpdateModel.class);
        Project updatedProject = projectService.updateProject(projectUpdateModel);

        ProjectDTO updatedProjectDTO = genericMapper.mapType(updatedProject, ProjectDTO.class);
        ResponseEntity<ProjectDTO> responseEntity = ResponseEntity.ok(updatedProjectDTO);
        return responseEntity;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProjectById(@PathVariable("id") Long id) {
        projectService.deleteProjectById(id);
        ResponseEntity<String> responseEntity = ResponseEntity.ok("Project deleted");
        return responseEntity;
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<ProjectDTO>> getProjectsByClientId(@PathVariable("clientId") Long clientId) {
        List<Project> projects = projectService.findProjectsByClientId(clientId);
        List<ProjectDTO> projectDTOs = genericMapper.mapList(projects, ProjectDTO.class);
        ResponseEntity<List<ProjectDTO>> responseEntity = ResponseEntity.ok(projectDTOs);
        return responseEntity;
    }
}
