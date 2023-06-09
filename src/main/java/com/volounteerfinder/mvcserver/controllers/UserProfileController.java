package com.volounteerfinder.mvcserver.controllers;

import com.volounteerfinder.mvcserver.model.UserProfile;
import com.volounteerfinder.mvcserver.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/volounteerfinder/user-profiles")
public class UserProfileController {

    @Autowired
    private UserProfileRepository userProfileRepository;

    // Get all user profiles
    @GetMapping
    public List<UserProfile> getAllUserProfiles() {
        return userProfileRepository.findAll();
    }

    // Get user profile by user ID
    @GetMapping("/{id}")
    public ResponseEntity<UserProfile> getUserProfileById(@PathVariable Integer id) {
        Optional<UserProfile> userProfile = userProfileRepository.findById(id);
        if (userProfile.isPresent()) {
            return new ResponseEntity<>(userProfile.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Create a new user profile
    @PostMapping
    public UserProfile createUserProfile(@RequestBody UserProfile userProfile) {
        return userProfileRepository.save(userProfile);
    }

    // Update a user profile
    @PutMapping("/{id}")
    public ResponseEntity<UserProfile> updateUserProfile(@PathVariable Integer id, @RequestBody UserProfile userProfileDetails) {
        Optional<UserProfile> userProfile = userProfileRepository.findById(id);
        if (userProfile.isPresent()) {
            UserProfile updatedUserProfile = userProfile.get();
            updatedUserProfile.setTitle(userProfileDetails.getTitle());
            updatedUserProfile.setAbout(userProfileDetails.getAbout());
            updatedUserProfile.setSkills(userProfileDetails.getSkills());
            userProfileRepository.save(updatedUserProfile);
            return new ResponseEntity<>(updatedUserProfile, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a user profile
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUserProfile(@PathVariable Integer id) {
        Optional<UserProfile> userProfile = userProfileRepository.findById(id);
        if (userProfile.isPresent()) {
            userProfileRepository.delete(userProfile.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
