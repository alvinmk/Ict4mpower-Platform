package ict4mpower;

import java.util.ArrayList;

public class MockUser {
	public String userId;
	public String userName;
	public ArrayList<String> userRoles;
	
	public MockUser(){
		userId="1234";
		userName="Alvin";
		userRoles = new ArrayList<String>();
		userRoles.add("doctor");
	}
}
