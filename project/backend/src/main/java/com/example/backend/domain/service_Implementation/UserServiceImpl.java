package com.example.backend.domain.service_Implementation;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.backend.domain.domain_repository.IUserDomainRepository;
import com.example.backend.domain.model.model.User;
import com.example.backend.domain.service.IUserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserServiceImpl implements IUserService {

    private final IUserDomainRepository userDomainRepository;

    @Override
    public List<User> getAllUsers() {
        return userDomainRepository.findAll();
    }

    @Override
    public Page<User> getAllUsersWithPaginationAndSorting(PageRequest pageRequest) {
        return userDomainRepository.findAll(pageRequest);
    }

    @Override
    public User getUserById(Long id) {
        return userDomainRepository.findById(id);
    }

    @Override
    public User createUser(User user) {
        return userDomainRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userDomainRepository.update(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userDomainRepository.deleteById(id);
    }

}
