package storage.dight;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import se.sics.dight.data.model.EntryId;
import se.sics.dight.data.model.Objekt;
import se.sics.dight.data.model.values.Value;

public class ApplicationRecord extends BaseRecord{
	
	private static ApplicationKlass klassContainer;
	
	public String newEntry(String application, String type){
		klassContainer = new ApplicationKlass(e);
		Value vApplication = e.makeStringValue(application);
		Value vType = e.makeStringValue(type);
		EntryId eid = null;
	
		Objekt entry = e.createObjekt(klassContainer.getKlass(), eid, null );
		entry.setValue(klassContainer.application, vApplication);
		entry.setValue(klassContainer.type, vType);
		
		entry.commit(klassContainer.getEntryAuthenticationAlgorithm());
		//Send to server
		return eid.getId().toString();
	}
	
	public Set<Object> getApplicationData(String application, String type){
		HashMap<String, Object> h = new HashMap<String, Object>();
		Set<HashMap> s = new HashSet<HashMap>();
		Value vMeasurement = e.makeStringValue(application);
		h.put("attribute", klassContainer.application);
		h.put("value", vMeasurement);
		s.add(h);
		
		Value vType = e.makeStringValue(type);
		h.put("attribute", klassContainer.type);
		h.put("value", type);
		s.add(h);
		
		return attributesQuery(s);
		
	}

}
