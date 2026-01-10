package com.example.crud_demo;


import org.springframework.http.HttpHeaders;
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

    @RequestMapping(value = "/{id}", method = RequestMethod.HEAD)
    public ResponseEntity<Void> userExists(@PathVariable Long id){
        boolean exists = service.existById(id);
        if(exists){
            return ResponseEntity.ok().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.OPTIONS)
        public ResponseEntity<Void> optionsUserbyId () {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Allow", "GET, PUT, DELETE, HEAD, OPTIONS");
            headers.add("Access-Control-Allow-Methods", "GET, PUT, DELETE, HEAD, OPTIONS");
            headers.add("Access-Control-Allow-Headers", "Content type, Authorization");
            headers.add("Access-Control-Allow-Origin", "*");

            return ResponseEntity.ok().headers(headers).build();
        }
    }


