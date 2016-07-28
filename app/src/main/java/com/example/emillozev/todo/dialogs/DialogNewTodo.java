package com.example.emillozev.todo.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.emillozev.todo.ContextGetter;
import com.example.emillozev.todo.R;

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
        mPriority = (Spinner) findViewById(R.id.spinnerPriority);
        mState = (Spinner) findViewById(R.id.spinnerState);


        ArrayAdapter<CharSequence> statusAdapter = ArrayAdapter
                .createFromResource(ContextGetter.getApplicationCtxt(),
                        R.array.status_array,
                        android.R.layout.simple_spinner_dropdown_item);
        mState.setAdapter(statusAdapter);

        ArrayAdapter<CharSequence> priorityAdapter = ArrayAdapter
                .createFromResource(ContextGetter.getApplicationCtxt(),
                        R.array.priority_array,
                        android.R.layout.simple_spinner_dropdown_item);
        mPriority.setAdapter(priorityAdapter);
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_yes:
                Toast.makeText(ContextGetter.getApplicationCtxt(), "Accepted", Toast.LENGTH_SHORT).show();

                dismiss();
                break;
            case R.id.btn_no:
                dismiss();
                break;
        }
    }

}
