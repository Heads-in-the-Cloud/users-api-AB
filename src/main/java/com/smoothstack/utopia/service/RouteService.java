package com.smoothstack.utopia.service;

import com.smoothstack.utopia.dao.RouteDao;
import com.smoothstack.utopia.entity.Route;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteService {
    private final RouteDao dao;

    public RouteService(final RouteDao dao) {
        this.dao = dao;
    }

    public void save(final Route route) {
        dao.save(route);
    }

    public List<Route> selectAll() {
        return dao.findAll();
    }

    public Optional<Route> selectById(final Integer id) {
        return dao.findById(id);
    }

    public void delete(final Route route) {
        dao.delete(route);
    }
}

