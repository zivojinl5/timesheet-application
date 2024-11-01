package com.example.backend.data.repository;

import java.util.HashMap;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.backend.data.entity.ClientEntity;
import com.example.backend.domain.model.search_model.ClientSearchModel;

public interface IClientJPARepository extends JpaRepository<ClientEntity, Long> {

        @Query("SELECT c FROM  #{#entityName} c WHERE c.name LIKE CONCAT(:#{T(String).valueOf(#clientSearchModel.startingLetter)}, '%') AND (:#{#clientSearchModel.searchString} IS NULL OR c.name LIKE CONCAT('%', :#{#clientSearchModel.searchString}, '%'))")
        Page<ClientEntity> searchClients(ClientSearchModel clientSearchModel,
                        PageRequest pageRequest);

        @Query("SELECT DISTINCT c.id, c.name FROM ProjectEntity p " +
                        "JOIN p.customer c " +
                        "JOIN p.workers w " +
                        "WHERE w.id = :userId")
        HashMap<Long, String> findClientNamesByUserId(@Param("userId") Long id);

        @Query("SELECT DISTINCT SUBSTRING(c.name, 1, 1) FROM #{#entityName} c")
        List<String> findDistinctStartingLetters();
}
