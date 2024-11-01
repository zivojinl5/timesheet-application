package com.example.backend.data.adapter;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.example.backend.data.entity.UserEntity;
import com.example.backend.data.repository.IUserJPARepository;
import com.example.backend.domain.domain_repository.IUserDomainRepository;
import com.example.backend.domain.model.model.User;
import com.example.backend.mapper.GenericMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class UserAdapter implements IUserDomainRepository {
    private final IUserJPARepository userJPARepository;
    private final GenericMapper genericMapper;

    @Override
    public Page<User> findAll(PageRequest pageRequest) {
        Page<UserEntity> userEntityPage = userJPARepository.findAll(pageRequest);
        return genericMapper.mapPage(userEntityPage, User.class);
    }

    @Override
    public List<User> findAll() {
        List<UserEntity> userEntities = userJPARepository.findAll();
        List<User> users = genericMapper.mapList(userEntities, User.class);
        return users;
    }

    @Override
    public User findById(Long id) {
        UserEntity userEntity = userJPARepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        User user = genericMapper.mapType(userEntity, User.class);
        return user;
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = genericMapper.mapType(user, UserEntity.class);
        UserEntity createdUserEntity = userJPARepository.save(userEntity);
        User createdUser = genericMapper.mapType(createdUserEntity, User.class);
        return createdUser;
    }

    @Override
    public User update(User user) {
        UserEntity userEntity = genericMapper.mapType(user, UserEntity.class);
        UserEntity updatedEntity = userJPARepository.save(userEntity);
        User updatedUser = genericMapper.mapTypeForPatch(updatedEntity, User.class);
        return updatedUser;
    }

    @Override
    public void deleteById(Long id) {
        userJPARepository.deleteById(id);
    }

}
