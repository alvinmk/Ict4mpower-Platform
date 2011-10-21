package storage;

import java.util.Set;

import storage.dight.MeasurementRecord;


public class MeasurementRecordSocket {

	public Set<Object> getMesurmentByType(String measurement, String patienId){
		MeasurementRecord m = new MeasurementRecord();
		return m.getMeasurement(patienId, measurement);
	}
	
	public String SignEntry(String measurement, String unit, String value, String patientId){
		MeasurementRecord m = new MeasurementRecord();
		return m.newEntry(measurement, unit, value, patientId);
	}
}
