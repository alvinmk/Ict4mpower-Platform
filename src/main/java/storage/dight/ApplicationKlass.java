package storage.dight;

import se.sics.dight.data.model.EntryId;
import se.sics.dight.data.model.attributes.Attribute;
import se.sics.dight.storage.engine.Engine;

public class ApplicationKlass extends KlassContainer{
	Attribute application;
	Attribute type;
	
	public ApplicationKlass(Engine e){
		super(e);
		this.application = e.makeStringAttribute("application");
		this.type = e.makeStringAttribute("type");
		byte[] b = new byte[20];
		EntryId eid = e.makeEntryId(b);
		klass.addAttribute(application);
		klass = e.createKlass(1L, eid, "MedicalRecord");
		klass.addAttribute(type);
		
		//Should be called last
		klass.commit(EAA);
	}	

}
