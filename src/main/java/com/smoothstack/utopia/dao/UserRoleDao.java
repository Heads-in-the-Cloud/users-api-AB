package com.smoothstack.utopia.dao;

import com.smoothstack.utopia.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleDao extends JpaRepository<UserRole, Integer> {}

