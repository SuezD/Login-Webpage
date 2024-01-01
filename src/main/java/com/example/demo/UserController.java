package com.example.demo;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response){
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        if (username == null || password == null){
            return ResponseEntity.badRequest().body("Username and Password must be provided");
        }
        if (username == "" || password == ""){
            return ResponseEntity.badRequest().body("Username and Password must not be blank");
        }

        // Check if the username is already taken
        User user = userRepository.findByUsername(username.toLowerCase());
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found");
        }

        if (!new BCryptPasswordEncoder().matches(password, user.getPassword())){
            System.out.println(user.getPassword());
            System.out.println(password);
            return ResponseEntity.badRequest().body("Incorrect password");
        }

        // Generate token and return as cookie
        String accessToken = generateAccessToken(user);
        Cookie cookie = new Cookie("access_token", user.getUsername());
        cookie.setPath("/"); // Set the cookie path as needed
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        response.addCookie(cookie); 
        return ResponseEntity.ok("Login Successful");
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequest signupRequest, HttpServletResponse response){
        String username = signupRequest.getUsername();
        String password = signupRequest.getPassword();
        String firstname = signupRequest.getFirstName();

        if (username == null || password == null || firstname == null){
            return ResponseEntity.badRequest().body("Username, Password and First Name must be provided");
        }
        if (username == "" || password == "" || firstname == ""){
            return ResponseEntity.badRequest().body("Username, Password and First Name must not be blank");
        }

        // Check if the username is already taken
        if (userRepository.findByUsername(username) != null) {
            return ResponseEntity.badRequest().body("Username already exists");
        }

        // Hash password
        String hashedPassword = new BCryptPasswordEncoder().encode(password);

        // Create a new user
        User user = new User();
        user.setUsername(username.toLowerCase());
        user.setPassword(hashedPassword);
        user.setFirstName(firstname);

        // Save the user to MongoDB
        userRepository.save(user);

        // generate token and return in cookie
        String accessToken = generateAccessToken(user);
        Cookie cookie = new Cookie("access_token", user.getUsername());
        cookie.setPath("/"); // Set the cookie path
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        response.addCookie(cookie); 
        
        return ResponseEntity.ok("Sign Up Successful");
    }

    @PostMapping("/user-details")
    private ResponseEntity<User> userDetails(@CookieValue(name = "access_token", required = true) String accessToken) {
        if (isValidToken(accessToken)) {
            User user = fetchUserDetails(accessToken);
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @DeleteMapping("/delete-account")
    public ResponseEntity<String> deleteUser(@CookieValue(name = "access_token", required = true) String accessToken) {
        try {
            // Perform the deletion in MongoDB based on the userId
            var id = userRepository.findByUsername(accessToken).getId();
            System.out.println(id);
            userRepository.deleteById(id);
            return ResponseEntity.ok("User deleted successfully");
        } catch (Exception e) {
            // Handle exceptions, log, and return an error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting user");
        }
    }

    private String generateAccessToken(User user) {
        // Implement logic to generate secure JWT
        return UUID.randomUUID().toString();
    }

    private boolean isValidToken(String accessToken) {
        // Token Validation
        return accessToken != null && !accessToken.isEmpty();
    }

    private User fetchUserDetails(String username) {
        // Should decrypt token and extract details from there (possibly?)
        // String username = "kimkil";
        User user = userRepository.findByUsername(username);
        return user;
    }
}
