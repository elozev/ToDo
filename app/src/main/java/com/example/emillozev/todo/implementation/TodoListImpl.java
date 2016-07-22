package com.example.emillozev.todo.implementation;

import com.example.emillozev.todo.interfaces.Criteria;
import com.example.emillozev.todo.interfaces.Task;
import com.example.emillozev.todo.interfaces.TodoList;

import java.util.List;

public class TodoListImpl implements TodoList {

    private List<Task> mTaskList;

    public TodoListImpl(String input){

    }

    @Override
    public List<Task> getTasks() {
        return null;
    }

    @Override
    public TodoList filter(Criteria criteria) {
        return null;
    }
}
