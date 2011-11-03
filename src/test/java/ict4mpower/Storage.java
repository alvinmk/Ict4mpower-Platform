package ict4mpower;

import java.util.Set;

import junit.framework.TestCase;

import org.apache.wicket.util.tester.WicketTester;
import storage.MedicalRecordSocket;

public class Storage extends TestCase{
	MedicalRecordSocket MRSocket = new MedicalRecordSocket();
		
	@Override
	public void setUp()
	{
		
	}
	
	public void testStoreAndRetrive(){
		MockPatient m1 = new MockPatient();
		MockPatient m2 = new MockPatient();
		MockPatient m3 = new MockPatient();
		long l = 1L;
		MRSocket.SignEntry(m1, "111", 1L, "app");
		MRSocket.SignEntry(m2, "111", 2L, "app");
		MRSocket.SignEntry(m3, "112", 1L, "app");
		
		Set s = MRSocket.getEntriesFromPatientId("111");
		assertTrue(s.contains(m1));
		assertTrue(s.contains(m2));
		s = MRSocket.getVisitEntry(1L, "111");
		assertTrue(s.contains(m1));
		assertFalse(s.contains(m2));
		s = MRSocket.getEntriesFromPatientId("112");
		assertTrue(s.contains(m3));
		assertTrue(s.size()==1);		
	}

}
