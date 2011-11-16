package models;

import java.io.Serializable;
import java.util.Date;

public class Prescription extends BaseModel{
	private Date Start;
	private Date end;
	private String drug;
	private String dosage;
	private Boolean replaceble; 
	private int visitId;
	private String reason;
}
