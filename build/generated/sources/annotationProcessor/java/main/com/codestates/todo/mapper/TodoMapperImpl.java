package com.codestates.todo.mapper;

import com.codestates.todo.dto.TodoDto;
import com.codestates.todo.entity.Todo;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-12T13:40:29+0900",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 11.0.18 (Azul Systems, Inc.)"
)
@Component
public class TodoMapperImpl implements TodoMapper {

    @Override
    public Todo todoPostDtoToTodo(TodoDto.Post requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Todo todo = new Todo();

        todo.setTitle( requestBody.getTitle() );
        todo.setTodoPriority( requestBody.getTodoPriority() );

        return todo;
    }

    @Override
    public Todo todoPatchDtoToTodo(TodoDto.Patch requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Todo todo = new Todo();

        todo.setTodoId( requestBody.getTodoId() );
        todo.setTitle( requestBody.getTitle() );
        todo.setTodoPriority( requestBody.getTodoPriority() );
        todo.setIsCompleted( requestBody.getIsCompleted() );

        return todo;
    }

    @Override
    public TodoDto.Response todoToTodoResponseDto(Todo todo) {
        if ( todo == null ) {
            return null;
        }

        TodoDto.Response.ResponseBuilder response = TodoDto.Response.builder();

        if ( todo.getTodoId() != null ) {
            response.todoId( todo.getTodoId() );
        }
        response.title( todo.getTitle() );
        response.todoPriority( todo.getTodoPriority() );
        response.isCompleted( todo.getIsCompleted() );
        if ( todo.getCreatedAt() != null ) {
            response.createdAt( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( todo.getCreatedAt() ) );
        }
        if ( todo.getModifiedAt() != null ) {
            response.modifiedAt( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( todo.getModifiedAt() ) );
        }

        return response.build();
    }

    @Override
    public List<TodoDto.Response> todosToTodoResponseDtos(List<Todo> todos) {
        if ( todos == null ) {
            return null;
        }

        List<TodoDto.Response> list = new ArrayList<TodoDto.Response>( todos.size() );
        for ( Todo todo : todos ) {
            list.add( todoToTodoResponseDto( todo ) );
        }

        return list;
    }
}
