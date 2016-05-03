package adaptor;

import java.util.ArrayList;

import com.google.gson.Gson;

import db.DBAdaptor;
import model.Record;

/**
 * @author Zuowei
 *
 */
public class RecordAdaptorImp implements RecordAdaptor {
	@Override
	public String getAllRecordsByUserId(int userId) {
		ArrayList<Record> itags = DBAdaptor.getAllRecordsByUserId(userId);
		String result = new Gson().toJson(itags);
		return result;
	}

	@Override
	public String createNewRecord(Record record) {
		boolean result = DBAdaptor.createNewRecord(record);
		return result + "";
	}

	@Override
	public String deleteRecord(int userId) {
		boolean result = DBAdaptor.deleteRecord(userId);
		return result + "";
	}
}
