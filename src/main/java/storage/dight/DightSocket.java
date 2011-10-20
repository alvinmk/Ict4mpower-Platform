package storage.dight;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import se.sics.dight.data.model.Entry;
import se.sics.dight.data.security.Credential;
import se.sics.dight.storage.engine.Engine;


public class DightSocket {
	
	public static void CreateOperationResult(Entry entry, List<Credential> creds, Engine e) throws IOException {
		ByteArrayOutputStream credBytes = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(credBytes);
		oos.writeObject(creds);
		oos.close();
		byte[] entryBytes = e.serialize(entry);
	}
}
