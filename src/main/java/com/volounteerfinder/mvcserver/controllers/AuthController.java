package com.volounteerfinder.mvcserver.controllers;

import com.volounteerfinder.mvcserver.jwt.JwtProvider;
import com.volounteerfinder.mvcserver.repository.dto.JwtResponse;
import com.volounteerfinder.mvcserver.repository.dto.LoginRequest;
import com.volounteerfinder.mvcserver.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;

    private UserDetailsServiceImpl userDetailsService;

    @GetMapping("/hello")
    public String hello(){
        return "alalalal";
    }

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
}
