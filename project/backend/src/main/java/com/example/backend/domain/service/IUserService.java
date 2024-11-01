package com.example.backend.domain.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.example.backend.domain.model.model.User;

public interface IUserService {

    List<User> getAllUsers();

    Page<User> getAllUsersWithPaginationAndSorting(PageRequest pageRequest);

    User getUserById(Long id);

    User createUser(User user);

    User updateUser(User user);

    void deleteUserById(Long id);

}
