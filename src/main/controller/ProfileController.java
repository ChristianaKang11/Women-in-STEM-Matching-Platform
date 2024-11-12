package main.controller;

import main.entity.User;
import main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private UserService userService;

    /**
     * Retrieves a user's profile by username.
     * @param username Username of the user.
     * @return User profile details.
     */
    @GetMapping("/{username}")
    public ResponseEntity<?> getUserProfile(@PathVariable String username) {
        Optional<User> user = userService.findByUsername(username);

        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(404).body("User not found.");
        }
    }

    /**
     * Updates a user's profile.
     * @param username Username of the user to update.
     * @param updatedUser Updated user details.
     * @return Success message or error.
     */
    @PutMapping("/{username}")
    public ResponseEntity<?> updateUserProfile(@PathVariable String username, @RequestBody User updatedUser) {
        Optional<User> user = userService.findByUsername(username);

        if (user.isPresent()) {
            User existingUser = user.get();
            existingUser.setRole(updatedUser.getRole());
            existingUser.setExpertise(updatedUser.getExpertise());
            userService.registerUser(existingUser);
            return ResponseEntity.ok("Profile updated successfully.");
        } else {
            return ResponseEntity.status(404).body("User not found.");
        }
    }
}
