package com.sparta.round16.dto.todo;

import lombok.Getter;

@Getter
public class TodoSaveRequestDto {

    private String todo;
    private String username;
    private Long password;
}
