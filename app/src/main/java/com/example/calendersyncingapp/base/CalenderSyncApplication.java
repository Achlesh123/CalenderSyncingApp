package com.example.calendersyncingapp.base;

import android.app.Application;

public class CalenderSyncApplication extends Application {

    private static CalenderSyncApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static CalenderSyncApplication getInstance() {
        return mInstance;
    }
}
