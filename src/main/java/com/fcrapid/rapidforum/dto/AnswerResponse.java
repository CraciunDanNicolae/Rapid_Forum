package com.fcrapid.rapidforum.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class AnswerResponse {
    private Long id;

    private String imageURL;
    private String content;
    private String userName;
    private LocalDateTime createdAt;
    private Long userId;
}
