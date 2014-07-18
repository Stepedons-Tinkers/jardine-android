package co.nextix.jardine.web.models;

import com.google.gson.annotations.SerializedName;

public class CalendarModel {
	// "activitytype": "Task",
	// "contactid": null,
	// "createdtime": "2014-06-25 20:41:43",
	// "crmid": null,
	// "date_start": "2014-06-25",
	// "description":
	// "Follow-up for Customer CUST0000010 - Company ABC&amp;amp;#039;s will be on 2014-06-25.",
	// "due_date": "2014-06-25",
	// "duration_hours": "",
	// "duration_minutes": "",
	// "eventstatus": "",
	// "location": "",
	// "modifiedby": "1",
	// "modifiedtime": "2014-06-25 20:41:43",
	// "notime": "1",
	// "priority": "High",
	// "recurringtype": "",
	// "reminder_time": null,
	// "sendnotification": "1",
	// "smownerid": "18",
	// "status": "Not Started",
	// "subject":
	// "Follow-up for Customer CUST0000010 - Company ABC&amp;amp;#039;s will be on 2014-06-25.",
	// "time_end": "07:10:00",
	// "time_start": "07:00:00",
	// "visibility": "Private",
	// "activityid": "499",
	// "deleted": "0"

	@SerializedName("crmid")
	private String crmid;

	@SerializedName("activitytype")
	private String activitytype;

	@SerializedName("date_start")
	private String date_start;

	@SerializedName("due_date")
	private String due_date;

	@SerializedName("description")
	private String description;

	@SerializedName("subject")
	private String subject;

	@SerializedName("time_start")
	private String time_start;

	@SerializedName("time_end")
	private String time_end;

	@SerializedName("activityid")
	private String activityid;

	@SerializedName("createdtime")
	private String created_time;

	@SerializedName("modifiedtime")
	private String modified_time;

	@SerializedName("smownerid")
	private String user_id;

	public String getCrmID() {
		return this.crmid;
	}

	public void setCrmID(String crmid) {
		this.crmid = crmid;
	}

	public String getActivityType() {
		return this.activitytype;
	}

	public void setActivityType(String activityType) {
		this.activitytype = activityType;
	}

	public String getDateStart() {
		return this.date_start;
	}

	public void setDateStart(String dateStart) {
		this.date_start = dateStart;
	}

	public String getDueDate() {
		return this.due_date;
	}

	public void setDueDate(String dueDate) {
		this.due_date = dueDate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTimeStart() {
		return this.time_start;
	}

	public void setTimeStart(String timeStart) {
		this.time_start = timeStart;
	}

	public String getTimeEnd() {
		return this.time_end;
	}

	public void setTimeEnd(String timeEnd) {
		this.time_end = timeEnd;
	}

	public String getActivityId() {
		return this.activityid;
	}

	public void setActivityId(String activityId) {
		this.activityid = activityId;
	}

	public String getCreatedTime() {
		return this.created_time;
	}

	public void setCreatedTime(String createdTime) {
		this.created_time = createdTime;
	}

	public String getModifiedTime() {
		return this.modified_time;
	}

	public void setModifiedTime(String modifiedTime) {
		this.modified_time = modifiedTime;
	}

	public String getUserId() {
		return this.user_id;
	}

	public void setUserId(String userId) {
		this.user_id = userId;
	}
}
