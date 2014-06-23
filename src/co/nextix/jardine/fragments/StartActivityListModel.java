package co.nextix.jardine.fragments;

public class StartActivityListModel {
	private String CrmNo = "";
	private String Workplan = "";
	private String ActivityType = "";
	private String StartTime = "";
	private String EndTime = "";
	private String AssignedTo = "";
	private String Action = "";

	/*********** Get Methods ****************/
	
	/**
	 * @return the crmNo
	 */
	public String getCrmNo() {
		return CrmNo;
	}

	/**
	 * @return the workplan
	 */
	public String getWorkplan() {
		return Workplan;
	}

	/**
	 * @return the activityType
	 */
	public String getActivityType() {
		return ActivityType;
	}

	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return StartTime;
	}

	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return EndTime;
	}

	/**
	 * @return the assignedTo
	 */
	public String getAssignedTo() {
		return AssignedTo;
	}

	/**
	 * @return the action
	 */
	public String getAction() {
		return Action;
	}

	/*********** Set Methods ******************/
	
	/**
	 * @param crmNo
	 *            the crmNo to set
	 */
	public void setCrmNo(String crmNo) {
		CrmNo = crmNo;
	}

	/**
	 * @param workplan
	 *            the workplan to set
	 */
	public void setWorkplan(String workplan) {
		Workplan = workplan;
	}

	/**
	 * @param activityType
	 *            the activityType to set
	 */
	public void setActivityType(String activityType) {
		ActivityType = activityType;
	}

	/**
	 * @param startTime
	 *            the startTime to set
	 */
	public void setStartTime(String startTime) {
		StartTime = startTime;
	}

	/**
	 * @param endTime
	 *            the endTime to set
	 */
	public void setEndTime(String endTime) {
		EndTime = endTime;
	}

	/**
	 * @param assignedTo
	 *            the assignedTo to set
	 */
	public void setAssignedTo(String assignedTo) {
		AssignedTo = assignedTo;
	}

	/**
	 * @param action
	 *            the action to set
	 */
	public void setAction(String action) {
		Action = action;
	}
}
