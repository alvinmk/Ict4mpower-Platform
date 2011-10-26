package ict4mpower.childHealth.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Component;

public class AdditionalData implements Serializable {
	private static final long serialVersionUID = -283564779395916587L;
	
	private static AdditionalData instance;
	
	public static AdditionalData instance() {
		if(instance == null) {
			instance = new AdditionalData();
		}
		return instance;
	}

	private List<String> reasons;
	
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
}
