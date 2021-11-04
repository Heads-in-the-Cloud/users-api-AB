package com.smoothstack.utopia.dao;

import com.smoothstack.utopia.entity.UserEntity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByUsername(final String username);
}

