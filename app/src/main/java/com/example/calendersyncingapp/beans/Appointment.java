package com.example.calendersyncingapp.beans;

import com.google.gson.annotations.SerializedName;

public class Appointment{

	@SerializedName("mentor_remarks")
	private String mentorRemarks;

	@SerializedName("created_time")
	private long createdTime;

	@SerializedName("start_time")
	private long startTime;

	@SerializedName("mentor")
	private Mentor mentor;

	@SerializedName("last_updated_time")
	private long lastUpdatedTime;

	@SerializedName("end_time")
	private long endTime;

	@SerializedName("id")
	private String id;

	@SerializedName("mentee")
	private Mentee mentee;

	@SerializedName("mentee_remarks")
	private String menteeRemarks;

	@SerializedName("status")
	private String status;

	public void setMentorRemarks(String mentorRemarks){
		this.mentorRemarks = mentorRemarks;
	}

	public String getMentorRemarks(){
		return mentorRemarks;
	}

	public void setCreatedTime(long createdTime){
		this.createdTime = createdTime;
	}

	public long getCreatedTime(){
		return createdTime;
	}

	public void setStartTime(long startTime){
		this.startTime = startTime;
	}

	public long getStartTime(){
		return startTime;
	}

	public void setMentor(Mentor mentor){
		this.mentor = mentor;
	}

	public Mentor getMentor(){
		return mentor;
	}

	public void setLastUpdatedTime(long lastUpdatedTime){
		this.lastUpdatedTime = lastUpdatedTime;
	}

	public long getLastUpdatedTime(){
		return lastUpdatedTime;
	}

	public void setEndTime(long endTime){
		this.endTime = endTime;
	}

	public long getEndTime(){
		return endTime;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setMentee(Mentee mentee){
		this.mentee = mentee;
	}

	public Mentee getMentee(){
		return mentee;
	}

	public void setMenteeRemarks(String menteeRemarks){
		this.menteeRemarks = menteeRemarks;
	}

	public String getMenteeRemarks(){
		return menteeRemarks;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"Appointment{" + 
			"mentor_remarks = '" + mentorRemarks + '\'' + 
			",created_time = '" + createdTime + '\'' + 
			",start_time = '" + startTime + '\'' + 
			",mentor = '" + mentor + '\'' + 
			",last_updated_time = '" + lastUpdatedTime + '\'' + 
			",end_time = '" + endTime + '\'' + 
			",id = '" + id + '\'' + 
			",mentee = '" + mentee + '\'' + 
			",mentee_remarks = '" + menteeRemarks + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}