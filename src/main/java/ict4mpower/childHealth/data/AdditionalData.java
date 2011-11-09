package ict4mpower.childHealth.data;

import ict4mpower.childHealth.ChildHealthData;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.wicket.Component;

/**
 * Data class for the AdditionalInfo task
 * @author Joakim Lindskog
 *
 */
public class AdditionalData implements ChildHealthData {
	private static final long serialVersionUID = -283564779395916587L;
	
	private static AdditionalData instance;
	
	public static AdditionalData instance() {
		if(instance == null) {
			instance = new AdditionalData();
		}
		return instance;
	}

	private List<String> reasons;
	private Date date;
	
	private AdditionalData() {}
	
	public List<String> getReasons() {
		if(reasons == null) {
			reasons = new ArrayList<String>();
		}
		return reasons;
	}

	public void setReasons(List<String> reasons) {
		this.reasons = reasons;
	}
	
	public String getReasonsAsString(Component c) {
		String rString = "";
		for(String s : getReasons()) {
			rString += c.getString(s)+"\n";
		}
		return rString;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate() {
		return date;
	}

	public void reset() {
		setDate(null);
		setReasons(null);
	}
}
