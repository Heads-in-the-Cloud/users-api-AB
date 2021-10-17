package com.smoothstack.utopia.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "flight")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Route route;
    private Airplane airplane;
    private Timestamp timeOfDeparture;
    private Timestamp timeOfArrival;

    public Flight() {
        this.airplane = new Airplane();
        this.route = new Route();
    }
    public Integer getId() {
        return id;
    }
    public void setId(final Integer id) {
        this.id = id;
    }
    public Route getRoute() {
        return route;
    }
    public void setRoute(final Route route) {
        this.route = route;
    }
    public Airplane getAirplane() {
        return airplane;
    }
    public void setAirplane(final Airplane airplane) {
        this.airplane = airplane;
    }
    public Timestamp getTimeOfDeparture() {
        return timeOfDeparture;
    }
    public void setTimeOfDeparture(final Timestamp timeOfDeparture) {
        this.timeOfDeparture = timeOfDeparture;
    }
    public Timestamp getTimeOfArrival() {
        return timeOfArrival;
    }
    public void setTimeOfArrival(final Timestamp timeOfArrival) {
        this.timeOfArrival = timeOfArrival;
    }
}
