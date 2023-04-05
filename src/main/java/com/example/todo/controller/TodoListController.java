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
    public List<Todo> list() {
        List<Todo> todoList = todoListService.getTodoList();
        return todoList;
    }

    @RequestMapping("/todolist")
    public String todolist(Model model) {
        List<Todo> todoListObj = todoListService.getTodoList();
        model.addAttribute("todoListObj", todoListObj);
        return "/todolist";
    }

//    @GetMapping("/write")
//    public String createForm() {
//        return "/write";
//    }

    @PostMapping("/todolist/write")
    public String write(TodoForm form){
        Todo todo = new Todo();
        System.out.println(form.getWriteContext());
        todo.setContext(form.getWriteContext());

        todoListService.writeTodoList(todo);

        return "redirect:/todolist";
    }

    @PostMapping("/todolist/{id}/delete")
    public String delete(@PathVariable(name = "id") Long Id){
        todoListService.deleteTodoList(Id);
        return "redirect:/todolist";
    }

    @PostMapping("/todolist/{id}/update")
    public String update(TodoForm form, @PathVariable(name = "id") Long Id){
        todoListService.updateTodoList(Id, form.getUpdateContext());
        return "redirect:/todolist";
    }

}
