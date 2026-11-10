package com.fcrapid.rapidforum.model;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Answer {
    @jakarta.persistence.Id
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
    private String imageURL;
    private String content;
    private String userName;
    private LocalDateTime createdAt;
    private Long userId;

    public Answer(String imageURL, String content, LocalDateTime createdAt, Post post, String userName, Long userId) {
        this.imageURL = imageURL;
        this.content = content;
        this.createdAt = createdAt;
        this.post = post;
        this.userName = userName;
        this.userId = userId;
    }

}
