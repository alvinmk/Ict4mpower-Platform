package storage.dight;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import se.sics.dight.data.model.EntryId;
import se.sics.dight.data.model.Objekt;
import se.sics.dight.data.model.values.Value;

public class MeasurementRecord extends BaseRecord{

	private static MeasurementKlass klassContainer;

	public MeasurementRecord(){
		setKlass((KlassContainer) klassContainer.getKlass());
	}
	
	public String newEntry(String measurement, String unit, String value, String patientId){
		klassContainer = new MeasurementKlass(e);
		Value vMeasurement = e.makeStringValue(measurement);
		Value vUnit = e.makeStringValue(unit);
		Value vValue = e.makeStringValue(value);
		Value vPatientId = e.makeStringValue(patientId);
		Value vDate = e.makeDateValue(new Date());
		EntryId eid = null;
	
		Objekt entry = e.createObjekt(klassContainer.getKlass(), eid, null );
		entry.setValue(klassContainer.patientId, vPatientId);
		entry.setValue(klassContainer.date, vDate);
		entry.setValue(klassContainer.measurement, vMeasurement);
		entry.setValue(klassContainer.unit, vUnit);
		entry.setValue(klassContainer.value, vValue);
		entry.commit(klassContainer.getEntryAuthenticationAlgorithm());
		//Send to server
		return eid.getId().toString();
	}
	
	public Set<Object> getMeasurement(String patientId, String measurement){
		HashMap<String, Object> h = new HashMap<String, Object>();
		Set<HashMap> s = new HashSet<HashMap>();
		Value vMeasurement = e.makeStringValue(measurement);
		h.put("attribute", klassContainer.measurement);
		h.put("value", vMeasurement);
		s.add(h);
		
		Value vPatientId = e.makeStringValue(patientId);
		h.put("attribute", klassContainer.patientId);
		h.put("value", patientId);
		s.add(h);
		
		return attributesQuery(s);
		
	}
}
