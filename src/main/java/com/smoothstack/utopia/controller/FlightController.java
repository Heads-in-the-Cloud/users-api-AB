package com.smoothstack.utopia.controller;

import com.smoothstack.utopia.NotFoundException;
import com.smoothstack.utopia.entity.Flight;
import com.smoothstack.utopia.service.FlightService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/flight")
public class FlightController {

    private final FlightService service;

    public FlightController(FlightService service) {
      this.service = service;
    }

    @PostMapping
    public ResponseEntity<Flight> createFlight(@RequestBody final Flight flight) {
        service.save(flight);
        return ResponseEntity.ok(flight);
    }

    @GetMapping
    public @ResponseBody List<Flight> readAllFlights() {
        return service.selectAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Flight> readFlightById(@PathVariable final Integer id) {
        final Flight flight = service.selectById(id).orElseThrow(NotFoundException::new);
        return ResponseEntity.ok(flight);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateFlight(@RequestBody final Flight flight) {
        if(id != flight.getId()) {
            return ResponseEntity.badRequest("Entity id's don't match").build();
        }
        final Flight _ogFlight = service.selectById(id).orElseThrow(NotFoundException::new);
        service.save(flight);
        return ResponseEntity.ok("Flight updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable final Integer id) {
        final Flight flight = service.selectById(id).orElseThrow(NotFoundException::new);
        service.delete(flight);
        return ResponseEntity.noContent().build();
    }
}
