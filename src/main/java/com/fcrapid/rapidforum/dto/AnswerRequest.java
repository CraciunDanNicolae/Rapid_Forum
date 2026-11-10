package com.fcrapid.rapidforum.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AnswerRequest {
    private String imageURL;
    private String content;
    private String userName;
    private Long userId;
    private Long postId;
}
