package com.volounteerfinder.mvcserver.repository;
import com.volounteerfinder.mvcserver.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {}

