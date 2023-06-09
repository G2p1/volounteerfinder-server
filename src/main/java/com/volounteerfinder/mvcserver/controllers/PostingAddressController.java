package com.volounteerfinder.mvcserver.controllers;


import com.volounteerfinder.mvcserver.model.PostingAddress;
import com.volounteerfinder.mvcserver.repository.PostingAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posting-addresses")
public class PostingAddressController {

    @Autowired
    private PostingAddressRepository postingAddressRepository;

    // Get all posting addresses
    @GetMapping
    public List<PostingAddress> getAllPostingAddresses() {
        return postingAddressRepository.findAll();
    }

    // Get posting address by ID
    @GetMapping("/{id}")
    public ResponseEntity<PostingAddress> getPostingAddressById(@PathVariable Integer id) {
        Optional<PostingAddress> postingAddress = postingAddressRepository.findById(id);
        if (postingAddress.isPresent()) {
            return new ResponseEntity<>(postingAddress.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Create a new posting address
    @PostMapping
    public PostingAddress createPostingAddress(@RequestBody PostingAddress postingAddress) {
        return postingAddressRepository.save(postingAddress);
    }

    // Update a posting address
    @PutMapping("/{id}")
    public ResponseEntity<PostingAddress> updatePostingAddress(@PathVariable Integer id, @RequestBody PostingAddress postingAddressDetails) {
        Optional<PostingAddress> postingAddress = postingAddressRepository.findById(id);
        if (postingAddress.isPresent()) {
            PostingAddress updatedPostingAddress = postingAddress.get();
            updatedPostingAddress.setCity(postingAddressDetails.getCity());
            updatedPostingAddress.setStreet(postingAddressDetails.getStreet());
            updatedPostingAddress.setHousenumber(postingAddressDetails.getHousenumber());
            updatedPostingAddress.setLat(postingAddressDetails.getLat());
            updatedPostingAddress.setLon(postingAddressDetails.getLon());
            postingAddressRepository.save(updatedPostingAddress);
            return new ResponseEntity<>(updatedPostingAddress, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a posting address
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePostingAddress(@PathVariable Integer id) {
        Optional<PostingAddress> postingAddress = postingAddressRepository.findById(id);
        if (postingAddress.isPresent()) {
            postingAddressRepository.delete(postingAddress.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
