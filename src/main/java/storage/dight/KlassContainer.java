package storage.dight;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import se.sics.dight.data.model.EntryId;
import se.sics.dight.data.model.Klass;
import se.sics.dight.data.model.attributes.Attribute;
import se.sics.dight.data.security.EntryAuthenticationAlgorithm;
import se.sics.dight.security.DummyEntryAuthenticationAlgorithm;
import se.sics.dight.storage.engine.Engine;

public class KlassContainer {
	protected Klass klass;
	Engine e;
	
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
}
