package edu.cmu.supermandy.isight.ws.remote;

import java.io.IOException;
import java.util.HashMap;

import edu.cmu.supermandy.isight.model.User;

/**
 * Created by Zuowei on 2016/5/2.
 */
public class UserRequest {
    public String getUserInfo(int id) {
        HashMap<String, String> r= new HashMap<>();
        r.put("id", String.valueOf(id));
        String result = "error";
        try {
            result = RequestHandler.getRequest(1, r);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String insertUser(User user) {
        HashMap<String, String> r= new HashMap<>();
        r.put("userName", user.getUsername());
        r.put("email", user.getEmail());
        r.put("password", user.getPassword());
        r.put("age", String.valueOf(user.getAge()));
        r.put("phoneNum", user.getPhoneNum());
        String result = "error";
        try {
            result = RequestHandler.getRequest(2, r);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String updateUser(User user) {
        HashMap<String, String> r= new HashMap<>();
        r.put("userName", user.getUsername());
        r.put("email", user.getEmail());
        r.put("password", user.getPassword());
        r.put("age", String.valueOf(user.getAge()));
        r.put("phoneNum", user.getPhoneNum());
        r.put("id", String.valueOf(user.getId()));
        String result = "error";
        try {
            result = RequestHandler.getRequest(3, r);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
