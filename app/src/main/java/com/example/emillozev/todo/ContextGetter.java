package com.example.emillozev.todo;

import android.app.Application;
import android.content.Context;

public class ContextGetter extends Application {
    private static ContextGetter mInstance;

    public static ContextGetter getInstance(){
        return mInstance;
    }

    public static Context getApplicationCtxt(){
        return mInstance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        mInstance = this;
        super.onCreate();
    }
}
