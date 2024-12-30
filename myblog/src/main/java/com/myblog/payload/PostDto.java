package com.myblog.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myblog.entities.Comment;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;


@Data
public class PostDto {

    private long id;

    @NotEmpty
    @Size(min = 2, message = "post title should have at least 2 characters")
    private String title;

    @NotEmpty
    @Size(min=10, message = "post description should have at least 10 characters")
    private String description;

    @NotEmpty
    @Size(min = 10, message = "post content should have at least 10 characters")
    private String content;

    @JsonIgnore
    private List<Comment> comments;
}
