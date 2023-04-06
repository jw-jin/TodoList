package com.example.todo.service;

import com.example.todo.domain.Todo;
import com.example.todo.repository.TodoListRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class TodoListService {
    private final TodoListRepository todoListRepository;

    public TodoListService(TodoListRepository todoListRepository) {
        this.todoListRepository = todoListRepository;
    }

    public List<Todo> getTodoList() {
        return todoListRepository.getTodoList();
    }

    public void writeTodoList(Todo todo){
        todoListRepository.saveTodoList(todo);
    }

    public void updateTodoList(Long Id, String newContext){
        todoListRepository.updateById(Id, newContext);
    }

    public void updateTodoList(Long Id, int Status){
        todoListRepository.updateById(Id, Status);
    }
    public void deleteTodoList(Long Id){
        todoListRepository.deleteById(Id);
    }



}
