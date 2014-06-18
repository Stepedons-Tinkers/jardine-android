package co.nextix.jardine.model;

public class ProfileNotification {

	private String date;
	private String notification;

	public ProfileNotification(String date, String notification) {
		this.date = date;
		this.notification = notification;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getNotification() {
		return this.notification;
	}

	public void setNotification(String notification) {
		this.notification = notification;
	}
}
