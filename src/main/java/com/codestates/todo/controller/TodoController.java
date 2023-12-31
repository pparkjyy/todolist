package com.codestates.todo.controller;

import com.codestates.response.MultiResponseDto;
import com.codestates.response.SingleResponseDto;
import com.codestates.todo.dto.TodoDto;
import com.codestates.todo.entity.Todo;
import com.codestates.todo.mapper.TodoMapper;
import com.codestates.todo.service.TodoService;
import com.codestates.utils.UriCreator;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/todos")
@Validated
public class TodoController {
    private final static String TODO_DEFAULT_URL = "/v1/todos";
    private final TodoService todoService;
    private final TodoMapper mapper;

    public TodoController(TodoService todoService, TodoMapper mapper) {
        this.todoService = todoService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postTodo(@Valid @RequestBody TodoDto.Post requestBody) {
        Todo createdTodo = todoService.createTodo(mapper.todoPostDtoToTodo(requestBody));
        URI location = UriCreator.createUri(TODO_DEFAULT_URL, createdTodo.getTodoId());

        return ResponseEntity.created(location).build();
    }

    @PatchMapping("{todo-id}")
    public ResponseEntity patchTodo(@PathVariable("todo-id") @Positive long todoId,
                                    @Valid @RequestBody TodoDto.Patch requestBody) {
        requestBody.addTodoId(todoId);
        Todo updatedTodo = todoService.updateTodo(mapper.todoPatchDtoToTodo(requestBody));

        return ResponseEntity.ok(mapper.todoToTodoResponseDto(updatedTodo));
    }

    @GetMapping("/{todo-id}")
    public ResponseEntity getTodo(@PathVariable("todo-id") @Positive long todoId) {
        Todo foundTodo = todoService.findTodo(todoId);
        TodoDto.Response response = mapper.todoToTodoResponseDto(foundTodo);
        return ResponseEntity.ok(new SingleResponseDto<>(response));
    }

    @GetMapping
    public ResponseEntity getTodos(@Positive @RequestParam int page,
                                                     @Positive @RequestParam int size) {
        Page<Todo> pageTodo = todoService.findAll(page, size);
        List<Todo> todos = pageTodo.getContent();
        return ResponseEntity.ok(new MultiResponseDto(mapper.todosToTodoResponseDtos(todos), pageTodo));
    }

    @DeleteMapping("/{todo-id}")
    public ResponseEntity deleteTodo(@PathVariable("todo-id") long todoId) {
        todoService.deleteTodo(todoId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity deleteAllTodos() {
        todoService.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
