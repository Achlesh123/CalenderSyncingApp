package com.example.calendersyncingapp.beans;

public class Appointment {


    private String id;

//    private Mentee mentee;
//
//    private Mentor mentor;

    private Long startTime;

    private Long endTime;

    private String status;

    private Long createdTime;

    private Long lastUpdatedTime;

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

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    public Long getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(Long lastUpdatedTime) {
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
