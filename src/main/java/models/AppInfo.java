package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class AppInfo implements Serializable{
	String data;
	String otherData;
	
	public AppInfo(){
		this.data = "test";
		this.otherData = "other test";
	}
}
 