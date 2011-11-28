package ict4mpower.childHealth.data;

import java.io.Serializable;

/**
 * Data object for checkable information in the Education task
 * @author Joakim Lindskog
 *
 */
public class CheckableOption implements Serializable {
	private static final long serialVersionUID = -8760200310456314580L;

	private String option;
	private boolean checked;
	
	/**
	 * Constructor
	 * @param option information text
	 */
	public CheckableOption(String option) {
		this.setOption(option);
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
}
