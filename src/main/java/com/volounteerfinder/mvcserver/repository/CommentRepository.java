package com.volounteerfinder.mvcserver.repository;
import com.volounteerfinder.mvcserver.model.Comment;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByFromUser(Integer fromUser);
    List<Comment> findByToUser(Integer toUser);
}