package com.recipeplatform.recipePlatform.Controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.recipeplatform.recipePlatform.Service.UserService;
import com.recipeplatform.recipePlatform.entity.User;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            return "password is null";
        }

        Optional<User> existingUser = userService.getUserByUsername(user.getUsername());
        if (existingUser.isPresent()) {
            return "Username already exists!";
        }

        User newUser = userService.registerUser(user);
        return "User registered successfully: " + newUser.getUsername();
    }



    @GetMapping("/{id}")
    public Object getUser(@PathVariable Long id, Authentication authentication) {
        Optional<User> user = userService.getUserById(id);

        if (user.isEmpty()) {
            return "User not found!";
        }

        User requestedUser = user.get();
        String currentUsername = authentication.getName();
        Optional<User> currentUser = userService.getUserByUsername(currentUsername);

        if (currentUser.isEmpty()) {
            return "Unauthorized access!";
        }

        User loggedInUser = currentUser.get();

       
        if ("ROLE_ADMIN".equals(loggedInUser.getRole())) {
            return requestedUser;
        }

        
        if ("ROLE_ADMIN".equals(requestedUser.getRole())) {
            return "Access denied!";
        }

       
        return requestedUser;
    }


    
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "User deleted successfully!";
    }
}
