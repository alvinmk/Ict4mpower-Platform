package storage.dight;

import se.sics.dight.data.model.EntryId;
import se.sics.dight.data.model.attributes.Attribute;
import se.sics.dight.storage.engine.Engine;

public class MeasurementKlass extends KlassContainer{

	Attribute measurement;
	Attribute unit;
	Attribute value;
	Attribute date;
	Attribute patientId;
		
	public MeasurementKlass(Engine e){
		super(e);
		this.measurement = e.makeStringAttribute("measurment");
		this.unit = e.makeStringAttribute("unit");
		this.value = e.makeSmallintAttribute("value");
		this.date = e.makeDateAttribute("date");
		this.patientId = e.makeSmallintAttribute("patientId");
		byte[] b = new byte[20];
		EntryId eid = e.makeEntryId(b);
		klass = e.createKlass(1L, eid, "MedicalRecord");
		klass.addAttribute(measurement);
		klass.addAttribute(unit);
		klass.addAttribute(value);
		klass.addAttribute(date);
		klass.addAttribute(patientId);
		
		//Should be called last
		klass.commit(EAA);
	}	
}
