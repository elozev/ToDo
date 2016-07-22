package com.example.emillozev.todo.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.emillozev.todo.MainActivity;
import com.example.emillozev.todo.R;

public class DialogNewTodo extends Dialog implements android.view.View.OnClickListener {

    public Context mContext;
    public Dialog mDialog;
    public Button mAccept, mCancel;

    public DialogNewTodo(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_layout);

        mAccept = (Button) findViewById(R.id.btn_yes);
        mCancel = (Button) findViewById(R.id.btn_no);

        mAccept.setOnClickListener(this);
        mCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_yes:
                dismiss();
                break;
            case R.id.btn_no:
                dismiss();
                break;
        }
    }

}
