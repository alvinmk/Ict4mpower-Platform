package storage;

import java.util.Set;

import storage.dight.MeasurementRecord;

public class MeasurementRecordKlass {

	public Set<Object> getMesurmentByType(String type, String patienId){
		MeasurementRecordKlass m = new MeasurementRecordKlass();
		return m.getMesurmentByType(type, patienId);
	}
	
	
}
