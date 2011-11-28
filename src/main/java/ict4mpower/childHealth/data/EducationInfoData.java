package ict4mpower.childHealth.data;

import java.io.Serializable;
import java.util.List;

/**
 * Data class for storing Education info for one part
 * @author Joakim Lindskog
 *
 */
public class EducationInfoData implements Serializable {
	private static final long serialVersionUID = -5329457188033766021L;
	
	private String header;
	private String text;
	private List<CheckableOption> options;

	public EducationInfoData(String header, String text, List<CheckableOption> options) {
		this.setHeader(header);
		this.setText(text);
		this.setOptions(options);
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<CheckableOption> getOptions() {
		return options;
	}

	public void setOptions(List<CheckableOption> options) {
		this.options = options;
	}
}
