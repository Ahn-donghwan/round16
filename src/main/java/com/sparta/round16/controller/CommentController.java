package com.sparta.round16.controller;

import com.sparta.round16.dto.comment.CommentResponseDto;
import com.sparta.round16.dto.comment.CommentSaveRequestDto;
import com.sparta.round16.dto.comment.CommentUpdateRequestDto;
import com.sparta.round16.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;

    // step[1] : todoId 에 해당하는 todo 에 댓글을 작성
    @PostMapping("/api/todos/{todoId}/comments")
    public CommentResponseDto saveCommentByTodoId(
            @PathVariable Long todoId, @RequestBody CommentSaveRequestDto commentSaveRequestDto){
        return commentService.saveCommentByTodoId(todoId, commentSaveRequestDto);
    }

    // step[2] : todoId 에 해당하는 todo 에 달린 댓글을 전체 조회
    @GetMapping("/api/todos/{todoId}/comments")
    public List<CommentResponseDto> getAllCommentByTodoId(@PathVariable Long todoId) {
        return commentService.getAllCommentByTodoId(todoId);
    }

    // step[3] : 특정 댓글을 단건 조회
    @GetMapping("/api/todos/comments/{commentId}")
    public CommentResponseDto getDetailCommentByCommentId(@PathVariable Long commentId){
        return commentService.getDetailCommentByCommentId(commentId);
    }

    // step[4] : 특정 댓글을 수정
    @PutMapping("/api/todos/comments/{commentId}")
    public CommentResponseDto updateCommentByCommentId(
            @PathVariable Long commentId, @RequestBody CommentUpdateRequestDto commentUpdateRequestDto){
        return commentService.updateCommentByCommentId(commentId, commentUpdateRequestDto);
    }

    // step[5] : 특정 댓글을 삭제
    @DeleteMapping("/api/todos/comments/{commentId}")
    public void deleteCommentByCommentId(@PathVariable Long commentId){
        commentService.deleteCommentByCommentId(commentId);
    }

}
