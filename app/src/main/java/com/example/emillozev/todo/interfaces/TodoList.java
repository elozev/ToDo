package com.example.emillozev.todo.interfaces;


import com.example.emillozev.todo.implementation.TodoListImpl;

import java.util.List;

public interface TodoList {

//    public static TodoList parse(String line){
//        return new TodoListImpl(line);
//    }

    List<Task> getTasks();
    TodoList filter(Criteria criteria);
}
