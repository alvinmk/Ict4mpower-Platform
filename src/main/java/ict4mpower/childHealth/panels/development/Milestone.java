package ict4mpower.childHealth.panels.development;

import java.io.Serializable;
import java.util.Date;

public class Milestone implements Serializable {
	private static final long serialVersionUID = -9155283621010002742L;
	
	public MilestoneTests tests;
	public Date date;
	public short grossMotor;
	public short fineMotor;
	public short communication;
	public short cognitive;
	public Short[] hearing;
	public Short[] eyesight;
	
	public Milestone(MilestoneTests tests, Date measuredDate, short grossMotor, short fineMotor,
			short communication, short cognitive, Short[] hearing, Short[] eyesight) {
		this.tests = tests;
		this.date = measuredDate;
		this.grossMotor = grossMotor;
		this.fineMotor = fineMotor;
		this.communication = communication;
		this.cognitive = cognitive;
		this.hearing = hearing;
		this.eyesight = eyesight;
	}
}
