package storage.dight;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.mortbay.log.Log;

import se.sics.dight.data.model.AttributeQuery;
import se.sics.dight.data.model.Entry;
import se.sics.dight.data.model.Objekt;
import se.sics.dight.data.model.attributes.Attribute;
import se.sics.dight.data.model.ex.PayloadNotRetrieved;
import se.sics.dight.data.model.templates.ValueTemplate;
import se.sics.dight.data.security.Credential;
import se.sics.dight.storage.engine.Engine;
import se.sics.dight.storage.store.query.AttributeQueryResult;
import se.sics.dight.storage.store.query.QueryResult;

public abstract class BaseRecord {
	protected KlassContainer klassContainer;
	protected Engine e = Engine.makeEngine(17, 16 * 1024 * 1024);
	int version;
	protected List<Credential> cred = new ArrayList<Credential>();
	private static final Logger log = Logger.getLogger(BaseRecord.class);
		
	public BaseRecord(){
	}
	
	public void setKlassContainer(KlassContainer kc){
		this.klassContainer=kc;
	}			
	
	/*
	 *  Makes an query from attributes and values
	 */
	protected Set<Object> attributesQueryToObject(Set<HashMap<String, Object>> a){
		Set<Entry> x = attributesQuery(a);
		log.info("Query returned " + x.size() +" Objects");
		return getObjectsFromEntrySet(x);
	}
	
	protected Set<Objekt> attributesQueryToObjekt(Set<HashMap<String, Object>> a){
		Set<Entry> x = attributesQuery(a);
		log.info("Query returned " + x.size() +" Objects");
		return getObjektsFromEntrySet(x);
	}
	
	private Set<Entry> attributesQuery(Set<HashMap<String, Object>> a){
		AttributeQuery aq = e.createAttributeQuery(klassContainer.getKlass(), false);
		for(HashMap<String, Object> map : a){
			aq.setValue((Attribute) map.get("attribute"), (ValueTemplate)map.get("value"));
		}		
		aq.commit(klassContainer.getEntryAuthenticationAlgorithm());
		//Send query to server
		
		QueryResult qr = null;
		try {
			qr = DightSocket.CreateOperationResult(aq, cred, e);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		AttributeQueryResult indexQResult = (AttributeQueryResult) qr;
		Set<Entry> x;
		if(indexQResult!=null){
			x = indexQResult.getResult();
		}
		else{
			log.info("Attribute query returned no results, creating empty result set");
			x = new HashSet<Entry>();
		}
		return x;
	}
	
	private Set<Objekt> getObjektsFromEntrySet(Set<Entry> s){
		Set<Objekt> ret = new HashSet<Objekt>();
		for(Entry currentEntry: s){
			ret.add(getObjektFromEntry(currentEntry));
		}
		return ret;
	}
	
	private Objekt getObjektFromEntry(Entry entry){
		return (Objekt) entry;
	}
	
	/*
	 * Returns a set of objects with data in them
	 */
	private Set<Object> getObjectsFromEntrySet(Set<Entry> s){
		Set<Object> ret = new HashSet<Object>();
		for(Entry currentEntry: s){
			ret.add(getObjectFromEntry(currentEntry));
		}
		return ret;
	}
		
	
	/*
	 * Takes an entry and extracts the objekt and then get the payload
	 * Deserialize it and return the object.
	 */
	private Object getObjectFromEntry(Entry entry){
		Objekt o = (Objekt) entry;
		Object ret = null;
		try {
			ret = deserialize(o.getPayload());
		} catch (PayloadNotRetrieved e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	/* 
	 * Serialize the object
	 */
	protected static byte[] serialize(Object obj) throws IOException {
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
    	ObjectOutputStream os = new ObjectOutputStream(out);
		os.writeObject(obj);
	    return out.toByteArray();
	}
	
	/*
	 * Deserialize the object
	 */	
	protected static Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
	    ByteArrayInputStream in = new ByteArrayInputStream(data);
	    ObjectInputStream is = null;
	    Object o = null;
		is = new ObjectInputStream(in);
		o = is.readObject();
		is.close();
		in.close();
		return o;
	}
	
}


