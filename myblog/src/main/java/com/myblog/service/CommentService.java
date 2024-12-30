package com.myblog.service;

import com.myblog.payload.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto saveComment(long id, CommentDto commentDto);

    List<CommentDto> getCommentsByPostId(long id);

    CommentDto getCommentByCId(long postId, long CId);

    CommentDto updateComment(long id, long cId, CommentDto commentDto);

    void deleteComment(long id, long cId);
}
