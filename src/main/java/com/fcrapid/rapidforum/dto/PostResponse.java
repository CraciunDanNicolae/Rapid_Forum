package com.fcrapid.rapidforum.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostResponse {
    private Long id;
    private String imageURL;
    private String posterName;
    private long posterId;
    private String question;
    private List<AnswerResponse> answers;
    private LocalDateTime postDate;
    private String category;
}
