package com.smoothstack.utopia.service;

import com.smoothstack.utopia.dao.AirplaneDao;
import com.smoothstack.utopia.entity.Airplane;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirplaneService {
    private final AirplaneDao dao;

    public AirplaneService(final AirplaneDao dao) {
        this.dao = dao;
    }

    public void save(final Airplane airplane) {
        dao.save(airplane);
    }

    public List<Airplane> selectAll() {
        return dao.findAll();
    }

    public Optional<Airplane> selectById(final Integer id) {
        return dao.findById(id);
    }

    public void delete(final Airplane airplane) {
        dao.delete(airplane);
    }
}

