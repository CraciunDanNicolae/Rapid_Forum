package com.fcrapid.rapidforum.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Setter
public class Post {
    @jakarta.persistence.Id
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    private String imageURL;
    private String posterName;
    private long posterId;
    private String question;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Answer> answers;
    private LocalDateTime postDate;
    @Enumerated(EnumType.STRING)
    private PostCategories category;


    public Post(String imageURL, String posterName, String question, List<Answer> answers, LocalDateTime postDate, PostCategories category, long posterId) {
        this.imageURL = imageURL;
        this.posterName = posterName;
        this.question = question;
        this.answers = answers;
        this.postDate = postDate;
        this.category = category;
        this.posterId = posterId;
    }
}
