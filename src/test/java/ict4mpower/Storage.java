package ict4mpower;

import java.util.Set;

import junit.framework.TestCase;

import org.apache.wicket.util.tester.WicketTester;

import storage.DataEndPoint;

public class Storage extends TestCase{
	DataEndPoint data;
		
	@Override
	public void setUp()
	{
		data = DataEndPoint.getDataEndPoint();
	}
	
	public void testStoreAndRetrive(){
		MockPatient m = new MockPatient();
		long l = 1L;
		String result = data.SignEntry(m.pi, "111", l , "TestApp");
		//assertEquals("new patient", result); //Nothing added yet
		Object o = new Object();
		//result = data.SignEntry(o, "111", l , "TestApp");
		//assertEquals("new type", result); //Only difference is the object
		Object o2 = new Object();
		//result = data.SignEntry(o2, "111", l , "TestApp");
		//assertEquals("new object", result); //Only difference is the object
		
		String type = m.pi.getClass().getName();
		Set<Object> s = data.getEntriesFromVisitIdAndType("111","TestApp", l, type);
		assertTrue(s.contains(m.pi));
		data.save();
	}

}
