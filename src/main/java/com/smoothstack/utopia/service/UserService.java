package com.smoothstack.utopia.service;

import com.smoothstack.utopia.dao.UserDao;
import com.smoothstack.utopia.entity.UserEntity;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserDao dao;

    public UserService(final UserDao dao) {
        this.dao = dao;
    }

    public void save(final UserEntity user) {
        dao.save(user);
    }

    public List<UserEntity> selectAll() {
        return dao.findAll();
    }

    public Optional<UserEntity> selectById(final Integer id) {
        return dao.findById(id);
    }

    public void delete(final UserEntity user) {
        dao.delete(user);
    }
}

