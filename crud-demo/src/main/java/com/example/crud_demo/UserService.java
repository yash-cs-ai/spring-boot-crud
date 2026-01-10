package com.example.crud_demo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repo;

    public UserService(UserRepository repo){
        this.repo = repo;
    }

    public List<User> getAllUsers(){
        return repo.findAll();
    }

    public User createUser (User user){
        return repo.save(user);
    }

    public Optional<User> findById(Long id){return repo.findById(id); }

    public void deleteById(Long id){repo.deleteById(id);}

    public User updateUser(Long id, User userDetails){
        User currentUser = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        currentUser.setName(userDetails.getName());
        currentUser.setEmail(userDetails.getEmail());
        return repo.save(currentUser);
    }
    @Transactional
    public User patchUser(Long id, User userDetails){
        User currentUser = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        System.out.println(currentUser.getEmail());
        System.out.println(currentUser.getName());

        if (userDetails.getEmail() != null) {
            currentUser.setEmail(userDetails.getEmail());
        }
        if (userDetails.getName() != null) {
            currentUser.setName(userDetails.getName());
        }

        return repo.save(currentUser);
    }

}
