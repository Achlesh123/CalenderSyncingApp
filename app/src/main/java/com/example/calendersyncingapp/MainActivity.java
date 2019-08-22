package com.example.calendersyncingapp;

import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.calendersyncingapp.beans.Appointment;
import com.example.calendersyncingapp.manager.CalenderSyncManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkForPermission();
    }

    public void addEventInCalender() {
        final Appointment appointment  = new Appointment();
        appointment.setStartTime(1566633600000L);   //  Saturday, 24 August 2019 08:00:00
        appointment.setEndTime(1566637200000L);     //  Saturday, 24 August 2019 09:00:00
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                CalenderSyncManager.getInstance().addEventToCalender(MainActivity.this, appointment);
            }
        }, 5000);
    }

    private void checkForPermission() {

        String[] permissions = {
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.READ_CALENDAR,
                android.Manifest.permission.WRITE_CALENDAR,
                android.Manifest.permission.READ_CONTACTS
        };



        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, permissions, 200);
            Log.d(TAG, "SOME PERMISSION NOT GRANTED");

        } else {
            Log.d(TAG, "PERMISSION GRANTED");
            addEventInCalender();
        }
    }

    @Override
    public void onRequestPermissionsResult(int permsRequestCode, String permissions[], int[] grantResults) {

        switch (permsRequestCode) {

            case 200:

                break;

        }

        Log.d(TAG, "permissions : "+ permissions.toString());
        Log.d(TAG, "grantResults : "+ grantResults.toString());

    }
}
