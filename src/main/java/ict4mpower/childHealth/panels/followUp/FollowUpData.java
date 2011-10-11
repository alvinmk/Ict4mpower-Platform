package ict4mpower.childHealth.panels.followUp;

import java.io.Serializable;
import java.util.Date;

public class FollowUpData implements Serializable {
	private static final long serialVersionUID = -8893036385045807296L;
	private Date date;
	private String message;

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate() {
		return date;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
