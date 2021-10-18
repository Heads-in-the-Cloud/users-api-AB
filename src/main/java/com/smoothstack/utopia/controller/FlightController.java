package com.smoothstack.utopia.controller;

import com.smoothstack.utopia.NotFoundException;
import com.smoothstack.utopia.entity.Flight;
import com.smoothstack.utopia.service.FlightService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping("/flight")
public class FlightController {

    private final FlightService service;

    public FlightController(final FlightService service) {
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
    public ResponseEntity<String> updateFlight(@PathVariable final Integer id, @RequestBody final Flight flight) {
        if(id != flight.getId()) {
            return new ResponseEntity<String>("Entity id's don't match", HttpStatus.BAD_REQUEST);
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
