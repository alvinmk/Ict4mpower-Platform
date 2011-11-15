package storage.dight;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

import se.sics.dight.data.model.ex.DightRuntimeException;
import se.sics.dight.storage.engine.Engine;
import se.sics.dight.storage.store.events.OperationResponse;
import se.sics.dight.storage.store.events.OperationResponse.Status;
import se.sics.dight.storage.store.query.QueryResult;

public class OperationResult {
	private final Status status;
	private final QueryResult result;

	public OperationResult(OperationResponse event) {
		status = event.getStatus();
		result = event.getQueryResults();
	}

	public OperationResult(Engine e, byte[] res) {
		ChannelBuffer buffer = ChannelBuffers.copiedBuffer(res);

		switch (buffer.readByte()) {
		case 0:
			status = Status.SUCCESS;
			break;
		case 1:
			status = Status.REFUSED;
			break;
		case 2:
			status = Status.FAIL;
			break;
		default:
			throw new DightRuntimeException("Unexpected tag") {
				private static final long serialVersionUID = 1L;
			};
		}
		if (buffer.readByte() == 0) {
			result = null;
		} else {
			result = QueryResult.deserialize(e, buffer);
		}
	}

	public QueryResult getQueryResults() {
		return result;
	}

	public boolean hasResults() {
		return result != null;
	}

	public Status getStatus() {
		return status;
	}

	byte[] serialize() {
		int size;

		if (result == null)
			size = 2;
		else
			size = (int) result.getSize() + 2;

		byte[] array = new byte[size];
		ChannelBuffer buffer = ChannelBuffers.wrappedBuffer(array);
		buffer.writerIndex(0);
		
		switch (status) {
		case SUCCESS:
			buffer.writeByte(0);
			break;
		case REFUSED:
			buffer.writeByte(1);
			break;
		case FAIL:
			buffer.writeByte(2);
			break;
		}

		if (result == null) {
			buffer.writeByte(0);
		} else {
			buffer.writeByte(1);
			result.serialize(buffer);
		}
		return array;
	}
}
