package com.example.Two.service;

import com.example.Two.model.User;
import com.example.Two.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    public String save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "Created user...";
    }

    public String delete(Long id) {
        userRepository.deleteById(id);
        return "User with id: " + id + " got deleted.";
    }
}
