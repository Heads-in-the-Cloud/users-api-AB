package com.smoothstack.utopia.dao;

import com.smoothstack.utopia.entity.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirplaneDao extends JpaRepository<Airplane, Integer> {}

