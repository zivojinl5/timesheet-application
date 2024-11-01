package com.example.backend.data.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.backend.data.entity.ProjectEntity;
import com.example.backend.domain.model.search_model.ProjectSearchModel;

public interface IProjectJPARepository extends JpaRepository<ProjectEntity, Long> {

        @Query("SELECT p FROM #{#entityName} p WHERE p.customer.id = :clientId")
        List<ProjectEntity> findProjectsByClientId(@Param("clientId") Long clientId);

        @Query("SELECT p FROM #{#entityName} p WHERE p.name LIKE CONCAT(:#{T(String).valueOf(#projectSearchModel.startingLetter)}, '%')"
                        +
                        " AND (:#{#projectSearchModel.searchString} IS NULL OR p.name LIKE CONCAT('%', :#{#projectSearchModel.searchString}, '%'))")
        Page<ProjectEntity> searchProjects(ProjectSearchModel projectSearchModel,
                        PageRequest pageRequest);

        @Query("SELECT DISTINCT SUBSTRING(p.name, 1, 1) FROM #{#entityName} p")
        List<String> findDistinctStartingLetters();
}
