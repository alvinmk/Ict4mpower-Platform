package storage.dight;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import se.sics.dight.data.model.Entry;
import se.sics.dight.data.model.EntryId;
import se.sics.dight.data.model.Klass;
import se.sics.dight.data.model.KlassQuery;
import se.sics.dight.data.model.attributes.Attribute;
import se.sics.dight.data.security.Credential;
import se.sics.dight.data.security.EntryAuthenticationAlgorithm;
import se.sics.dight.security.DummyEntryAuthenticationAlgorithm;
import se.sics.dight.storage.engine.Engine;
import se.sics.dight.storage.store.query.KlassQueryResult;
import se.sics.dight.storage.store.query.QueryResult;

public class KlassContainer {
	protected Klass klass;
	Engine e;
	String klassName;
	
	EntryAuthenticationAlgorithm EAA = new DummyEntryAuthenticationAlgorithm();
	
	public KlassContainer(Engine e){
		this.e = e;
	}

	public Klass getKlass(){
		return klass;
	}
		
	public EntryAuthenticationAlgorithm getEntryAuthenticationAlgorithm(){
		return EAA;
	}
	
	public EntryId generateUniqeEntryId(){
		byte id[] = new byte[20];
		UUID u = UUID.randomUUID();
		Date d = new Date();
		ByteBuffer bb = ByteBuffer.wrap(id);
		bb.order(ByteOrder.LITTLE_ENDIAN);
		bb.putLong(u.getMostSignificantBits());
		bb.putLong(u.getLeastSignificantBits());

		//Get four more bits into the byte
		Calendar c = Calendar.getInstance();
		byte temp[] = new byte[8];
		ByteBuffer bbTemp = ByteBuffer.wrap(temp);
		bbTemp.putLong(c.getTimeInMillis());
		//Slice the buffer in half
		bbTemp.position(4);
		bbTemp = bbTemp.slice();
		bb.put(bbTemp);
		return e.makeEntryId(id);	
	}
	
	public Klass queryKlass(String klass){
		//Does the class exsist
		KlassQuery query;
		query = e.createKlassQuery(klass, KlassQuery.LATEST_VERSION);
		query.commit(EAA);
		List<Credential> creds = null;
		QueryResult result = null;
		try {
			result = DightSocket.CreateOperationResult(query, creds, e);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if(result != null){
			KlassQueryResult klassQResult = (KlassQueryResult) result;
			Set<Entry> x = klassQResult.getResult();
			Entry entry  = klassQResult.getResult().iterator().next();
			Klass k = (Klass) entry;
			return k;
		}
		return null;
		
		
		//if not create it
	}
}
