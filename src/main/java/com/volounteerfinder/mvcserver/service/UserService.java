package com.volounteerfinder.mvcserver.service;

import com.volounteerfinder.mvcserver.model.User;
import com.volounteerfinder.mvcserver.repository.UserRepository;
import com.volounteerfinder.mvcserver.repository.dto.SignUpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.volounteerfinder.mvcserver.model.User.Role.CLIENT;

@Component
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }
    public void createUser(SignUpRequest signUpRequest) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword( passwordEncoder.encode(signUpRequest.getPassword()));
        user.setRole(CLIENT);
        // Set other properties if needed...

        userRepository.save(user);
    }
    // Other service methods...
}
