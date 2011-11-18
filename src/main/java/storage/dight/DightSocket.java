package storage.dight;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import org.apache.log4j.Logger;
import se.sics.dight.data.model.Entry;
import se.sics.dight.data.security.Credential;
import se.sics.dight.storage.engine.Engine;
import se.sics.dight.storage.store.query.QueryResult;
import se.sics.dight.storage.webservice.DightStorageService;
import se.sics.dight.storage.webservice.DightStorageServiceEndpoint;

public class DightSocket {
	
	 private static final Logger log = Logger.getLogger(DightSocket.class);
	
	public static QueryResult CreateOperationResult(Entry entry, List<Credential> creds, Engine e) throws IOException {
		ByteArrayOutputStream credBytes = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(credBytes);
		oos.writeObject(creds);
		oos.close();
		byte[] entryBytes = e.serialize(entry);	
		
		//Start DIGHT service and send data
		DightStorageService d = new DightStorageService();
		DightStorageServiceEndpoint endpoint = d.getDightStorageServiceEndpointPort();
		byte[] result = endpoint.performOperation(entryBytes, credBytes.toByteArray());
		
		OperationResult operationResult = new OperationResult(e, result);
		log.info("*Result of operation " +operationResult.getStatus().toString());
		return operationResult.getQueryResults();
	}
}
