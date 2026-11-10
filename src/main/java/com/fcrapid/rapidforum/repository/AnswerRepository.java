package com.fcrapid.rapidforum.repository;

import com.fcrapid.rapidforum.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
     List<Answer> findByPostIdAndUserName(Long postId, String userName);
     List<Answer> findByPostId(Long postId);
     List<Answer> findByUserId(Long userId);
}
