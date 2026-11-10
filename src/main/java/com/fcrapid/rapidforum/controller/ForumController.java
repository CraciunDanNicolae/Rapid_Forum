package com.fcrapid.rapidforum.controller;
import com.fcrapid.rapidforum.dto.AnswerRequest;
import com.fcrapid.rapidforum.dto.AnswerResponse;
import com.fcrapid.rapidforum.dto.PostRequest;
import com.fcrapid.rapidforum.dto.PostResponse;
import com.fcrapid.rapidforum.mapper.ForumMapper;
import com.fcrapid.rapidforum.model.Answer;
import com.fcrapid.rapidforum.model.Post;
import com.fcrapid.rapidforum.service.ForumService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api/forum")
@RestController
public class ForumController {
    private final ForumService forumService;
    public ForumController(ForumService forumService) {
        this.forumService = forumService;
    }

    @PostMapping("/addPost")
    public ResponseEntity<PostResponse> addPost(@RequestBody PostRequest request) {
        Post post = forumService.addPost(request.getImageURL(), request.getPosterName(), request.getPosterId(), request.getCategory(), request.getQuestion());
        if (post == null) return ResponseEntity.badRequest().build();
        PostResponse response = ForumMapper.mapToPostResponse(post);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/addAnswer")
    public ResponseEntity<AnswerResponse> addAnswer(@RequestBody AnswerRequest request){
        Answer answer = forumService.addAnswer(request.getImageURL(), request.getUserName(), request.getUserId(), request.getContent(), request.getPostId());
        if (answer == null) return ResponseEntity.badRequest().build();
        AnswerResponse response = ForumMapper.mapToAnswerResponse(answer);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getPosts")
    public ResponseEntity<List<PostResponse>> getAllPosts(@RequestParam(required = false) String category){
        List<Post> posts = new ArrayList<>();
        if(category != null){
            posts = forumService.getPostsBasedOnCategory(category);
        }
        else{
            posts = forumService.getPosts();
        }
        if(posts == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(posts.stream().map(ForumMapper::mapToPostResponse).toList());
    }

    @GetMapping("/getAnswers")
    public ResponseEntity<List<AnswerResponse>> getPostsAnswers(@RequestParam Long postId){
        List<Answer> answers = forumService.getPostsAnswers(postId);
        if(answers == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(answers.stream().map(ForumMapper::mapToAnswerResponse).toList());
    }

    @GetMapping("/getUsersPosts")
    public ResponseEntity<List<PostResponse>> getUsersPosts(@RequestParam Long posterId){
        List<Post> posts = forumService.getUsersPosts(posterId);
        if(posts == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(posts.stream().map(ForumMapper::mapToPostResponse).toList());
    }

    @PutMapping("/updatePosts")
    public ResponseEntity<Void> updatePosts(@RequestParam String newName, @RequestParam Long posterId, @RequestParam String newImageUrl){
        forumService.updatePosts(newName, posterId, newImageUrl);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/updateAnswers")
    public ResponseEntity<Void> updateAnswers(@RequestParam String newName, @RequestParam Long userId, @RequestParam String newImageUrl){
        forumService.updateAnswers(newName, userId, newImageUrl);
        return ResponseEntity.ok().build();
    }
}
