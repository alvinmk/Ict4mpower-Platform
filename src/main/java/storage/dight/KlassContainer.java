package storage.dight;

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
}
