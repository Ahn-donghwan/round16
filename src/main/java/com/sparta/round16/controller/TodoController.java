package com.sparta.round16.controller;

import com.sparta.round16.dto.todo.TodoResponseDto;
import com.sparta.round16.dto.todo.TodoSaveRequestDto;
import com.sparta.round16.dto.todo.TodoSimpleResponseDto;
import com.sparta.round16.dto.todo.TodoUpdateRequestDto;
import com.sparta.round16.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public TodoResponseDto saveTodo(@RequestBody TodoSaveRequestDto todoSaveRequestDto){
        return todoService.saveTodo(todoSaveRequestDto);
    }

    // 단건 조회
    @GetMapping("/{todoId}")
    public TodoResponseDto getDetailTodo(@PathVariable Long todoId){
        return todoService.getDetailTodo(todoId);
    }

    // 전체 조회
    @GetMapping
    public List<TodoSimpleResponseDto> getAllTodo(){
        return todoService.getAllTodo();
    }

    @PutMapping("/{todoId}")
    public TodoResponseDto updateTodo(@PathVariable Long todoId, @RequestBody TodoUpdateRequestDto todoUpdateRequestDto){
        return  todoService.updateTodo(todoId, todoUpdateRequestDto);
    }

    @DeleteMapping("/{todoId}")
    public void deleteTodo(@PathVariable Long todoId){
        todoService.deleteTodo(todoId);
    }

}
