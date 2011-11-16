package ict4mpower;

import java.io.Serializable;
import java.util.Set;

import junit.framework.TestCase;

import models.AppInfo;
import models.PatientInfo;
import models.Prescription;

import org.apache.wicket.util.tester.WicketTester;

import storage.ApplicationSocket;
import storage.MeasurementRecordSocket;
import storage.MedicalRecordSocket;
import storage.dight.MeasurementRecord.Measurement;

public class Storage extends TestCase{
	
		
	
	public void testMedicalRecords(){
		MedicalRecordSocket MRSocket = new MedicalRecordSocket();
		PatientInfo p1 = new PatientInfo("Test1", "12", "None");
		PatientInfo p2 = new PatientInfo("Test1", "12", "Allerigic");
		Prescription p = new Prescription();
		PatientInfo p3 = new PatientInfo("Test2", "13", "Allergic");
		
		MRSocket.SignEntry(p1, "12", 1L, "app");
		MRSocket.SignEntry(p, "12", 1L, "app");
		MRSocket.SignEntry(p2, "12", 2L, "app");
		MRSocket.SignEntry(p3, "15", 1L, "app");
				
		Set<Object> s = MRSocket.getEntriesForPatientId("12", "PatientInfo", "app");
		
		for(Object o : s){
			PatientInfo pi = (PatientInfo) o;
			assertEquals(o.getClass().getSimpleName(), p1.getClass().getSimpleName());
			assertEquals(pi.getName(), p1.getName());
			assertTrue(pi.getVisit() == 1l || pi.getVisit() == 2l);
		}		
		
		s = MRSocket.getVisitEntries(1l, "12", "Prescription", "app");
		for(Object o : s){
			Prescription pi = (Prescription) o;
			assertEquals(o.getClass().getSimpleName(), p.getClass().getSimpleName());	
		}			
	}
	
	public void testMeasurmentRecords(){
		MeasurementRecordSocket mSocket = new MeasurementRecordSocket();
		mSocket.SignEntry("test", "m", 10.0, "111");
		mSocket.SignEntry("test", "m", 11.5, "111");
		mSocket.SignEntry("Water", "m", 12.0, "111");
		mSocket.SignEntry("Water", "l", 100.0, "112");
		Set<Measurement> s;
		/*
		s = mSocket.getMesurmentByType("test", "111");
		assertTrue(s.size() == 2);
		Measurement m  = s.iterator().next();
		assertEquals("111", m.getPatientId());
		assertEquals(10.0, m.getValue());
		assertEquals("m", m.getUnit());
		
		m  = s.iterator().next();
		assertEquals("111", m.getPatientId());
		assertEquals(11.5, m.getValue());
		assertEquals("m", m.getUnit());
		
		s = mSocket.getMesurmentByType("Water", "111");
		assertTrue(s.size() == 1);
		m  = s.iterator().next();
		assertEquals("111", m.getPatientId());
		assertEquals(12.0, m.getValue());
		assertEquals("l", m.getUnit());
		s = mSocket.getMesurmentByType("Water", "112");
		assertTrue(s.size() == 1);
		m  = s.iterator().next();
		assertEquals("112", m.getPatientId());
		assertEquals(100.0, m.getValue());
		assertEquals("l", m.getUnit());*/
	}
	
	public void testApplicationRecord(){
		ApplicationSocket aSocket = new ApplicationSocket();
		AppInfo a1 = new AppInfo();
		AppInfo a2 = new AppInfo();
		AppInfo a3 = new AppInfo();
		/*aSocket.storeData("test", "general", a1);
		aSocket.storeData("test", "general", a2);
		aSocket.storeData("test", "other", a3);
		aSocket.storeData("test", "other", a1);*/
	}

}
