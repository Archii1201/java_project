package com.recipeplatform.recipePlatform.Service;

import com.recipeplatform.recipePlatform.Repository.UserRepository;
import com.recipeplatform.recipePlatform.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

    }

    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.setRole("ROLE_USER"); // Default role
        user.setEnabled(true);     // Ensure user is active
        return userRepository.save(user);
    }

    public User upgradeToPremium(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
       user.setPremium(true);
        return userRepository.save(user);
   }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
   
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
