package storage.dight;

import se.sics.dight.data.model.attributes.Attribute;
import se.sics.dight.storage.engine.Engine;

public class MedicalRecordKlass extends KlassContainer{
	Attribute type;
	Attribute application;
	Attribute visitId;
	Attribute date;
	Attribute patientId;
		
	public MedicalRecordKlass(Engine e){
		super(e);
		this.type = e.makeStringAttribute("type");
		this.application = e.makeStringAttribute("application");
		this.visitId = e.makeBigintAttribute("visitId");
		this.date = e.makeDateAttribute("date");
		this.patientId = e.makeSmallintAttribute("patientId");
		klass.addAttribute(date);
		klass.addAttribute(application);
		klass.addAttribute(type);
		klass.addAttribute(patientId);
		klass.addAttribute(visitId);
		//Should be called last
		klass.commit(EAA);
	}	
}
