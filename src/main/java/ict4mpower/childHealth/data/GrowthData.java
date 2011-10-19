package ict4mpower.childHealth.data;

import ict4mpower.childHealth.panels.growth.Indicator;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.wicket.model.StringResourceModel;

/**
 * Data for the Growth task
 * @author Joakim Lindskog
 *
 */
public class GrowthData implements Serializable {
	private static final long serialVersionUID = -7434447093247650316L;
	
	private static GrowthData instance = null;

	public static GrowthData instance() {
		if(instance == null) {
			instance = new GrowthData();
		}
		return instance;
	}
	
	// Growth indicators
	private List<Indicator>	indicators;
	private float head;
	private float height;
	private float weight;
	
	// Feeding
	private StringResourceModel feeding;
	
	// PMTCT status
	private StringResourceModel pmtct;
	private StringResourceModel hivTestRadio;
	private StringResourceModel initTreatmentRadio;
	private Date initTreatmentDate;
	
	private GrowthData() {}
	
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

	public StringResourceModel getFeeding() {
		return feeding;
	}

	public void setFeeding(StringResourceModel feeding) {
		this.feeding = feeding;
	}

	public List<Indicator> getIndicators() {
		return indicators;
	}

	public void setIndicators(List<Indicator> indicators) {
		this.indicators = indicators;
	}
	
	public void setPmtct(StringResourceModel pmtct) {
		this.pmtct = pmtct;
	}
	
	public StringResourceModel getPmtct() {
		return pmtct;
	}

	public StringResourceModel getHivTestRadio() {
		return hivTestRadio;
	}

	public void setHivTestRadio(StringResourceModel hivTestRadio) {
		this.hivTestRadio = hivTestRadio;
	}

	public StringResourceModel getInitTreatmentRadio() {
		return initTreatmentRadio;
	}

	public void setInitTreatmentRadio(StringResourceModel initTreatmentRadio) {
		this.initTreatmentRadio = initTreatmentRadio;
	}

	public Date getInitTreatmentDate() {
		return initTreatmentDate;
	}

	public void setInitTreatmentDate(Date initTreatmentDate) {
		this.initTreatmentDate = initTreatmentDate;
	}
}
