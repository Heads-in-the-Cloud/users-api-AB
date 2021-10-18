package com.smoothstack.utopia.dao;

import com.smoothstack.utopia.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightDao extends JpaRepository<Flight, Integer> {}

