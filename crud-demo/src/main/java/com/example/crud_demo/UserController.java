package com.example.crud_demo;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")

public class UserController {
    private final UserService service;
    public UserController(UserService service) {
        this.service = service;

    }
    @GetMapping
    public List<User> getUsers() {
        return service.getAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<User> findById(@PathVariable Long id){
        return service.findById(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return service.createUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        service.deleteById(id);
    }
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User userDetails){
        return service.updateUser(id,userDetails);
    }
    @PatchMapping("/{id}")
    public User patchUser(@PathVariable Long id, @RequestBody User userDetails){
        return service.patchUser(id,userDetails);
    }
}

