package com.example.emillozev.todo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.emillozev.todo.dialogs.DialogNewTodo;
import com.example.emillozev.todo.implementation.TaskImpl;
import com.example.emillozev.todo.interfaces.Priority;
import com.example.emillozev.todo.interfaces.Status;
import com.example.emillozev.todo.recycler.*;
import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    public static RecyclerAdapter mAdapter;
    public static List<TaskImpl> mTaskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.todoHolder);
        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);

        initializeStartCards();
        mAdapter = new RecyclerAdapter(mTaskList);
        mRecyclerView.setAdapter(mAdapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            DialogNewTodo dialog = new DialogNewTodo(MainActivity.this);
            dialog.show();
        });
    }

    public void initializeStartCards(){
        mTaskList = new ArrayList<>();
        mTaskList.add(new TaskImpl(Status.DONE, Priority.HIGH, "Walk the dog", "Go to the lake"));
        mTaskList.add(new TaskImpl(Status.DOING, Priority.LOW, "Do the dishes", "..."));
        mTaskList.add(new TaskImpl(Status.TODO, Priority.NORMAL, "Go to cinema", "..."));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_todo_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
