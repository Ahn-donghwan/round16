package com.sparta.round16.service;

import com.sparta.round16.dto.comment.CommentDto;
import com.sparta.round16.dto.todo.TodoResponseDto;
import com.sparta.round16.dto.todo.TodoSaveRequestDto;
import com.sparta.round16.dto.todo.TodoSimpleResponseDto;
import com.sparta.round16.dto.todo.TodoUpdateRequestDto;
import com.sparta.round16.entity.Comment;
import com.sparta.round16.entity.Todo;
import com.sparta.round16.repository.CommentRepository;
import com.sparta.round16.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TodoService {

    private final TodoRepository todoRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public TodoResponseDto saveTodo(TodoSaveRequestDto todoSaveRequestDto) {
        Todo todo = new Todo(
                todoSaveRequestDto.getTodo(),
                todoSaveRequestDto.getUsername(),
                todoSaveRequestDto.getPassword()
        );

       todoRepository.save(todo);

        return new TodoResponseDto(
                todo.getId(),
                todo.getTodo(),
                todo.getUsername(),
                todo.getPassword(),
                todo.getCreatedAt(),
                todo.getModifiedAt()
        );
    }

    public TodoResponseDto getDetailTodo(Long todoId) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new NullPointerException("ㅈㅅ 그런거 업슴!"));

        return new TodoResponseDto(
                todo.getId(),
                todo.getTodo(),
                todo.getUsername(),
                todo.getPassword(),
                todo.getCreatedAt(),
                todo.getModifiedAt()
        );
    }

    public List<TodoSimpleResponseDto> getAllTodo() {

        List<Todo> todoList = todoRepository.findAll();

        List<TodoSimpleResponseDto> dtoList = new ArrayList<>();

        for (Todo todo : todoList) {
            List<CommentDto> commentList = commentRepository.findAllCommentByTodoId(todo.getId());

            TodoSimpleResponseDto dto = new TodoSimpleResponseDto(
                    todo.getId(),
                    todo.getTodo(),
                    todo.getUsername(),
                    todo.getPassword(),
                    todo.getCreatedAt(),
                    todo.getModifiedAt(),
                    commentList
            );
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Transactional
    public TodoResponseDto updateTodo(Long todoId, TodoUpdateRequestDto todoUpdateRequestDto) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new NullPointerException("ㅈㅅ 그런거 업슴!"));

        todo.updateTodo(todoUpdateRequestDto.getTodo(), todoUpdateRequestDto.getUsername());

        return new TodoResponseDto(
                todo.getId(),
                todo.getTodo(),
                todo.getUsername(),
                todo.getPassword(),
                todo.getCreatedAt(),
                todo.getModifiedAt()
        );
    }

    @Transactional
    public void deleteTodo(Long todoId) {
        todoRepository.findById(todoId).orElseThrow(()-> new NullPointerException("ㅈㅅ 그런거 업슴!"));

        todoRepository.deleteById(todoId);
    }
}
