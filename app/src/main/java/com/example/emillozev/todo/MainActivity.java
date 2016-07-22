package com.example.emillozev.todo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.emillozev.todo.dialogs.DialogNewTodo;
import com.example.emillozev.todo.implementation.TaskImpl;
import com.example.emillozev.todo.interfaces.Priority;
import com.example.emillozev.todo.interfaces.Status;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private RecyclerAdapter mAdapter;
    private List<TaskImpl> mTaskList;

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
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Dialog", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//
                DialogNewTodo dialog = new DialogNewTodo(MainActivity.this);
                dialog.show();
            }
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

    class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

        private List<TaskImpl> mTaskList;

        public RecyclerAdapter(List<TaskImpl> taskList) {
            mTaskList = taskList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_row, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(getApplicationContext(), R.array.planets_array, android.R.layout.simple_spinner_dropdown_item);
            holder.mStatus.setAdapter(adapter);

            holder.mStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    switch (i){
                        case 0:
                            holder.mStatusText.setText("DONE");
                            break;
                        case 1:
                            holder.mStatusText.setText("DOING");
                            break;
                        case 2:
                            holder.mStatusText.setText("TODO");
                            break;
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {}
            });

            holder.mTitle.setText(mTaskList.get(position).getTaskTitle());
            holder.mDescription.setText(mTaskList.get(position).getDescription());
            holder.mStatusText.setText(mTaskList.get(position).getStatus().toString());
            holder.mPriority.setText(mTaskList.get(position).getPriority().toString());
            holder.mDel.setOnClickListener(view -> {
                mTaskList.remove(position);
                mAdapter.notifyDataSetChanged();
            });
        }

        @Override
        public int getItemCount() {
            return mTaskList.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder{
            CardView mCard;
            TextView mTitle;
            TextView mDescription;
            Spinner mStatus;
            TextView mStatusText;
            TextView mPriority;
            Button mDel;

            public MyViewHolder(View itemView) {
                super(itemView);
                mCard = (CardView) itemView.findViewById(R.id.todoCard);
                mTitle = (TextView) itemView.findViewById(R.id.taskTitle);
                mDescription = (TextView) itemView.findViewById(R.id.taskDescription);
                mDel = (Button) itemView.findViewById(R.id.taskDelete);
                mStatus = (Spinner) itemView.findViewById(R.id.taskStatus);
                mStatusText = (TextView) itemView.findViewById(R.id.textSpinner);
                mPriority = (TextView) itemView.findViewById(R.id.taskPriority);
            }
        }
    }

}
