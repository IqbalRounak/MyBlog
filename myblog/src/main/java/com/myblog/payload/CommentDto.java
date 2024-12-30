package com.myblog.payload;

import com.myblog.entities.Post;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class CommentDto {
    private long id;
    @NotEmpty
    @Size(min = 4, message = "Name should be at least 4 characters")
    private String name;
    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Size(min = 4, message = "message should be at least 5 characters")
    private String body;
    //private Post post;
}
