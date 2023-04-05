package com.example.todo.repository;

import com.example.todo.domain.Todo;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
public class JpaTodoListRepository implements TodoListRepository {

    private final EntityManager em;

    public JpaTodoListRepository(EntityManager em) {
        this.em = em;
    }



    @Override
    public Todo saveTodoList(Todo todo) {
        em.persist(todo);
        return todo;
    }



    @Override
    public List<Todo> getTodoList() {
        return em.createQuery("select t from Todo t",Todo.class).getResultList();
    }

    @Override
    public void updateById(Long Id, String newContext) {
        Todo updateTodo = em.find(Todo.class, Id);
        updateTodo.setContext(newContext);
        em.merge(updateTodo);

    }

    @Override
    public void deleteById(Long Id) {
        Todo deleteTodo = em.find(Todo.class,Id);
        em.remove(deleteTodo);
    }



}
