package com.example.crud.service;

import com.example.crud.model.User;
import com.example.crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return "Email already exists!";
        }
        userRepository.save(user);
        return "User registered successfully!";
    }


    public String loginUser(String email, String password) {
        User existingUser = userRepository.findByEmail(email);
        if (existingUser == null || !existingUser.getPassword().equals(password)) {
            return "Invalid username or password";
        }
        return "Welcome, " + existingUser.getUsername() + "!";
    }

    public List<User> getUsers() {
       return userRepository.findAll();
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
