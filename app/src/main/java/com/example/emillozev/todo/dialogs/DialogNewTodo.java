package com.example.emillozev.todo.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emillozev.todo.ContextGetter;
import com.example.emillozev.todo.MainActivity;
import com.example.emillozev.todo.R;
import com.example.emillozev.todo.implementation.TaskImpl;
import com.example.emillozev.todo.interfaces.Priority;
import com.example.emillozev.todo.interfaces.Status;

public class DialogNewTodo extends Dialog implements android.view.View.OnClickListener {

    public Context mContext;
    public Dialog mDialog;
    public Button mAccept;
    public Button mCancel;
    public EditText mTitle;
    public EditText mDescription;
    public Spinner mPriority;
    public Spinner mState;

    public DialogNewTodo(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_layout);

        initialiseViews();
        mAccept.setOnClickListener(this);
        mCancel.setOnClickListener(this);
    }

    private void initialiseViews(){
        mAccept = (Button) findViewById(R.id.btn_yes);
        mCancel = (Button) findViewById(R.id.btn_no);
        mTitle = (EditText) findViewById(R.id.editTextTitle);
        mDescription = (EditText) findViewById(R.id.editTextDescription);


        mState = (Spinner) findViewById(R.id.spinnerState);
        ArrayAdapter<CharSequence> statusAdapter = ArrayAdapter
                .createFromResource(ContextGetter.getApplicationCtxt(),
                        R.array.status_array,
                        android.R.layout.simple_spinner_dropdown_item);
        mState.setAdapter(statusAdapter);


        mState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        setPriorityOrStatus("DONE", 0);
                        break;
                    case 1:
                        setPriorityOrStatus("DOING", 0);
                        break;
                    case 2:
                        setPriorityOrStatus("TODO", 0);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        mPriority = (Spinner) findViewById(R.id.spinnerPriority);
        ArrayAdapter<CharSequence> priorityAdapter = ArrayAdapter
                .createFromResource(ContextGetter.getApplicationCtxt(),
                        R.array.priority_array,
                        android.R.layout.simple_spinner_dropdown_item);
        mPriority.setAdapter(priorityAdapter);

        mPriority.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        setPriorityOrStatus("HIGH", 1);
                        break;
                    case 1:
                        setPriorityOrStatus("NORMAL", 1);
                        break;
                    case 2:
                        setPriorityOrStatus("LOW", 1);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

    }

    private void setPriorityOrStatus(String set, int i) {
        switch (i){
            case 0:
                TextView state = (TextView) findViewById(R.id.textViewState);
                state.setText(set);
                break;
            case 1:
                TextView priority = (TextView) findViewById(R.id.textViewPriority);
                priority.setText(set);
                break;
        }

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_yes:
                Toast.makeText(ContextGetter.getApplicationCtxt(), "Accepted", Toast.LENGTH_SHORT).show();
                while(mTitle.getText() == null || mTitle.getText().equals("")){
                    Toast.makeText(ContextGetter.getApplicationCtxt(), "Fill Title", Toast.LENGTH_SHORT).show();
                    break;
                }

                while(mDescription.getText() == null || mDescription.getText().equals("")){
                    Toast.makeText(ContextGetter.getApplicationCtxt(), "Fill Description", Toast.LENGTH_SHORT).show();
                    break;
                }

                Status state = Status.valueOf((String) mState.getSelectedItem());
                Priority priority = Priority.valueOf((String) mPriority.getSelectedItem());
                TaskImpl task = new TaskImpl(state,priority,mTitle.getText().toString(),mDescription.getText().toString());

                MainActivity.addTask(task);
                dismiss();
                break;
            case R.id.btn_no:
                dismiss();
                break;
        }
    }

}
