package com.smoothstack.utopia.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "route")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "origin_id")
    private Airport origin;

    @ManyToOne
    @JoinColumn(name = "destination_id")
    private Airport destination;

    public Integer getId() {
        return id;
    }
    public void setId(final Integer id) {
        this.id = id;
    }
    public Airport getOrigin() {
        return origin;
    }
    public void setOrigin(final Airport origin) {
        this.origin = origin;
    }
    public Airport getDestination() {
        return destination;
    }
    public void setDestination(final Airport destination) {
        this.destination = destination;
    }
}
