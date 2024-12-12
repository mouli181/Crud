package com.example.crud.controller;

import com.example.crud.model.User;
import com.example.crud.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000") // Replace with your frontend URL
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getuser")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    // Create/Register User
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user) {
        String response = userService.registerUser(user);
        if (response.equals("Username already exists!")) {
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        String response = userService.loginUser(user.getUsername(), user.getPassword());
        if (response.equals("Invalid username or password")) {
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }
}
