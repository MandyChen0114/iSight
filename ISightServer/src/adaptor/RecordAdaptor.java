package adaptor;

import model.Record;

/**
 * @author Zuowei
 *
 */
public interface RecordAdaptor {
	public String getAllRecordsByUserId(int userId);
	public String createNewRecord(Record tag);
	public String deleteRecord(int userId);
}
