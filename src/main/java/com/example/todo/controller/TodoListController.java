package com.example.todo.controller;

import com.example.todo.domain.Todo;
import com.example.todo.service.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TodoListController {
    private TodoListService todoListService;

    @Autowired
    public TodoListController(TodoListService todoListService) {
        this.todoListService = todoListService;
    }

    @RequestMapping(value = "/todo", method = RequestMethod.GET) @ResponseBody
    public List<Todo> list(Model model) {
        List<Todo> todoList = todoListService.getTodoList();
        return todoList;
    }

    @GetMapping("/todolist")
    public String todolist() {
        return "/todolist";
    }

    @GetMapping("/write")
    public String createForm() {
        return "/write";
    }

    @PostMapping("/write")
    public String write(TodoForm form){
        Todo todo = new Todo();
        todo.setContext(form.getContext());

        todoListService.writeTodoList(todo);

        return "redirect:/";
    }
}
