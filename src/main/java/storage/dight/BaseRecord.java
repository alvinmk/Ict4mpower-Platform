package storage.dight;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import se.sics.dight.data.model.AttributeQuery;
import se.sics.dight.data.model.Entry;
import se.sics.dight.data.model.EntryId;
import se.sics.dight.data.model.Klass;
import se.sics.dight.data.model.Objekt;
import se.sics.dight.data.model.attributes.Attribute;
import se.sics.dight.data.model.ex.PayloadNotRetrieved;
import se.sics.dight.data.model.templates.ValueTemplate;
import se.sics.dight.data.model.values.Value;
import se.sics.dight.data.security.Credential;
import se.sics.dight.data.security.EntryAuthenticationAlgorithm;
import se.sics.dight.security.DummyEntryAuthenticationAlgorithm;
import se.sics.dight.storage.engine.Engine;
import se.sics.dight.storage.store.query.AttributeQueryResult;
import se.sics.dight.storage.store.query.QueryResult;

public abstract class BaseRecord {
	protected KlassContainer klassContainer;
	protected Klass record;
	protected Engine e = Engine.makeEngine(17, 16 * 1024 * 1024);
	int version;
	private List<Credential> credential = new ArrayList<Credential>();
		
	public BaseRecord(){
	}
	
	public void setKlass(KlassContainer kc){
		record = kc.getKlass();
	}			
		
	//Test code to map out some thoughs
	public Set<Object> testQuery(){
		HashMap<String, Object> m = new HashMap<String, Object>();
		Attribute a = null;
		Value v = null;
		m.put("attribute", a);
		m.put("value", v);
		Set<HashMap> s = null;
		s.add(m);
		return attributesQuery(s);
	}
	
	/*
	 *  Makes an query from attributes and values
	 */
	protected Set<Object> attributesQuery(Set<HashMap> a){
		AttributeQuery aq = null;
		for(HashMap<String, Object> map : a){
			aq = e.createAttributeQuery(record, true);
			aq.setValue((Attribute) map.get("attribute"),(ValueTemplate)map.get("value"));
		}
		aq.commit(klassContainer.getEntryAuthenticationAlgorithm());
		//Send query to server
		QueryResult qr = null;
		AttributeQueryResult indexQResult = (AttributeQueryResult) qr;
		Set<Entry> x = indexQResult.getResult();
		return getObjectsFromEntrySet(x);
	}
	
	/*
	 * Returns a set of objects with data in them
	 */
	private Set<Object> getObjectsFromEntrySet(Set<Entry> s){
		Set<Object> ret = null;
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


