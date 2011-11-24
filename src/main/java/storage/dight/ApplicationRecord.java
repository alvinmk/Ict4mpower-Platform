package storage.dight;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import se.sics.dight.data.model.EntryId;
import se.sics.dight.data.model.Objekt;
import se.sics.dight.data.model.templates.ValueTemplate;
import se.sics.dight.data.model.values.Value;

public class ApplicationRecord extends BaseRecord{
	
	private static ApplicationKlass klassContainer;
	
	public ApplicationRecord(){
		klassContainer = new ApplicationKlass(e);
		setKlassContainer(klassContainer);
	}
	
	public String newEntry(String application, String type, Object data){
		Value vApplication = e.makeStringValue(application);
		Value vType = e.makeStringValue(type);
		
		EntryId eid = klassContainer.generateUniqeEntryId();		
		byte[] payload = null;
		try {
			payload = serialize(data);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Objekt entry = e.createObjekt(klassContainer.getKlass(), eid, payload );
		entry.setValue(klassContainer.type, vType);
		entry.setValue(klassContainer.application, vApplication);		
		entry.commit(klassContainer.getEntryAuthenticationAlgorithm());
		//Send to server
		return eid.getId().toString();
	}
	
	public Set<Object> getApplicationData(String application, String type){
		HashMap<String, Object> h = new HashMap<String, Object>();
		Set<HashMap<String, Object>> s = new HashSet<HashMap<String, Object>>();
		ValueTemplate vApplication = e.makeStringValueTemplate(application);
		ValueTemplate vType = e.makeStringValueTemplate(type);
		h.put("attribute", klassContainer.type);
		h.put("value", vType);
		s.add(h);
		h.put("attribute", klassContainer.application);
		h.put("value", vApplication);
		s.add(h);
		return attributesQueryToObject(s);
		
	}

}
