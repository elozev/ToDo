package com.example.emillozev.todo.interfaces;

public interface Task {
    Status getStatus();
    Priority getPriority();
    String getDescription();
    String getTaskTitle();
}
