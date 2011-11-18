package storage.dight;

import java.io.IOException;
import java.util.List;

import se.sics.dight.data.model.EntryId;
import se.sics.dight.data.model.attributes.Attribute;
import se.sics.dight.data.security.Credential;
import se.sics.dight.storage.engine.Engine;

public class MeasurementKlass extends KlassContainer{

	Attribute measurement;
	Attribute unit;
	Attribute value;
	Attribute date;
	Attribute patientId;
	Long version = 3l;
		
	public MeasurementKlass(Engine e){
		super(e);
		klassName = "MeasurementRecord";
		createAttributes();
		klass = queryKlass(klassName);
		if(klass!=null && klass.getVersion() >= version){
			System.err.println("KLASSS FOUND");
			System.err.println(klass.getName() +" ");
	
		}
		else{
			System.err.println("KLASSS NOT FOUND; CREATING IT");
			createAndStoreKlass();
		}		
	
	}	
	
	protected void createAttributes(){
		this.measurement = e.makeStringAttribute("measurment");
		this.unit = e.makeStringAttribute("unit");
		this.value = e.makeDoubleAttribute("value");
		this.date = e.makeDateAttribute("date");
		this.patientId = e.makeStringAttribute("patientId");
	}
	
	protected void createAndStoreKlass(){
		byte[] b = new byte[20];
		EntryId eid = e.makeEntryId(b);
		klass = e.createKlass(1L, eid, "MedicalRecord");
		klass.addAttribute(measurement);
		klass.addAttribute(unit);
		klass.addAttribute(value);
		klass.addAttribute(date);
		klass.addAttribute(patientId);
		klass.commit(EAA);
		List<Credential> cred = null;
		try {
			DightSocket.CreateOperationResult(klass, klassCreds, e);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
