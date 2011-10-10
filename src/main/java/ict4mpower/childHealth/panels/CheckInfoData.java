package ict4mpower.childHealth.panels;

import java.io.Serializable;

import org.apache.wicket.model.StringResourceModel;

public class CheckInfoData implements Serializable {
	private static final long serialVersionUID = 4232181354140299404L;
	
	private boolean check;
	private String info;
	private String label;
	
	public CheckInfoData(StringResourceModel label) {
		this.label = label.getObject();
	}
	
	public void setCheck(boolean check) {
		this.check = check;
	}
	public boolean isCheck() {
		return check;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getInfo() {
		return info;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getLabel() {
		return label;
	}
}
