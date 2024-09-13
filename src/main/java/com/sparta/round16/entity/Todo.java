package com.sparta.round16.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Todo extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String todo;
    private String username;
    private Long password;

    public Todo(String todo, String username, Long password) {
        this.todo = todo;
        this.username = username;
        this.password = password;
    }

    public void updateTodo(String todo, String username) {
        this.todo = todo;
        this.username = username;
    }
}
