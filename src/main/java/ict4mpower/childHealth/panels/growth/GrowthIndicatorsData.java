package ict4mpower.childHealth.panels.growth;

import java.io.Serializable;

public class GrowthIndicatorsData implements Serializable {
	private static final long serialVersionUID = -7434447093247650316L;
	
	private float head;
	private float height;
	private float weight;
	
	public void setHead(float head) {
		this.head = head;
	}
	public float getHead() {
		return head;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	public float getHeight() {
		return height;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public float getWeight() {
		return weight;
	}
}
