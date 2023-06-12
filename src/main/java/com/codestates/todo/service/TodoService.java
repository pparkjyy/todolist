package com.codestates.todo.service;

import com.codestates.exception.BusinessLogicException;
import com.codestates.exception.ExceptionCode;
import com.codestates.todo.entity.Todo;
import com.codestates.todo.repository.TodoRepository;
import com.codestates.utils.CustomBeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class TodoService {
    private final TodoRepository todoRepository;
    private final CustomBeanUtils<Todo> beanUtils;

    public TodoService(TodoRepository todoRepository, CustomBeanUtils beanUtils) {
        this.todoRepository = todoRepository;
        this.beanUtils = beanUtils;
    }

    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public Todo updateTodo(Todo todo) {
        Todo foundTodo = findTodo(todo.getTodoId());
        Todo updatedTodo = beanUtils.copyNonNullProperties(todo, foundTodo);

        return todoRepository.save(updatedTodo);
    }

    @Transactional(readOnly = true)
    public Todo findTodo(long todoId) {
        return todoRepository
                .findById(todoId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.TODO_NOT_FOUND));
    }

    public Page<Todo> findAll(int page, int size) {
        return todoRepository.findAll(PageRequest.of(page - 1, size, Sort.by("todoId").descending()));
    }

    public void deleteTodo(long todoId) {
        Todo foundTodo = findTodo(todoId);
        todoRepository.delete(foundTodo);
    }

    public void deleteAll() {
        todoRepository.deleteAll();
    }
}
