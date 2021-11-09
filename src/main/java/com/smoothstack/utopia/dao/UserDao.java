package com.smoothstack.utopia.dao;

import com.smoothstack.utopia.entity.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(final String username);
}

