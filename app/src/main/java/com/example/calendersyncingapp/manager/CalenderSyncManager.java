package com.example.calendersyncingapp.manager;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
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


    public void setDataInCalendar(final Context context, final Appointment appointment) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                ContentResolver cr = context.getContentResolver();
                ContentValues values = new ContentValues();
                values.put(Events.DTSTART, appointment.getStartTime());
                values.put(Events.DTEND, appointment.getEndTime());
                values.put(Events.TITLE, "This is a sample title");
                values.put(Events.DESCRIPTION, "This is a sample description");
                values.put(Events.CALENDAR_ID, "23232");
                values.put(Events.EVENT_TIMEZONE, String.valueOf(TimeZone.getTimeZone(TimeZone.getDefault().getID())));
//                values.put(Events.EVENT_LOCATION, "");
                values.put(Events.ALL_DAY, Events.ALL_DAY);
//                values.put(Events.STATUS, 1);
//                values.put(Events.HAS_ALARM, 1);
                Uri uri = cr.insert(Events.CONTENT_URI, values);


//
//                Intent intent = new Intent(Intent.ACTION_INSERT_OR_EDIT);
//                intent.setType("vnd.android.cursor.item/event");
//                intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, appointment.getStartTime());
//                intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,appointment.getEndTime());
//                intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);
//                intent.putExtra(Events.TITLE, "Neel Birthday");
//                intent.putExtra(Events.DESCRIPTION, "This is a sample description");
//                intent.putExtra(Events.EVENT_LOCATION, "My Guest House");
//                intent.putExtra(Events.RRULE, "FREQ=YEARLY");
//                context.startActivity(intent);
                Toast.makeText(CalenderSyncApplication.getInstance().getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
            }
        }, 5000);


    }

    public void setDataListInCalender(final Context context, final Appointment appointment) {


        new Handler().post(new Runnable() {
            @Override
            public void run() {

                ContentResolver event = context.getContentResolver();
                ContentValues values = new ContentValues();
                values.put("calendar_id", 500);
                values.put("title", "this is title");
                values.put("allDay", 0);
                values.put("dtstart", appointment.getStartTime());
                values.put("dtend", appointment.getEndTime());
                values.put("description", "this is description");
                values.put(Events.EVENT_TIMEZONE, String.valueOf(TimeZone.getTimeZone(TimeZone.getDefault().getID())));
                Uri uri = event.insert(Events.CONTENT_URI, values);
            }
        });


    }

//    private void addEventToCalender(Context ourActivity, String title, String desc, String place, int status, long startDate, long endDte, boolean needReminder, boolean needMailService) {


    public void addEventToCalender(Context ourActivity, Appointment appointment) {
        try {
            String eventUriString = "content://com.android.calendar/events";
            ContentValues eventValues = new ContentValues();
            eventValues.put("calendar_id", 1); // id, We need to choose from         // our mobile for primary its 1
            eventValues.put("title", "My Title");
            eventValues.put("description", "My Description");
            eventValues.put("eventLocation", "Noida,UP ");

            eventValues.put("dtstart", appointment.getStartTime());
            eventValues.put("dtend", appointment.getEndTime());
            eventValues.put("allDay", 1); // 1 for whole day
            //eventValues.put("rrule", "FREQ=YEARLY");

            // values.put("allDay", 1); //If it is bithday alarm or such
            // kind (which should remind me for whole day) 0 for false, 1
            // for true
            eventValues.put("eventStatus", 1); // This information is
            // sufficient for most
            // entries tentative (0),
            // confirmed (1) or canceled
            // (2):
            eventValues.put("eventTimezone", String.valueOf(TimeZone.getTimeZone(TimeZone.getDefault().getID())));
            eventValues.put("hasAlarm", 1); // 0 for false, 1 for true
            Uri eventUri = ourActivity.getApplicationContext().getContentResolver().insert(Uri.parse(eventUriString), eventValues);
            long eventID = Long.parseLong(eventUri.getLastPathSegment());
            Log.i("eventID", eventID + "Event added to calender successfuly.");
        } catch (Exception ex) {
            Log.e("error", "Error in adding event on calendar" + ex.getMessage());
        }

    }


}
