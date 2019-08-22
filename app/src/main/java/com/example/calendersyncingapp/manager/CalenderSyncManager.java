package com.example.calendersyncingapp.manager;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.provider.CalendarContract.Events;
import android.util.Log;
import android.widget.Toast;

import com.example.calendersyncingapp.base.CalenderSyncApplication;
import com.example.calendersyncingapp.beans.Appointment;

import java.util.TimeZone;

public class CalenderSyncManager {

    private static CalenderSyncManager mInstance;

    private CalenderSyncManager() {

    }

    public static CalenderSyncManager getInstance() {
        if (mInstance == null) {
            mInstance = new CalenderSyncManager();
        }
        return mInstance;
    }

    public void addEventToCalender(Context ourActivity, Appointment appointment) {
        try {
            String eventUriString = "content://com.android.calendar/events";
            ContentValues eventValues = new ContentValues();
            eventValues.put(Events.CALENDAR_ID, 1); // id, We need to choose from         // our mobile for primary its 1
            eventValues.put(Events.TITLE, "My First Calender Event");
            eventValues.put(Events.DESCRIPTION, "My Description");
//            eventValues.put(Events.EVENT_LOCATION, "Noida,UP ");
            eventValues.put(Events.DTSTART, appointment.getStartTime());
            eventValues.put(Events.DTEND, appointment.getEndTime());
            eventValues.put(Events.ALL_DAY, 0); // 1 for whole day //If it is bithday alarm or such
            eventValues.put(Events.STATUS, 1); // This information is
            eventValues.put(Events.EVENT_TIMEZONE, TimeZone.getTimeZone(TimeZone.getDefault().getID()).getID());
            eventValues.put(Events.HAS_ALARM, 0); // 0 for false, 1 for true
            Uri eventUri = ourActivity.getApplicationContext().getContentResolver().insert(Uri.parse(eventUriString), eventValues);
            long eventID = Long.parseLong(eventUri.getLastPathSegment());
            Log.i("eventID", eventID + "Event added to calender successfuly.");
            Toast.makeText(CalenderSyncApplication.getInstance().getApplicationContext(), "Event Added", Toast.LENGTH_SHORT).show();
        } catch (Exception ex) {
            Log.e("error", "Error in adding event on calendar" + ex.getMessage());
        }

    }


    private String getCalendarUriBase(Context con) {
        String calendarUriBase = null;
//        Uri calendars = Uri.parse("content://calendar/calendars");
//        Cursor managedCursor = null;
//        try {
//            managedCursor = con.managedQuery(calendars, null, null, null, null);
//        } catch (Exception e) {
//            // eat
//        }
//
//        if (managedCursor != null) {
//            calendarUriBase = "content://calendar/";
//        } else {
//            calendars = Uri.parse("content://com.android.calendar/calendars");
//            try {
//                managedCursor = managedQuery(calendars, null, null, null, null);
//            } catch (Exception e) {
//                // statement to print the stacktrace
//            }
//
//            if (managedCursor != null) {
//                calendarUriBase = "content://com.android.calendar/";
//            }
//
//        }

        return calendarUriBase;
    }


}
