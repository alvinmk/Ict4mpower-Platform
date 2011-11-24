package storage.dight;

import java.io.IOException;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.mortbay.log.Log;

import models.Measurement;

import se.sics.dight.data.model.EntryId;
import se.sics.dight.data.model.Objekt;
import se.sics.dight.data.model.attributes.Attribute;
import se.sics.dight.data.model.templates.ValueTemplate;
import se.sics.dight.data.model.values.DateValue;
import se.sics.dight.data.model.values.DoubleValue;
import se.sics.dight.data.model.values.StringValue;
import se.sics.dight.data.model.values.Value;
import se.sics.dight.storage.store.query.QueryResult;

public class MeasurementRecord extends BaseRecord{

	private static MeasurementKlass klassContainer;
	private static final Logger log = Logger.getLogger(MeasurementRecord.class);
	
	public MeasurementRecord(){
		klassContainer = new MeasurementKlass(e);
		setKlassContainer(klassContainer);
	}
	
	public String newEntry(String measurement, String unit, Double value, String patientId){
		Value vMeasurement = e.makeStringValue(measurement);
		Value vUnit = e.makeStringValue(unit);
		Value vValue = e.makeDoubleValue(value);
		Value vPatientId = e.makeStringValue(patientId);
		Value vDate = e.makeDateValue(new Date());
	
		EntryId eid = klassContainer.generateUniqeEntryId();
		byte b[] = new byte[1];
		b[0] = 1;
		
		Objekt entry = e.createObjekt(klassContainer.getKlass(), eid, b);
		entry.setValue(klassContainer.patientId, vPatientId);
		entry.setValue(klassContainer.date, vDate);
		entry.setValue(klassContainer.measurement, vMeasurement);
		entry.setValue(klassContainer.unit, vUnit);
		entry.setValue(klassContainer.value, vValue);
		entry.commit(klassContainer.getEntryAuthenticationAlgorithm());		
		try {
			QueryResult res = DightSocket.CreateOperationResult(entry, cred, e);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return eid.getId().toString();
	}
	
	public Set<Measurement> getMeasurement(String patientId, String measurement){
		HashMap<String, Object> h = new HashMap<String, Object>();
		Set<HashMap<String, Object>> s = new HashSet<HashMap<String, Object>>();
		ValueTemplate vMeasurement = e.makeStringValueTemplate(measurement);
		h.put("attribute", klassContainer.measurement);
		h.put("value", vMeasurement);
		s.add(h);		
		
		ValueTemplate vPatientId = e.makeStringValueTemplate(patientId);
		h = new HashMap<String, Object>();
		h.put("attribute", klassContainer.patientId);
		h.put("value", vPatientId);
		s.add(h);
		
		//Retrive result
		Set<Objekt> objekts = attributesQueryToObjekt(s);
		Set<Measurement> ret = new TreeSet<Measurement>(new MeasurementComparator());		
		for(Objekt o : objekts){
			Measurement m = new Measurement();
			m.setDate( ((DateValue) o.getValue(klassContainer.date)).getValue());
			m.setValue( ((DoubleValue)o.getValue(klassContainer.value)).getValue() );
			m.setUnit( ((StringValue)o.getValue(klassContainer.unit)).getValue());
			m.setMeasurement(measurement);
			m.setPatientId(patientId);
			ret.add(m);
		}
		return ret;
	}
	
	
	private class MeasurementComparator implements Comparator<Measurement>{
		public int compare(Measurement m1, Measurement m2){
			if(m1.getDate().before(m2.getDate())){
				return 1;
			}
			return 0;
		}
	}
}
