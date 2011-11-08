package storage.dight;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import se.sics.dight.data.model.Entry;
import se.sics.dight.data.security.Credential;
import se.sics.dight.storage.engine.Engine;
import se.sics.dight.storage.store.events.OperationResponse;
import se.sics.dight.storage.store.query.QueryResult;



public class DightSocket {
	
	
	
	public static QueryResult CreateOperationResult(Entry entry, List<Credential> creds, Engine e) throws IOException {
		ByteArrayOutputStream credBytes = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(credBytes);
		oos.writeObject(creds);
		oos.close();
		byte[] entryBytes = e.serialize(entry);
		//Start DIGHT service and send data
		OperationResponse r;
		//dightStorageService = new DightStorageService();
		
		return null;
	}
}
