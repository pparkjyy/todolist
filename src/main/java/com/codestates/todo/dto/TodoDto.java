package com.codestates.todo.dto;

import com.codestates.todo.entity.Todo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TodoDto {
    @Getter
    @AllArgsConstructor
    public static class Post {
        @NotBlank
        private String title;

        @NotNull
        private Todo.TodoPriority todoPriority;
    }

    @Getter
    public static class Patch {
        private long todoId;
        private String title;
        private Todo.TodoPriority todoPriority;
        private Boolean isCompleted;

        public void addTodoId(long todoId) {
            this.todoId = todoId;
        }
    }

    @Getter
    @Builder
    public static class Response {
        private long todoId;
        private String title;
        private Todo.TodoPriority todoPriority;
        private Boolean isCompleted;
        private String createdAt;
        private String modifiedAt;
    }
}
