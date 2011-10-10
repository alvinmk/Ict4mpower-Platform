package ict4mpower.childHealth.panels;

import java.io.Serializable;

public class SingleTextData implements Serializable {
	private static final long serialVersionUID = -4999299134839002267L;

	private String text;

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
