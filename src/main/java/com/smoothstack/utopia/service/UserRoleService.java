package com.smoothstack.utopia.service;

import com.smoothstack.utopia.dao.UserRoleDao;
import com.smoothstack.utopia.entity.UserRole;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserRoleService {
    private final UserRoleDao dao;

    public UserRoleService(final UserRoleDao dao) {
        this.dao = dao;
    }

    public void save(final UserRole userRole) {
        dao.save(userRole);
    }

    public List<UserRole> selectAll() {
        return dao.findAll();
    }

    public Optional<UserRole> selectById(final Integer id) {
        return dao.findById(id);
    }

    public void delete(final UserRole userRole) {
        dao.delete(userRole);
    }
}

