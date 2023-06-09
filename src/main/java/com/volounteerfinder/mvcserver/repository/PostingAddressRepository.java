package com.volounteerfinder.mvcserver.repository;
import com.volounteerfinder.mvcserver.model.PostingAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

public interface PostingAddressRepository extends JpaRepository<PostingAddress, Integer> {}
