package edu.cmu.supermandy.isight.ws.remote;

import java.io.IOException;
import java.util.HashMap;

import edu.cmu.supermandy.isight.model.Record;
/**
 * Created by Zuowei on 2016/5/2.
 */
public class RecordRequest {
    public String getRecordbyUser(int userId) {
        HashMap<String, String> r= new HashMap<>();
        r.put("userId", String.valueOf(userId));
        String result = "error";
        try {
            result = RequestHandler.getRequest(4, r);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String insertRecord(Record record) {
        HashMap<String, String> r= new HashMap<>();
        r.put("userId", String.valueOf(record.getUserId()));
        r.put("testId", String.valueOf(record.getTestId()));
        r.put("timestamp", record.getTimestamp());
        r.put("result", record.getResult());
        String result = "error";
        try {
            result = RequestHandler.getRequest(5, r);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String deleteRecordbyUser(int userId) {
        HashMap<String, String> r= new HashMap<>();
        r.put("userId", String.valueOf(userId));
        String result = "error";
        try {
            result = RequestHandler.getRequest(6, r);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
