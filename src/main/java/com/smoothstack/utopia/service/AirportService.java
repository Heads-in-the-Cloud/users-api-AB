package com.smoothstack.utopia.service;

import com.smoothstack.utopia.dao.AirportDao;
import com.smoothstack.utopia.entity.Airport;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportService {
    private final AirportDao dao;

    public AirportService(final AirportDao dao) {
        this.dao = dao;
    }

    public void save(final Airport airport) {
        dao.save(airport);
    }

    public List<Airport> selectAll() {
        return dao.findAll();
    }

    public Optional<Airport> selectByCode(final String code) {
        return dao.findById(code);
    }

    public void delete(final Airport airport) {
        dao.delete(airport);
    }
}

