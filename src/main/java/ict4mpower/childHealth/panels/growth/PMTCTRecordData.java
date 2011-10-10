package ict4mpower.childHealth.panels.growth;

import java.io.Serializable;
import java.util.Date;

import org.apache.wicket.model.StringResourceModel;

public class PMTCTRecordData implements Serializable {
	private static final long serialVersionUID = -1043567552158221408L;

	private StringResourceModel pmtct;
	private StringResourceModel hivTest;
	private StringResourceModel initTreatment;
	private Date date;
	
	public void setPmtct(StringResourceModel pmtct) {
		this.pmtct = pmtct;
	}
	public StringResourceModel getPmtct() {
		return pmtct;
	}
	public void setHivTest(StringResourceModel hivTest) {
		this.hivTest = hivTest;
	}
	public StringResourceModel getHivTest() {
		return hivTest;
	}
	public void setInitTreatment(StringResourceModel initTreatment) {
		this.initTreatment = initTreatment;
	}
	public StringResourceModel getInitTreatment() {
		return initTreatment;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getDate() {
		return date;
	}
}
