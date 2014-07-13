package co.nextix.jardine.database.records;

public class CalendarRecord {
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

	// ===========================================================
	// Private fields
	// ===========================================================

	private long id;
	private String activitytype;
	private String date_start;
	private String due_date;
	private String description;
	private String subject;
	private String time_start;
	private String time_end;
	private long activity;
	private String modifiedtime;
	private String createdtime;
	private long smownerid;

	// ===========================================================
	// Public constructors
	// ===========================================================

	public CalendarRecord() {
	}

	public CalendarRecord(long id, String activityType, String dateStart,
			String dateEnd, String description, String subject,
			String timeStart, String timeEnd, long activity,
			String modifiedTime, String createdTime, long user) {

		this.id = id;
		this.activitytype = activityType;
		this.date_start = dateStart;
		this.due_date = dateEnd;
		this.description = description;
		this.subject = subject;
		this.time_start = timeStart;
		this.time_end = timeEnd;
		this.activity = activity;
		this.modifiedtime = modifiedTime;
		this.createdtime = createdTime;
		this.smownerid = user;
	}

	// ===========================================================
	// Public methods
	// ===========================================================

	public long getId() {
		return this.id;
	}

	public void setActivityType(String activityType) {
		this.activitytype = activityType;
	}

	public String getActivityType() {
		return this.activitytype;
	}

	public void setDateStart(String dateStart) {
		this.date_start = dateStart;
	}

	public String getDateStart() {
		return this.date_start;
	}

	public void setDateEnd(String dateEnd) {
		this.due_date = dateEnd;
	}

	public String getDateEnd() {
		return this.due_date;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setTimeStart(String timeStart) {
		this.time_start = timeStart;
	}

	public String getTimeStart() {
		return this.time_start;
	}

	public void setTimeEnd(String timeEnd) {
		this.time_end = timeEnd;
	}

	public String getTimeEnd() {
		return this.time_end;
	}

	public void setActivity(long activity) {
		this.activity = activity;
	}

	public long getActivity() {
		return this.activity;
	}

	public void setModifiedTime(String modifiedTime) {
		this.modifiedtime = modifiedTime;
	}

	public String getModifiedTime() {
		return this.modifiedtime;
	}

	public void setCreatetTime(String createtTime) {
		this.createdtime = createtTime;
	}

	public String getCreatetTime() {
		return this.createdtime;
	}

	public void setAssignedUser(long assignedUser) {
		this.smownerid = assignedUser;
	}

	public long getAssignedUser() {
		return this.smownerid;
	}
}
