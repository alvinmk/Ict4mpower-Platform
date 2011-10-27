package Models;

import java.io.Serializable;
import java.util.Date;

public class Prescription implements Serializable{
	private Date Start;
	private Date end;
	private String drug;
	private String dosage;
	private Boolean replaceble; 
	private int visitId;
	private String reason;
}
