package edu.cmu.supermandy.isight.ws.remote;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
/**
 * Created by Zuowei on 2016/5/2.
 */
public class RequestHandler {

    private static final String SERVER = "http://ec2-54-235-239-186.compute-1.amazonaws.com:8080";
    public static final int GET_USER_INFO = 1;
    public static final int  CREATE_NEW_USER = 2;
    public static final int UPDATE_USER_INFO = 3;
    public static final int GET_ALL_RECORDS_BY_USER_ID = 4;
    public static final int CREATE_NEW_RECORD = 5;
    public static final int DELETE_RECORD = 6;

    public static String getRequest(int qid, HashMap<String, String> requestData) throws IOException{

        StringBuilder requestBuilder = new StringBuilder();
        requestBuilder.append(SERVER).append("/ISightServer/ISightQuery?queryId=").append(String.valueOf(qid));

        for( String key : requestData.keySet()){
            requestBuilder.append("&").append(key).append("=").append(requestData.get(key));
        }

        String request = requestBuilder.toString();

        log(request);

        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(request.replace(" ", "%20"));
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            if(connection.getResponseCode() != 200) {

                throw new IOException(connection.getResponseMessage());
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String s;
            while((s = reader.readLine()) != null) {

                result.append(s);
            }
            reader.close();
            connection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        log(result.toString());

        return result.toString();
    }


    public static void log (String s ){
        System.out.println(s);
    }
}


