package storage.dight;

import java.io.IOException;

import org.apache.log4j.Logger;

import se.sics.dight.data.model.EntryId;
import se.sics.dight.data.model.Klass;
import se.sics.dight.data.model.attributes.Attribute;
import se.sics.dight.storage.engine.Engine;

public class ApplicationKlass extends KlassContainer{
	private static final Logger log = Logger.getLogger(ApplicationKlass.class);
	Attribute application;
	Attribute type;
	private Klass klass;
	
	public ApplicationKlass(Engine e){
		super(e);
		version=1l;
		klassName = "ApplicationRecord";
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

	@Override
	protected void createAttributes() {
		this.application = e.makeStringAttribute("application");
		this.type = e.makeStringAttribute("type");		
	}

	@Override
	protected void createAndStoreKlass() {
		EntryId eid = generateUniqeEntryId();
		klass = e.createKlass(1L, eid, klassName);
		klass.addAttribute(application);
		klass.addAttribute(type);
		klass.commit(EAA);
		try {
			DightSocket.CreateOperationResult(klass, klassCreds, e);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public Klass getKlass() {
		return klass;
	}	
}
