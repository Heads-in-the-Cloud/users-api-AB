package com.smoothstack.utopia.service;

import com.smoothstack.utopia.dao.UserDao;
import com.smoothstack.utopia.entity.User;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserDao dao;

    public UserService(final UserDao dao) {
        this.dao = dao;
    }

    public void save(final User user) {
        dao.save(user);
    }

    public List<User> selectAll() {
        return dao.findAll();
    }

    public Optional<User> selectById(final Integer id) {
        return dao.findById(id);
    }

    public void delete(final User user) {
        dao.delete(user);
    }
}

