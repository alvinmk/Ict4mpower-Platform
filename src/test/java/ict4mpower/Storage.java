package ict4mpower;

import junit.framework.TestCase;

import org.apache.wicket.util.tester.WicketTester;

import storage.DataEndPoint;

public class Storage extends TestCase{
	DataEndPoint d;
		
	@Override
	public void setUp()
	{
		d = DataEndPoint.getDataEndPoint();
	}
	
	public void TestStoreAndRetrive(){
		//MockPatient m = new MockPatient();
		
		
	}

}
