package edu.cmu.supermandy.isight.model;

import java.util.Date;

/**
 * Created by Mandy on 4/11/16.
 */
public class Record {
    Integer id;
    Integer userId;
    Integer testId;
    Integer timestamp;
    String result;

    public Record(Integer userId, Integer testId, Integer timestamp, String result) {
        this.userId = userId;
        this.testId=testId;
        this.timestamp = timestamp;
        this.result = result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTestId() {
        return testId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
