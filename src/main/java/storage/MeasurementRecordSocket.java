package storage;

import java.io.Serializable;
import java.util.Set;

import models.Measurement;

import storage.dight.MeasurementRecord;


public class MeasurementRecordSocket {

	public Set<Measurement> getMesurmentByType(String measurement, String patienId){
		MeasurementRecord m = new MeasurementRecord();
		return m.getMeasurement(patienId, measurement);
	}
	
	public String SignEntry(String measurement, String unit, Double value, String patientId){
		MeasurementRecord m = new MeasurementRecord();
		System.err.println("------------------------------------CREATING ENTRY FROM SOCKET---------------------------------------------");
		return m.newEntry(measurement, unit, value, patientId);
	}
}
