package com.smoothstack.utopia.controller;

import com.smoothstack.utopia.NotFoundException;
import com.smoothstack.utopia.entity.UserRole;
import com.smoothstack.utopia.service.UserRoleService;

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
@RequestMapping("/user-role")
public class UserRoleController {

    private final UserRoleService service;

    public UserRoleController(final UserRoleService service) {
      this.service = service;
    }

    @PostMapping
    public ResponseEntity<UserRole> create(@RequestBody final UserRole userRole) {
        service.save(userRole);
        return ResponseEntity.ok(userRole);
    }

    @GetMapping
    public @ResponseBody List<UserRole> readAll() {
        return service.selectAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRole> readById(@PathVariable final Integer id) {
        final UserRole userRole = service.selectById(id).orElseThrow(NotFoundException::new);
        return ResponseEntity.ok(userRole);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateById(@PathVariable final Integer id, @RequestBody final UserRole userRole) {
        if(id != userRole.getId()) {
            return new ResponseEntity<String>("UserRole ids don't match", HttpStatus.BAD_REQUEST);
        }
        final UserRole _ogUserRole = service.selectById(id).orElseThrow(NotFoundException::new);
        service.save(userRole);
        return ResponseEntity.ok("UserRole updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable final Integer id) {
        final UserRole userRole = service.selectById(id).orElseThrow(NotFoundException::new);
        service.delete(userRole);
        return ResponseEntity.noContent().build();
    }
}
