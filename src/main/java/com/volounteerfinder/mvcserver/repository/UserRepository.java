package com.volounteerfinder.mvcserver.repository;

import com.volounteerfinder.mvcserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    User findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
