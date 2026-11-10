package com.fcrapid.rapidforum.repository;

import com.fcrapid.rapidforum.model.Post;
import com.fcrapid.rapidforum.model.PostCategories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByPosterId(Long posterId);
    List<Post> findByPosterName(String posterName);
    List<Post> findByCategory(PostCategories category);
}
