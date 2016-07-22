package com.example.emillozev.todo.implementation;

import com.example.emillozev.todo.interfaces.Priority;
import com.example.emillozev.todo.interfaces.Status;
import com.example.emillozev.todo.interfaces.Task;

public class TaskImpl implements Task {

    private Status mStatus;
    private Priority mPriority;
    private String mTitle;
    private String mDescription;

    public TaskImpl(Status status, Priority priority,String title, String description) {
        mStatus = status;
        mPriority = priority;
        mDescription = description;
        mTitle = title;
    }

    public void setStatus(Status status) {
        mStatus = status;
    }

    public void setPriority(Priority priority) {
        mPriority = priority;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    @Override
    public Status getStatus() {
        return mStatus;
    }

    @Override
    public Priority getPriority() {
        return mPriority;
    }

    @Override
    public String getDescription() {
        return mDescription;
    }

    @Override
    public String getTaskTitle() {
        return mTitle;
    }


}
