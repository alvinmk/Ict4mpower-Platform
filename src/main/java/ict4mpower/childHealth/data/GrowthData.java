package ict4mpower.childHealth.data;

import ict4mpower.childHealth.ChildHealthData;
import ict4mpower.childHealth.panels.growth.Indicator;

import java.util.Date;
import java.util.List;

/**
 * Data class for the Growth task
 * @author Joakim Lindskog
 *
 */
public class GrowthData implements ChildHealthData {
	private static final long serialVersionUID = -7434447093247650316L;
	
	private static GrowthData instance = null;

	public static GrowthData instance() {
		if(instance == null) {
			instance = new GrowthData();
		}
		return instance;
	}
	
	/** Date of last edit */
	private Date date;
	
	// Growth indicators
	private List<Indicator>	indicators;
	
	// Feeding
	private String feeding;
	
	// PMTCT status
	private String pmtct;
	private String hivTestRadio;
	private String initTreatmentRadio;
	private Date initTreatmentDate;
	
	public GrowthData() {}

	public String getFeeding() {
		return feeding;
	}

	public void setFeeding(String feeding) {
		this.feeding = feeding;
	}

	public List<Indicator> getIndicators() {
		return indicators;
	}

	public void setIndicators(List<Indicator> indicators) {
		this.indicators = indicators;
	}
	
	public void setPmtct(String pmtct) {
		this.pmtct = pmtct;
	}
	
	public String getPmtct() {
		return pmtct;
	}

	public String getHivTestRadio() {
		return hivTestRadio;
	}

	public void setHivTestRadio(String hivTestRadio) {
		this.hivTestRadio = hivTestRadio;
	}

	public String getInitTreatmentRadio() {
		return initTreatmentRadio;
	}

	public void setInitTreatmentRadio(String initTreatmentRadio) {
		this.initTreatmentRadio = initTreatmentRadio;
	}

	public Date getInitTreatmentDate() {
		return initTreatmentDate;
	}

	public void setInitTreatmentDate(Date initTreatmentDate) {
		this.initTreatmentDate = initTreatmentDate;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public Date getDate() {
		return date;
	}

	public void reset() {
		setDate(null);
		setFeeding(null);
		setHivTestRadio(null);
		setIndicators(null);
		setInitTreatmentDate(null);
		setInitTreatmentRadio(null);
		setPmtct(null);
	}
}
