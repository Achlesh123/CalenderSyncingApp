package com.example.calendersyncingapp;

import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.calendersyncingapp.beans.Appointment;
import com.example.calendersyncingapp.beans.AppointmentDemo;
import com.example.calendersyncingapp.beans.CalendarEvent;
import com.example.calendersyncingapp.manager.CalenderSyncManager;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkForPermission();
    }


    public void addOrignalAppointment() {

        String jsonString = "{\n" +
                "  \"created_time\": 1566553802180,\n" +
                "  \"end_time\": 1566653444659,\n" +
                "  \"id\": \"039ba072-397d-4869-8585-7ba28e0da6dc\",\n" +
                "  \"last_updated_time\": 1566553845981,\n" +
                "  \"mentee\": {\n" +
                "    \"name\": \"HimanshuRR\",\n" +
                "    \"url\": \"\",\n" +
                "    \"user_id\": 1126\n" +
                "  },\n" +
                "  \"mentee_remarks\": \"Yes I am looking to buy this domain may the force really good at it and the first one is for the delay I had a chance for you to see you tomorrow morning to you\",\n" +
                "  \"mentor\": {\n" +
                "    \"name\": \"Ravichandran\",\n" +
                "    \"url\": \"\",\n" +
                "    \"user_id\": 1233\n" +
                "  },\n" +
                "  \"mentor_remarks\": \"Yes I am looking to buy this domain may the force really good at it and the first one is for the delay I had a chance for you to see you tomorrow morning to you\",\n" +
                "  \"start_time\": 1566649844659,\n" +
                "  \"status\": \"ACCEPTED\"\n" +
                "}";
        Gson gson = new Gson();
        final Appointment appointment = gson.fromJson(jsonString, Appointment.class);

//        for(int i=0; i<3; i++) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    ArrayList<String> eventArrayList = CalenderSyncManager.getInstance().readCalendarEvent(MainActivity.this);

                    if(!eventArrayList.contains(appointment.getId())) {
                        CalenderSyncManager.getInstance().addOriginalAppointmentToCalender(MainActivity.this,appointment);
                    }

                }
            }, 3000);
//        }

    }

    public void addEventInCalender() {
        final AppointmentDemo appointmentDemo = new AppointmentDemo();
        appointmentDemo.setStartTime(1566633600000L);   //  Saturday, 24 August 2019 08:00:00
        appointmentDemo.setEndTime(1566637200000L);     //  Saturday, 24 August 2019 09:00:00
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                CalenderSyncManager.getInstance().addEventToCalender(MainActivity.this, appointmentDemo);
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
            addOrignalAppointment();
        }
    }

    @Override
    public void onRequestPermissionsResult(int permsRequestCode, String permissions[], int[] grantResults) {

        switch (permsRequestCode) {

            case 200:

                boolean calenderGranted = grantResults[2] == PackageManager.PERMISSION_GRANTED;

                if(calenderGranted) {
                    addOrignalAppointment();
                }

                break;

        }

        Log.d(TAG, "permissions : "+ permissions.toString());
        Log.d(TAG, "grantResults : "+ grantResults.toString());

    }
}
