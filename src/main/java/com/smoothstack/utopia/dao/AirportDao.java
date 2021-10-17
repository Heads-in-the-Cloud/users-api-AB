package com.smoothstack.utopia.dao;

import com.smoothstack.utopia.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportDao extends JpaRepository<Airport, Integer> {}

