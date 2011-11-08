package storage;


import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
		ObjectInputStream ois = null;
		try {
			File file = new File("Application.dat");
			if(file.exists()) {
			    FileInputStream fin = new FileInputStream(file);
			    ois = new ObjectInputStream(fin);
			    Object o = ois.readObject();
			    if(o instanceof HashMap) {
			    	temp = (HashMap<String, HashMap<String, Set<Object>>>) o;
			    }
			    else {
			    	System.err.println("Not hashmap "+o);
			    }
			    System.out.println("temp "+temp);
			}
		} catch(EOFException eof) {
			// There's nothing there!
			eof.printStackTrace();
		} catch (Exception e) { e.printStackTrace(); }
		finally {
			if(ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
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

		printMap();
		return "success";
	}
	
	public Set<Object> getData(String application, String type){
		printMap();
		
		return temp.get(application).get(type);
		//ApplicationRecord a = new ApplicationRecord();
		//return a.getApplicationData(application, type);
	}
	
	public void save(){
		printMap();
		ObjectOutputStream oos = null;
		 try {
		      FileOutputStream fout = new FileOutputStream("Application.dat");
		      oos = new ObjectOutputStream(fout);
		      oos.writeObject(temp);
		 }
		 catch (Exception e) { e.printStackTrace(); }
		 finally {
			 if(oos != null) {
				 try {
					oos.flush();
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			 }
		 }
	}
	
	private void printMap(){
		for(String key : temp.keySet()){
			
			for(String Ikey : temp.get(key).keySet()){
					System.err.print(key);
					System.err.print("->{"+Ikey+"}->{\n");
					Set<Object> s = temp.get(key).get(Ikey);
					for(Object o : s){
						System.err.println("           "+o.getClass().getName());
					}
					System.err.println("}");
			}
		}
	}
}
