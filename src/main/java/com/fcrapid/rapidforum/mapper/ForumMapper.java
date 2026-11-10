package com.fcrapid.rapidforum.mapper;

import com.fcrapid.rapidforum.dto.AnswerResponse;
import com.fcrapid.rapidforum.dto.PostResponse;
import com.fcrapid.rapidforum.model.Answer;
import com.fcrapid.rapidforum.model.Post;

public class ForumMapper {
    public static PostResponse mapToPostResponse(Post post){
        PostResponse response = new PostResponse();
        response.setId(post.getId());
        response.setImageURL(post.getImageURL());
        response.setPostDate(post.getPostDate());
        response.setCategory(post.getCategory().name());
        response.setQuestion(post.getQuestion());
        response.setPosterId(post.getPosterId());
        response.setPosterName(post.getPosterName());
        response.setAnswers(post.getAnswers().stream().map(ForumMapper::mapToAnswerResponse).toList());
        return response;
    }

    public static AnswerResponse mapToAnswerResponse(Answer answer){
        AnswerResponse response = new AnswerResponse();
        response.setContent(answer.getContent());
        response.setId(answer.getId());
        response.setImageURL(answer.getImageURL());
        response.setUserId(answer.getUserId());
        response.setUserName(answer.getUserName());
        response.setCreatedAt(answer.getCreatedAt());
        return response;
    }
}
