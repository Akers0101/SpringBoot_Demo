package akers.mongodb.controller;

import akers.mongodb.model.User;
import akers.mongodb.services.IUserService;
import akers.mongodb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    IUserService iUserService;

    @GetMapping("/users")
    public List<User> getAll() {
       return iUserService.getAll();
    }
    @GetMapping("/users/{id}")
    public User getById(@PathVariable("id") String id) {
        return iUserService.getById(id);
    }
    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        iUserService.add(user);
        return user;
    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable("id") String id, @RequestBody User user) {
        iUserService.update(id,user);
        return user;
    }
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable("id") String id) {
        iUserService.delete(id);
    }

}

