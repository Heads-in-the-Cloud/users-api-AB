package com.smoothstack.utopia.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity
@Table(name = "airport")
public class Airport {
    @Id
    @Column(name = "iata_id")
    private String code;

    private String city;

    public String getCode() {
        return code;
    }
    public void setCode(final String code) {
        this.code = code;
    }
    public String getCity() {
        return city;
    }
    public void setCity(final String city) {
        this.city = city;
    }
}
