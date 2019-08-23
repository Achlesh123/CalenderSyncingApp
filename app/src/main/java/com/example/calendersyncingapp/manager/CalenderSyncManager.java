package com.example.calendersyncingapp.manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract.Events;
import android.util.Log;
import android.widget.Toast;

import com.example.calendersyncingapp.base.CalenderSyncApplication;
import com.example.calendersyncingapp.beans.Appointment;
import com.example.calendersyncingapp.beans.AppointmentDemo;
import com.example.calendersyncingapp.beans.CalendarEvent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

public class CalenderSyncManager {

    private static CalenderSyncManager mInstance;
    private Context ctx;
    private String baseUri;

    private CalenderSyncManager() {

    }

    public static CalenderSyncManager getInstance() {
        if (mInstance == null) {
            mInstance = new CalenderSyncManager();
        }
        return mInstance;
    }

    public void addEventToCalender(Context ourActivity, AppointmentDemo appointmentDemo) {
        try {
            String eventUriString = "content://com.android.calendar/events";
            ContentValues eventValues = new ContentValues();
            eventValues.put(Events.CALENDAR_ID, 1); // id, We need to choose from         // our mobile for primary its 1
            eventValues.put(Events.TITLE, "My First Calender Event");
            eventValues.put(Events.DESCRIPTION, "My Description");
//            eventValues.put(Events.EVENT_LOCATION, "Noida,UP ");
            eventValues.put(Events.DTSTART, appointmentDemo.getStartTime());
            eventValues.put(Events.DTEND, appointmentDemo.getEndTime());
            eventValues.put(Events.ALL_DAY, 0); // 1 for whole day //If it is bithday alarm or such
            eventValues.put(Events.STATUS, 1); // This information is
            eventValues.put(Events.EVENT_TIMEZONE, TimeZone.getTimeZone(TimeZone.getDefault().getID()).getID());
            eventValues.put(Events.HAS_ALARM, 0); // 0 for false, 1 for true
            eventValues.put(Events.CAN_PARTIALLY_UPDATE, 1);
            eventValues.put(Events.CALENDAR_ACCESS_LEVEL, 200);
            Uri eventUri = ourActivity.getApplicationContext().getContentResolver().insert(Uri.parse(eventUriString), eventValues);
            long eventID = Long.parseLong(eventUri.getLastPathSegment());
            Log.i("eventID", eventID + " Event added to calender successfuly.");
            Toast.makeText(CalenderSyncApplication.getInstance().getApplicationContext(), "Event Added", Toast.LENGTH_SHORT).show();
        } catch (Exception ex) {
            Log.e("error", "Error in adding event on calendar" + ex.getMessage());
        }

    }

    public static ContentValues toContentValues(CalendarEvent evt) {
        ContentValues cv = new ContentValues();
//        cv.put("calendar_id", evt.getIdCalendar());
//        cv.put("title", evt.getTitle());
//        cv.put("description", evt.getDescr());
//        cv.put("eventLocation", evt.getLocation());
//        cv.put("dtstart", evt.getStartTime());
//        cv.put("dtend", evt.getEndTime());
//        cv.put("eventStatus", 1);
//        cv.put("visibility", 0);
//        cv.put("transparency", 0);

        return cv;

    }


    public void addEvent(Context ctx, CalendarEvent evt) {
        //Log.d(Params.LOG_APP, "Insert event ["+evt+"]");

        try {
//            Uri evtUri = ctx.getContentResolver().insert(getCalendarUri("events"), CalendarEvent.toContentValues(evt));
//            Log.d("URI : ", "" + evtUri);
        }
        catch(Throwable t) {
            //Log.e(Params.LOG_APP, "", t);
        }
    }

    public void setContext(Context context) {
        this.ctx = context;
        this.baseUri = getCalendarUriBase();
    }

    private Uri getCalendarUri(String path) {
        return Uri.parse(baseUri + "/" + path);
    }

    private String getCalendarUriBase() {
        String calendarUriBase = null;
        Uri calendars = Uri.parse("content://calendar/calendars");
        Cursor managedCursor = null;
        try {
            managedCursor = ctx.getContentResolver().query(calendars, null, null, null, null);
        } catch (Exception e) {
            // e.printStackTrace();
        }

        if (managedCursor != null) {
            calendarUriBase = "content://calendar/";
        } else {
            calendars = Uri.parse("content://com.android.calendar/calendars");
            try {
                managedCursor = ctx.getContentResolver().query(calendars, null, null, null, null);
            } catch (Exception e) {
                // e.printStackTrace();
            }

            if (managedCursor != null) {
                calendarUriBase = "content://com.android.calendar/";
            }

        }

        Log.d("URI : ", "URI ["+calendarUriBase+"]");
        return calendarUriBase;
    }


    public void addOriginalAppointmentToCalender(Context ourActivity, Appointment appointment) {
        try {
            String eventUriString = "content://com.android.calendar/events";
            ContentValues eventValues = new ContentValues();
            eventValues.put(Events.CALENDAR_ID, 1); // id, We need to choose from         // our mobile for primary its 1
            eventValues.put(Events.TITLE, "Your call appointment is schedule with "+appointment.getMentee().getName());
            eventValues.put(Events.DESCRIPTION, appointment.getMenteeRemarks());
            eventValues.put(Events.EVENT_LOCATION, appointment.getId());
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

    public ArrayList<String> nameOfEvent = new ArrayList<String>();


    public ArrayList<String> readCalendarEvent(Context context) {
        try {
            Cursor cursor = context.getContentResolver().query(Uri.parse("content://com.android.calendar/events"),
                    new String[] { "calendar_id", "title", "description",
                            "dtstart", "dtend", "eventLocation" }, null,
                    null, null);
            cursor.moveToFirst();
            // fetching calendars name
            String CNames[] = new String[cursor.getCount()];

            // fetching calendars id
            nameOfEvent.clear();

            for (int i = 0; i < CNames.length; i++) {

                CalendarEvent calendarEvent = new CalendarEvent();

                calendarEvent.setCalendarId(Integer.parseInt(cursor.getString(0)));
                calendarEvent.setTitle(cursor.getString(1));
                calendarEvent.setDescription(cursor.getString(2));
                calendarEvent.setStartTime(Long.parseLong(cursor.getString(3)));
                calendarEvent.setEndTime(Long.parseLong(cursor.getString(4)));
                nameOfEvent.add(cursor.getString(5));
                CNames[i] = cursor.getString(1);
                cursor.moveToNext();

            }
        } catch (Exception e){
            Log.e("CalenderSyncManager", e.toString());
        }

        return nameOfEvent;
    }


    public static String getDate(long milliSeconds) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }


}
