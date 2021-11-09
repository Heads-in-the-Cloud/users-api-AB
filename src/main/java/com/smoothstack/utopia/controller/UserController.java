package com.smoothstack.utopia.controller;

import com.smoothstack.utopia.exception.*;
import com.smoothstack.utopia.entity.User;
import com.smoothstack.utopia.service.UserService;

import java.util.List;

import javax.validation.Valid;
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

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;

    @PostMapping
    public ResponseEntity<User> create(@Valid @RequestBody final User user) {
        service.save(user);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public @ResponseBody List<User> readAll() {
        return service.selectAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> readById(@PathVariable final Integer id) {
        final User user = service.selectById(id).orElseThrow(NotFoundException::new);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateById(@PathVariable final Integer id, @Valid @RequestBody final User user) {
        if(id != user.getId()) {
            throw new InvalidUpdateIdException();
        }
        final User _ogUser = service.selectById(id).orElseThrow(NotFoundException::new);
        service.save(user);
        return ResponseEntity.ok("User updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable final Integer id) {
        final User user = service.selectById(id).orElseThrow(NotFoundException::new);
        service.delete(user);
        return ResponseEntity.noContent().build();
    }
}
