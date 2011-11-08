package storage;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;

//import storage.dight.MedicalRecord;

public class ApplicationSocketTemp {
	
	//A temporary in memory object storage {Application}->{type}->Set<Objects>
	
	private static HashMap<String, HashMap<String, Set<Object>>> temp = new HashMap<String, HashMap<String,  Set<Object>>>();
	private static ApplicationSocketTemp d;
	
	public static ApplicationSocketTemp getApplicationSocketTemp(){
		if(d!=null){
			return d;
		}
		else{
			d = new ApplicationSocketTemp();
			return d;
		}
	}
	
	
	private ApplicationSocketTemp(){
		try {
		    FileInputStream fin = new FileInputStream("Application.dat");
		    ObjectInputStream ois = new ObjectInputStream(fin);
		    temp = (HashMap<String, HashMap<String, Set<Object>>>) ois.readObject();
		    ois.close();
		    }
		   catch (Exception e) { e.printStackTrace(); }
	}
	
	public String storeData(String application, String type, Object data){
		//ApplicationRecord a = new ApplicationRecord();
		//return a.newEntry(application, type, data);
		if(temp.containsKey(application)){
			if(temp.get(application).containsKey(type)){
				temp.get(application).get(type).add(data);
			}
			else{
				Set<Object> s = new HashSet<Object>();
				s.add(data);
				temp.get(application).put(type, s);
			}			
		}
		else{
			Set<Object> s = new HashSet<Object>();
			s.add(data);
			HashMap<String, Set<Object>> h = new HashMap<String, Set<Object>>();
			h.put(type, s);
			temp.put(application, h);
		}
		return "success";
	}
	
	public Set<Object> getData(String application, String type){
		return temp.get(application).get(type);
		//ApplicationRecord a = new ApplicationRecord();
		//return a.getApplicationData(application, type);
		
	}
	
	public void save(){		
		 try {
		      FileOutputStream fout = new FileOutputStream("MedicalRecords.dat");
		      ObjectOutputStream oos = new ObjectOutputStream(fout);
		      oos.writeObject(temp);
		      oos.close();
		      }
		 catch (Exception e) { e.printStackTrace(); }
		 
	}

}
