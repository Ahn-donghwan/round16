package com.sparta.round16.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id", nullable = false)
    private Todo todo;

    public Comment(String username, String contents, Todo todo) {
        this.username = username;
        this.contents = contents;
        this.todo = todo;
    }

    public void updateComment(String username, String contents) {
        this.username = username;
        this.contents = contents;
    }
}
