package com.myblog.controller;

import com.myblog.entities.Post;
import com.myblog.payload.PostDto;
import com.myblog.payload.PostResponse;
import com.myblog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    //http://localhost:8080/api/posts

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Object> createPost(@Valid @RequestBody PostDto postDto, BindingResult result){

        if(result.hasErrors()){
            return new ResponseEntity<Object>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        PostDto dto = postService.createPost(postDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping
    public PostResponse getPosts(@RequestParam(value = "pageNo", defaultValue = "0",required = false) int pageNo,
                          @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
                          @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
                          @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir){
        return postService.getALlPosts(pageNo, pageSize, sortBy, sortDir);

    }

    //http://localhost:8080/api/posts/1
    @GetMapping("/{id}")
        public ResponseEntity<PostDto>getPostById(@PathVariable("id") long id){
        PostDto dto = postService.getPostById(id);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    //http://localhost:8080/api/posts/1
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
        public ResponseEntity<PostDto>updatePost(@RequestBody PostDto postDto, @PathVariable("id") long id){
        PostDto dto = postService.updatePost(postDto, id);
        return new ResponseEntity<>(dto,HttpStatus.CREATED);
    }

    //http://localhost:8080/api/posts/1
    @DeleteMapping("/{id}")
        public ResponseEntity<String> deletePost(@PathVariable("id") long id){
        postService.deletePostById(id);
        return new ResponseEntity<>("post is deleted successfully",HttpStatus.OK);
        }
}
