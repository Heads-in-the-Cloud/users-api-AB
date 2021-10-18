package com.smoothstack.utopia.controller;

import com.smoothstack.utopia.NotFoundException;
import com.smoothstack.utopia.entity.AirplaneType;
import com.smoothstack.utopia.service.AirplaneTypeService;

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
@RequestMapping("/airplaneType")
public class AirplaneTypeController {

    private final AirplaneTypeService service;

    public AirplaneTypeController(final AirplaneTypeService service) {
      this.service = service;
    }

    @PostMapping
    public ResponseEntity<AirplaneType> create(@RequestBody final AirplaneType airplaneType) {
        service.save(airplaneType);
        return ResponseEntity.ok(airplaneType);
    }

    @GetMapping
    public @ResponseBody List<AirplaneType> readAll() {
        return service.selectAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AirplaneType> readById(@PathVariable final Integer id) {
        final AirplaneType airplaneType = service.selectById(id).orElseThrow(NotFoundException::new);
        return ResponseEntity.ok(airplaneType);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateById(@PathVariable final Integer id, @RequestBody final AirplaneType airplaneType) {
        if(id != airplaneType.getId()) {
            return new ResponseEntity<String>("AirplaneType ids don't match", HttpStatus.BAD_REQUEST);
        }
        final AirplaneType _ogAirplaneType = service.selectById(id).orElseThrow(NotFoundException::new);
        service.save(airplaneType);
        return ResponseEntity.ok("AirplaneType updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable final Integer id) {
        final AirplaneType airplaneType = service.selectById(id).orElseThrow(NotFoundException::new);
        service.delete(airplaneType);
        return ResponseEntity.noContent().build();
    }
}
