package com.sparta.round16.repository;

import com.sparta.round16.dto.comment.CommentDto;
import com.sparta.round16.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByTodoId(Long todoId);

    List<CommentDto> findAllCommentByTodoId(Long id);
}
