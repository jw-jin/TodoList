package com.example.todo.config;

import com.example.todo.repository.TodoListRepository;
import com.example.todo.service.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    private final TodoListRepository todoListRepository;

    @Autowired
    public SpringConfig(TodoListRepository todoListRepository) {
        this.todoListRepository = todoListRepository;
    }
    @Bean
    public TodoListService todoListService() {
        return new TodoListService(todoListRepository);
    }

}
