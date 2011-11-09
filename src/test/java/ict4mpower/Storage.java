package ict4mpower;

import java.io.Serializable;
import java.util.Set;

import junit.framework.TestCase;

import org.apache.wicket.util.tester.WicketTester;

import storage.MeasurementRecordSocket;
import storage.MedicalRecordSocket;

public class Storage extends TestCase{
	
		
	
	public void testMedicalRecords(){
		MedicalRecordSocket MRSocket = new MedicalRecordSocket();
		MockPatient m1 = new MockPatient();
		MockPatient m2 = new MockPatient();
		MockPatient m3 = new MockPatient();
		MRSocket.SignEntry(m1.pi, "111", 1L, "app");
		MRSocket.SignEntry(m2.pi, "111", 2L, "app");
		MRSocket.SignEntry(m3.pi, "112", 1L, "app");
		
		Set<Object> s = MRSocket.getEntriesFromPatientId("111");
		assertTrue(s.contains(m1.pi));
		assertTrue(s.contains(m2.pi));
		s = MRSocket.getVisitEntry(1L, "111");
		assertTrue(s.contains(m1.pi));
		assertFalse(s.contains(m2.pi));
		s = MRSocket.getEntriesFromPatientId("112");
		assertTrue(s.contains(m3.pi));
		assertTrue(s.size()==1);		
	}
	
	public void testMeasurmentRecords(){
		MeasurementRecordSocket mSocket = new MeasurementRecordSocket();
		MockPatient m1 = new MockPatient();
		MockPatient m2 = new MockPatient();
		MockPatient m3 = new MockPatient();
		mSocket.SignEntry("test", "m", 10.0, "111");
		mSocket.SignEntry("test", "m", 11.5, "111");
		mSocket.SignEntry("Water", "m", 12.0, "111");
		mSocket.SignEntry("Water", "l", 100.0, "112");
		
		mSocket.getMesurmentByType("test", "111");
		mSocket.getMesurmentByType("Water", "111");
		mSocket.getMesurmentByType("Water", "112");
		
		
		
	}

}
