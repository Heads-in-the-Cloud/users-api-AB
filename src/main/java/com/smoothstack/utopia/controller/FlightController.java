package com.smoothstack.utopia.controller;

import com.smoothstack.utopia.entity.Flight;
import com.smoothstack.utopia.service.FlightService;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/flight")
public class FlightController {

    private final FlightService service;

    public FlightController(FlightService service) {
      this.service = service;
    }

    @GetMapping
    public @ResponseBody List<Flight> getAllFlights() {
        return service.selectAll();
    }

    @GetMapping("/{id")
    public ResponseEntity<Flight> getFlightById(@PathVariable final Integer id) {
        final Optional<Flight> flight = service.selectById(id);
        if(flight.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(flight.get());
    }
}
