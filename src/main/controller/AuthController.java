package main.controller;

import com.example.mentormatch.entity.User;
import com.example.mentormatch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    /**
     * Registers a new user.
     * @param user User details for registration.
     * @return A response indicating success or failure.
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if (userService.isUsernameTaken(user.getUsername())) {
            return ResponseEntity.badRequest().body("Username is already taken.");
        }
        userService.registerUser(user);
        return ResponseEntity.ok("User registered successfully.");
    }

    /**
     * Logs in a user (token generation is managed separately).
     * @param username Username provided for login.
     * @param password Password provided for login.
     * @return Success message with token or failure message.
     */
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestParam String username, @RequestParam String password) {
        Optional<User> user = userService.findByUsername(username);

        if (user.isPresent() && userService.validatePassword(password, user.get().getPassword())) {
            // Assuming we have a JWT token utility to generate tokens
            String token = "GeneratedJWTToken";  // replace with actual token generation logic
            return ResponseEntity.ok().body("Login successful. Token: " + token);
        } else {
            return ResponseEntity.status(401).body("Invalid username or password.");
        }
    }
}
