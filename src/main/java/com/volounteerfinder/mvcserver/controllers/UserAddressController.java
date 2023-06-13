package com.volounteerfinder.mvcserver.controllers;


import com.volounteerfinder.mvcserver.model.UserAddress;
import com.volounteerfinder.mvcserver.repository.UserAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/user-addresses")
public class UserAddressController {

    @Autowired
    private UserAddressRepository userAddressRepository;

    // Get all user addresses
    @GetMapping
    public List<UserAddress> getAllUserAddresses() {
        return userAddressRepository.findAll();
    }

    // Get user address by user ID
    @GetMapping("/{id}")
    public ResponseEntity<UserAddress> getUserAddressById(@PathVariable Integer id) {
        Optional<UserAddress> userAddress = userAddressRepository.findById(id);
        if (userAddress.isPresent()) {
            return new ResponseEntity<>(userAddress.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Create a new user address
    @PostMapping
    public UserAddress createUserAddress(@RequestBody UserAddress userAddress) {
        return userAddressRepository.save(userAddress);
    }

    // Update a user address
    @PutMapping("/{id}")
    public ResponseEntity<UserAddress> updateUserAddress(@PathVariable Integer id, @RequestBody UserAddress userAddressDetails) {
        Optional<UserAddress> userAddress = userAddressRepository.findById(id);
        if (userAddress.isPresent()) {
            UserAddress updatedUserAddress = userAddress.get();
            updatedUserAddress.setBusy(userAddressDetails.getBusy());
            updatedUserAddress.setCity(userAddressDetails.getCity());
            updatedUserAddress.setStreet(userAddressDetails.getStreet());
            updatedUserAddress.setHousenumber(userAddressDetails.getHousenumber());
            userAddressRepository.save(updatedUserAddress);
            return new ResponseEntity<>(updatedUserAddress, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a user address
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUserAddress(@PathVariable Integer id) {
        Optional<UserAddress> userAddress = userAddressRepository.findById(id);
        if (userAddress.isPresent()) {
            userAddressRepository.delete(userAddress.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
