package storage.dight;

import java.io.IOException;
import java.util.List;

import se.sics.dight.data.model.EntryId;
import se.sics.dight.data.model.Klass;
import se.sics.dight.data.model.attributes.Attribute;
import se.sics.dight.data.security.Credential;
import se.sics.dight.storage.engine.Engine;
import se.sics.dight.storage.store.query.QueryResult;

public class MedicalRecordKlass extends KlassContainer{
	Attribute type;
	Attribute application;
	Attribute visitId;
	Attribute date;
	Attribute patientId;
	Long version = 3l;
		
	public MedicalRecordKlass(Engine e){
		super(e);
		klassName = "MedicalRecord";
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
		this.type = e.makeStringAttribute("type");
		this.application = e.makeStringAttribute("application");
		this.visitId = e.makeBigintAttribute("visitId");
		this.date = e.makeDateAttribute("date");
		this.patientId = e.makeStringAttribute("patientId");
	}
	
	protected void createAndStoreKlass(){
		
		byte[] b = new byte[20];
		EntryId eid = e.makeEntryId(b);
		klass = e.createKlass(version, eid, klassName);
		klass.addAttribute(date);
		klass.addAttribute(patientId);
		klass.addAttribute(type);
		klass.addAttribute(application);
		klass.addAttribute(visitId);
		//Should be called last
		klass.commit(EAA);
		QueryResult result = null;
		try {
			result = DightSocket.CreateOperationResult(klass, klassCreds, e);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
