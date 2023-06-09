package com.volounteerfinder.mvcserver.repository;
import com.volounteerfinder.mvcserver.model.Posting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface PostingRepository extends JpaRepository<Posting, Integer> {
    List<Posting> findByAuthorId(Integer authorId);
}