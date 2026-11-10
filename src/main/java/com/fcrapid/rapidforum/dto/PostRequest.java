package com.fcrapid.rapidforum.dto;

import com.fcrapid.rapidforum.model.PostCategories;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostRequest {
    private String imageURL;
    private String posterName;
    private Long posterId;
    private String question;
    private String category;
}
