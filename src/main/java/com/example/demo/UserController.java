package com.example.demo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest){
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        if (username == null || password == null){
            return ResponseEntity.badRequest().body("Username and Password must be provided");
        }
        if (username == "" || password == ""){
            return ResponseEntity.badRequest().body("Username and Password must not be blank");
        }

        // authentication logic
        // Check if the username is already taken
        if (userRepository.findByUsername(username) == null) {
            return ResponseEntity.badRequest().body("User not found");
        }
        if (!userRepository.findByUsername(username).getPassword().equals(password)){
            System.out.println(userRepository.findByUsername(username).getPassword());
            return ResponseEntity.badRequest().body("Incorrect password");
        }
        // generate token and return in cookie?

        return ResponseEntity.ok("Login Successful"); // add "Hello firstname lastname"
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequest signupRequest){
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

        // Create a new user
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setFirstName(firstname);

        // Save the user to MongoDB
        userRepository.save(user);

        // fetch all customers
        System.out.println("Users found with findAll():");
        System.out.println("-------------------------------");
        for (User userInDb : userRepository.findAll()) {
            System.out.println(userInDb);
        }
        System.out.println();
        
        return ResponseEntity.ok("Sign Up Successful");
    }

    @PostMapping("/user-details")
    private ResponseEntity<User> userDetails(@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken){
        // validate access token and use it to retrieve user details
        // return user details in response
        User user = userRepository.findByUsername("kimkil");
        return ResponseEntity.ok(user);
    }
}
