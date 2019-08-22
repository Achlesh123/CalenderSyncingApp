package com.example.calendersyncingapp.beans;

public class Appointment {


    private String id;

//    private Mentee mentee;
//
//    private Mentor mentor;

    private long startTime;

    private long endTime;

    private String status;

    private long createdTime;

    private long lastUpdatedTime;

    private String mentorRemarks;

    private String menteeRemarks;

    public Appointment() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public long getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(long lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public String getMentorRemarks() {
        return mentorRemarks;
    }

    public void setMentorRemarks(String mentorRemarks) {
        this.mentorRemarks = mentorRemarks;
    }

    public String getMenteeRemarks() {
        return menteeRemarks;
    }

    public void setMenteeRemarks(String menteeRemarks) {
        this.menteeRemarks = menteeRemarks;
    }
}
