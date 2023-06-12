package com.codestates.todo.mapper;

import com.codestates.todo.dto.TodoDto;
import com.codestates.todo.entity.Todo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TodoMapper {
    Todo todoPostDtoToTodo(TodoDto.Post requestBody);
    Todo todoPatchDtoToTodo(TodoDto.Patch requestBody);
    TodoDto.Response todoToTodoResponseDto(Todo todo);

    List<TodoDto.Response> todosToTodoResponseDtos(List<Todo> todos);
}
