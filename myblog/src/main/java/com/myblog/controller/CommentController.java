package com.myblog.controller;

import com.myblog.payload.CommentDto;
import com.myblog.repository.CommentRepository;
import com.myblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class CommentController {

    @Autowired
    private CommentService commentService;

    //http://localhost:8080/api/posts/1/comments
    @PostMapping("/{id}/comments")
    public ResponseEntity<Object> createComment(@Valid @PathVariable("id") long id, @RequestBody CommentDto commentDto, BindingResult result){

        if(result.hasErrors()){
            return new ResponseEntity<Object>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        CommentDto dto = commentService.saveComment(id, commentDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    //http://localhost:8080/api/posts/1/comments
    @GetMapping("/{id}/comments")
    public List<CommentDto> getAllByOneId(@PathVariable("id") long id){
        List<CommentDto> dtos = commentService.getCommentsByPostId(id);
        return dtos;
    }

    @GetMapping("/{id}/comments/{cId}")
    public ResponseEntity<CommentDto> getCommentByCId(@PathVariable("id") long id,@PathVariable("cId") long cId){
        CommentDto dto = commentService.getCommentByCId(id, cId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    //http://localhost:8080/api/posts/1/comments/1
    @PutMapping("/{id}/comments/{cId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable("id") long id,
                                                    @PathVariable("cId") long cId,
                                                    @RequestBody CommentDto commentDto){
        CommentDto dto = commentService.updateComment(id, cId, commentDto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    //http://localhost:8080/api/posts/1/comments/1
    @DeleteMapping("/{id}/comments/{cId}")
    public ResponseEntity<String> deleteComment(@PathVariable("id") long id,
                                                @PathVariable("cId") long cId
                                                ){
        commentService.deleteComment(id,cId);
        return new ResponseEntity<>("Comment deleted",HttpStatus.OK);
    }

}
