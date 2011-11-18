package storage.dight;

import java.io.IOException;

import se.sics.dight.data.model.EntryId;
import se.sics.dight.data.model.attributes.Attribute;
import se.sics.dight.storage.engine.Engine;

public class ApplicationKlass extends KlassContainer{
	Attribute application;
	Attribute type;
	
	public ApplicationKlass(Engine e){
		super(e);
		version=1l;
		klassName = "ApplicationRecord";
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

	@Override
	protected void createAttributes() {
		this.application = e.makeStringAttribute("application");
		this.type = e.makeStringAttribute("type");		
	}

	@Override
	protected void createAndStoreKlass() {
		byte[] b = new byte[20];
		EntryId eid = e.makeEntryId(b);
		klass = e.createKlass(1L, eid, klassName);
		
		klass.addAttribute(application);
		klass.addAttribute(this.type);
		klass.commit(EAA);
		try {
			DightSocket.CreateOperationResult(klass, klassCreds, e);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}	
}
