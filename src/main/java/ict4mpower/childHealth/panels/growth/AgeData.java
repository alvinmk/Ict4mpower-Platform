package ict4mpower.childHealth.panels.growth;

import java.io.Serializable;

public class AgeData implements Serializable {
	private static final long serialVersionUID = 8881353048567347442L;

	private int weeksMonths;
	private int extraDays;
	
	public AgeData(int weeksMonths, int extraDays) {
		this.setWeeksMonths(weeksMonths);
		this.setExtraDays(extraDays);
	}

	public int getWeeksMonths() {
		return weeksMonths;
	}

	public void setWeeksMonths(int weeksMonths) {
		this.weeksMonths = weeksMonths;
	}

	public int getExtraDays() {
		return extraDays;
	}

	public void setExtraDays(int extraDays) {
		this.extraDays = extraDays;
	}
}
