package com.sparta.round16.dto.todo;

import com.sparta.round16.dto.comment.CommentDto;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class TodoSimpleResponseDto {

    private final Long id;
    private final String todo;
    private final String username;
    private final Long password;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    private final List<CommentDto> commentList;

    public TodoSimpleResponseDto(Long id, String todo, String username, Long password, LocalDateTime createdAt, LocalDateTime modifiedAt, List<CommentDto> commentList) {
        this.id = id;
        this.todo = todo;
        this.username = username;
        this.password = password;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.commentList = commentList;
    }
}
