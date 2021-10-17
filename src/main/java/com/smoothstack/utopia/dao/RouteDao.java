package com.smoothstack.utopia.dao;

import com.smoothstack.utopia.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteDao extends JpaRepository<Route, Integer> {}

