package com.example.backend.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.data.entity.UserEntity;

public interface IUserJPARepository extends JpaRepository<UserEntity, Long> {

}
