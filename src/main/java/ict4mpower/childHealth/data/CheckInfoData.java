package ict4mpower.childHealth.data;

import java.io.Serializable;

import org.apache.wicket.model.StringResourceModel;

/**
 * Data for checkable and editable data in the StatusPraesens task
 * @author Joakim Lindskog
 *
 */
public class CheckInfoData implements Serializable {
	private static final long serialVersionUID = 4232181354140299404L;
	
	/** Whether this item is selected or not */
	private boolean check;
	/** Info text accompanying this item */
	private String info;
	/** Text label to describe this item */
	private StringResourceModel label;
	
	/**
	 * Constructor
	 * @param label a model for the label text
	 */
	public CheckInfoData(StringResourceModel label) {
		this.label = label;
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
	public void setLabel(StringResourceModel label) {
		this.label = label;
	}
	public String getLabel() {
		return label.getObject();
	}
}
