package com.fcrapid.rapidforum.service;

import com.fcrapid.rapidforum.model.Answer;
import com.fcrapid.rapidforum.model.Post;
import com.fcrapid.rapidforum.model.PostCategories;
import com.fcrapid.rapidforum.repository.AnswerRepository;
import com.fcrapid.rapidforum.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ForumService {
    private final AnswerRepository answerRepository;
    private final PostRepository postRepository;

    public ForumService(AnswerRepository answerRepository, PostRepository postRepository) {
        this.answerRepository = answerRepository;
        this.postRepository = postRepository;
    }

    public Post addPost(String imageURL, String posterName, Long posterId, String category, String question){
        PostCategories mappedCategory;
        try {
            mappedCategory = PostCategories.valueOf(category);
        } catch (IllegalArgumentException e) {
            return null;
        }
        Post post = new Post(imageURL, posterName, question, new ArrayList<Answer>(), LocalDateTime.now(), mappedCategory, posterId);
        postRepository.save(post);
        return post;
    }

    public void updatePosts(String newPosterName, Long posterId, String imageURL){
        List<Post> userPosts = postRepository.findByPosterId(posterId);
        for(Post post : userPosts){
            post.setPosterName(newPosterName);
            post.setImageURL(imageURL);
        }
        postRepository.saveAll(userPosts);
    }

    public Answer addAnswer(String imageURL, String userName, Long userId, String content, Long postId){
        Post post = postRepository.findById(postId).orElse(null);
        if(post == null){
            return null;
        }
        Answer answer = new Answer(imageURL, content, LocalDateTime.now(), post, userName, userId);
        answerRepository.save(answer);
        return answer;
    }

    public void updateAnswers(String newUserName, Long userId, String newImageURL){
        List<Answer> userAnswers = answerRepository.findByUserId(userId);
        if(userAnswers.isEmpty()){
            return;
        }
        for(Answer answer : userAnswers){
            answer.setImageURL(newImageURL);
            answer.setUserName(newUserName);
        }
        answerRepository.saveAll(userAnswers);
    }

    public List<Post> getPosts(){
        return postRepository.findAll();
    }

    public List<Post> getPostsBasedOnCategory(String category){
        if(category == null || category.isBlank()){
            return null;
        }
        try {
            PostCategories mappedCategory = PostCategories.valueOf(category);
            return postRepository.findByCategory(mappedCategory);
        } catch (IllegalArgumentException e) {
            return null;
        }


    }

    public List<Answer> getPostsAnswers(Long postId){
        if(postId == null || postId <= 0){
            return null;
        }
        return answerRepository.findByPostId(postId);
    }

    public List<Post> getUsersPosts(Long posterId){
        if(posterId == null || posterId <= 0){
            return null;
        }
        return postRepository.findByPosterId(posterId);
    }
}
