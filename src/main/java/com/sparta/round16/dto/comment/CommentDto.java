package com.sparta.round16.dto.comment;

import lombok.Getter;

@Getter
public class CommentDto {

    private final Long id;
    private final String username;
    private final String contents;


    public CommentDto(Long id, String username, String contents) {
        this.id = id;
        this.username = username;
        this.contents = contents;
    }
}
