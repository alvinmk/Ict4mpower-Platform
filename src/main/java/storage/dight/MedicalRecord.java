package storage.dight;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import se.sics.dight.data.model.EntryId;
import se.sics.dight.data.model.Objekt;
import se.sics.dight.data.model.templates.ValueTemplate;
import se.sics.dight.data.model.values.Value;

public class MedicalRecord extends BaseRecord{
	private static MedicalRecordKlass klassContainer;

	public MedicalRecord(){
		setKlass((KlassContainer) klassContainer.getKlass());
	}
	
	public String newEntry(Object data, String type, String app,String patientId, long visitId) {
		klassContainer = new MedicalRecordKlass(e);
		Value vType = e.makeStringValue(type);
		Value vApp = e.makeStringValue(app);
		Value vPatientId = e.makeStringValue(patientId);
		Value vVisitId = e.makeBigintValue(visitId);
		Value vDate = e.makeDateValue(new Date());
		EntryId eid = null;
		byte[] payload = null;
		try {
			payload = serialize(data);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		Objekt entry = e.createObjekt(klassContainer.getKlass(), eid, payload );
		entry.setValue(klassContainer.application, vApp);
		entry.setValue(klassContainer.patientId, vPatientId);
		entry.setValue(klassContainer.type, vType);
		entry.setValue(klassContainer.visitId, vVisitId);
		entry.setValue(klassContainer.date, vDate);
		entry.commit(klassContainer.getEntryAuthenticationAlgorithm());
		//Send to server
		return eid.getId().toString();
	}
	
	public Set<Object> getObjectsFromTypeAndVisitId(String type, long visitId){
		HashMap<String, Object> h = new HashMap<String, Object>();
		Set<HashMap> s = new HashSet<HashMap>();
		Value typeVal = e.makeStringValue(type);
		h.put("attribute", klassContainer.type);
		h.put("value", typeVal);
		
		Value patientIdVal = e.makeBigintValue(visitId);
		h.put("attribute", klassContainer.visitId);
		h.put("value", visitId);
		s.add(h);		
		return attributesQuery(s);
	}
	
	public Set<Object> getObjectsFromVisitId(long visitId){
		HashMap<String, Object> h = new HashMap<String, Object>();
		Set<HashMap> s = new HashSet<HashMap>();
		Value visitVal = e.makeBigintValue(visitId);
		h.put("attribute", klassContainer.visitId);
		h.put("value", visitVal);
		s.add(h);		
		return attributesQuery(s);
	}
	
	public Set<Object> getObjectsFromPatientId(String patientId){
		HashMap<String, Object> h = new HashMap<String, Object>();
		Set<HashMap> s = new HashSet<HashMap>();
		Value visitVal = e.makeStringValue(patientId);
		h.put("attribute", klassContainer.visitId);
		h.put("value", visitVal);
		s.add(h);		
		return attributesQuery(s);
	}
	
	public Set<Object> getObjectsFromDateRange(String patientId, Date low, Date high){
		HashMap<String, Object> h = new HashMap<String, Object>();
		Set<HashMap> s = new HashSet<HashMap>();
		ValueTemplate vDateRange = e.makeDateValueRangeTemplate(low, high);
		h.put("attribute", klassContainer.date);
		h.put("value", vDateRange);
		Value visitVal = e.makeStringValue(patientId);
		h.put("attribute", klassContainer.visitId);
		h.put("value", visitVal);
		s.add(h);
		return attributesQuery(s);
	}
}
