package com.example.backend.domain.domain_repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.example.backend.domain.model.model.User;

public interface IUserDomainRepository {

    Page<User> findAll(PageRequest pageRequest);

    List<User> findAll();

    User findById(Long id);

    User save(User user);

    User update(User user);

    void deleteById(Long id);

}
