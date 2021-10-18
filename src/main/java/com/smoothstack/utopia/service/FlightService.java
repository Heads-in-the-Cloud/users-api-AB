package com.smoothstack.utopia.service;

import com.smoothstack.utopia.dao.FlightDao;
import com.smoothstack.utopia.entity.Flight;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {
    private final FlightDao dao;

    public FlightService(final FlightDao dao) {
        this.dao = dao;
    }

    public void save(final Flight flight) {
        dao.save(flight);
    }

    public List<Flight> selectAll() {
        return dao.findAll();
    }

    public Optional<Flight> selectById(final Integer id) {
        return dao.findById(id);
    }

    public void delete(final Flight flight) {
        dao.delete(flight);
    }
}

