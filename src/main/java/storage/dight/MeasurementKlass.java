package storage.dight;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import se.sics.dight.data.model.EntryId;
import se.sics.dight.data.model.Klass;
import se.sics.dight.data.model.attributes.Attribute;
import se.sics.dight.data.security.Credential;
import se.sics.dight.storage.engine.Engine;

public class MeasurementKlass extends KlassContainer{
	private static final Logger log = Logger.getLogger(MeasurementKlass.class);
	private Klass klass;
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
		if(klass!=null){
			log.info(klass.getName() +" exist in database");
		}
		else{
			log.info(klassName +" does not exsitst in database, creating it");
			createAndStoreKlass();
		}		
	}	
	
	protected void createAttributes(){
		this.measurement = e.makeStringAttribute("measurement");
		this.unit = e.makeStringAttribute("unit");
		this.value = e.makeDoubleAttribute("value");
		this.date = e.makeDateAttribute("date");
		this.patientId = e.makeStringAttribute("patientId");
		
	}
	
	protected void createAndStoreKlass(){		
		EntryId eid = generateUniqeEntryId();
		klass = e.createKlass(1L, eid, klassName);		
		klass.addAttribute(measurement);
		klass.addAttribute(unit);
		klass.addAttribute(value);
		klass.addAttribute(date);
		klass.addAttribute(patientId);
		klass.commit(EAA);
		try {
			DightSocket.CreateOperationResult(klass, klassCreds, e);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	@Override
	public Klass getKlass() {
		return klass;
	}
}
