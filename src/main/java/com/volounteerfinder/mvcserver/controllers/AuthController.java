package com.volounteerfinder.mvcserver.controllers;

import com.volounteerfinder.mvcserver.CustomAuthenticationManager;
import com.volounteerfinder.mvcserver.jwt.JwtProvider;
import com.volounteerfinder.mvcserver.repository.UserRepository;
import com.volounteerfinder.mvcserver.repository.dto.JwtResponse;
import com.volounteerfinder.mvcserver.repository.dto.LoginRequest;
import com.volounteerfinder.mvcserver.repository.dto.SignUpRequest;
import com.volounteerfinder.mvcserver.service.UserDetailsServiceImpl;
import com.volounteerfinder.mvcserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

@Autowired
    private CustomAuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;
@Autowired
    private UserDetailsServiceImpl userDetailsService;
@Autowired
    private UserService userService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);


        String jwt = jwtProvider.generateToken(authentication);
        String username = loginRequest.getUsername(); // You can also fetch username from authentication object
        return ResponseEntity.ok(new JwtResponse(jwt, username));
    }
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signUpRequest) {
        if (userService.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }

        if (userService.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>("Email is already in use!", HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        userService.createUser(signUpRequest);

        return ResponseEntity.ok("User registered successfully!");
    }
}
