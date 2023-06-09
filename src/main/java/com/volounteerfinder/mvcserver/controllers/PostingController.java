package com.volounteerfinder.mvcserver.controllers;


import com.volounteerfinder.mvcserver.model.Posting;
import com.volounteerfinder.mvcserver.repository.PostingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/postings")
public class PostingController {

    @Autowired
    private PostingRepository postingRepository;

    // Get all postings
    @GetMapping
    public List<Posting> getAllPostings() {
        return postingRepository.findAll();
    }

    // Get posting by ID
    @GetMapping("/{id}")
    public ResponseEntity<Posting> getPostingById(@PathVariable Integer id) {
        Optional<Posting> posting = postingRepository.findById(id);
        if (posting.isPresent()) {
            return new ResponseEntity<>(posting.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Create a new posting
    @PostMapping
    public Posting createPosting(@RequestBody Posting posting) {
        return postingRepository.save(posting);
    }

    // Update a posting
    @PutMapping("/{id}")
    public ResponseEntity<Posting> updatePosting(@PathVariable Integer id, @RequestBody Posting postingDetails) {
        Optional<Posting> posting = postingRepository.findById(id);
        if (posting.isPresent()) {
            Posting updatedPosting = posting.get();
            updatedPosting.setTitle(postingDetails.getTitle());
            updatedPosting.setDescription(postingDetails.getDescription());
            updatedPosting.setActive(postingDetails.getActive());
            postingRepository.save(updatedPosting);
            return new ResponseEntity<>(updatedPosting, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a posting
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePosting(@PathVariable Integer id) {
        Optional<Posting> posting = postingRepository.findById(id);
        if (posting.isPresent()) {
            postingRepository.delete(posting.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
