package com.smoothstack.utopia.service;

import com.smoothstack.utopia.dao.AirplaneTypeDao;
import com.smoothstack.utopia.entity.AirplaneType;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirplaneTypeService {
    private final AirplaneTypeDao dao;

    public AirplaneTypeService(final AirplaneTypeDao dao) {
        this.dao = dao;
    }

    public void save(final AirplaneType airplaneType) {
        dao.save(airplaneType);
    }

    public List<AirplaneType> selectAll() {
        return dao.findAll();
    }

    public Optional<AirplaneType> selectById(final Integer id) {
        return dao.findById(id);
    }

    public void delete(final AirplaneType airplaneType) {
        dao.delete(airplaneType);
    }
}

