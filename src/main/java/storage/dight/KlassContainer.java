package storage.dight;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import se.sics.dight.data.model.Entry;
import se.sics.dight.data.model.EntryId;
import se.sics.dight.data.model.Klass;
import se.sics.dight.data.model.KlassQuery;
import se.sics.dight.data.security.Credential;
import se.sics.dight.data.security.EntryAuthenticationAlgorithm;
import se.sics.dight.security.DummyEntryAuthenticationAlgorithm;
import se.sics.dight.storage.engine.Engine;
import se.sics.dight.storage.store.query.KlassQueryResult;
import se.sics.dight.storage.store.query.QueryResult;

public abstract class KlassContainer {
	Engine e;
	String klassName;
	protected List<Credential> klassCreds = new ArrayList<Credential>();
	protected long version;
	
	EntryAuthenticationAlgorithm EAA = new DummyEntryAuthenticationAlgorithm();
	
	public KlassContainer(Engine e){
		this.e = e;
	}

	public abstract Klass getKlass();
		
	public EntryAuthenticationAlgorithm getEntryAuthenticationAlgorithm(){
		return EAA;
	}
	
	protected abstract void createAttributes();
	protected abstract void createAndStoreKlass();
	
	protected EntryId generateUniqeEntryId(){
		byte id[] = new byte[20];
		UUID u = UUID.randomUUID();
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
	
	protected Klass queryKlass(String klass){
		//Does the Klass exsist?
		KlassQuery query = e.createKlassQuery(klass, KlassQuery.LATEST_VERSION);
		query.commit(EAA);
		QueryResult result = null;
		try {
			result = DightSocket.CreateOperationResult(query, klassCreds, e);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if(result != null){
			KlassQueryResult klassQResult = (KlassQueryResult) result;
			Entry entry  = klassQResult.getResult().iterator().next();
			Klass k = (Klass) entry;
			return k;
		}
		return null;
	}
}
