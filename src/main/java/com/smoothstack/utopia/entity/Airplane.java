package com.smoothstack.utopia.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "airplane")
public class Airplane {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private AirplaneType type;

    public Airplane() {
        this.type = new AirplaneType();
    }
    public Integer getId() {
        return id;
    }
    public void setId(final Integer id) {
        this.id = id;
    }
    public AirplaneType getType() {
        return type;
    }
    public void setType(final AirplaneType type) {
        this.type = type;
    }
}
