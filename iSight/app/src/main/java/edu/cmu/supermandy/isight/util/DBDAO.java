package edu.cmu.supermandy.isight.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import edu.cmu.supermandy.isight.model.Quiz;
import edu.cmu.supermandy.isight.model.User;

/**
 * Created by Mandy on 4/11/16.
 */
public class DBDAO {
    private SQLiteDatabase db;

    public DBDAO(Context context) {
        db = new DBHelper(context).getWritableDatabase();
    }

    public void insertUser(User user) {
        /**
         * insert info of users into database
         */
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put("Username", "["+user.getUsername()+"]");
            values.put("Email", "["+user.getEmail()+"]");
            values.put("Password", user.getPassword());
            values.put("Age", user.getAge());
            values.put("PhoneNum", user.getPhoneNum());
            db.insert("UserTable", null, values);
            db.setTransactionSuccessful();

            Log.d("Insert a user.", "Succeed");

        } finally {
            db.endTransaction();
        }
    }

    public void insertQuiz(Quiz quiz) {
        /**
         * insert info of users into database
         */
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put("Question",  quiz.getQuestion());
            values.put("Answer", quiz.getAnswer() );
            db.insert("QuizTable", null, values);
            db.setTransactionSuccessful();

            Log.d("Insert a quiz.", "Succeed");

        } finally {
            db.endTransaction();
        }
    }

    public int count(String TableName){
        String sql = "SELECT COUNT(*) FROM "+TableName+";";
        SQLiteStatement statement = db.compileStatement(sql);
        int count = (int)statement.simpleQueryForLong();
        return count;
    }

    public int checkDataExist(String TableName, String dbfield, String fieldValue) {
        String Query = "Select * from " + TableName + " where " + dbfield + " = '" + fieldValue+"' LIMIT 1;";
        Cursor cursor = db.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return -1;
        }
        cursor.moveToFirst();
        int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("Id")));
        cursor.close();
        return id;
    }

    public String[] getRowData(String TableName, String dbfield, String fieldValue, String[] columnindex){
        String[] rowData=new String[columnindex.length];
        String Query = "Select * from " + TableName + " where " + dbfield + " = '" + fieldValue+"';";
        Cursor cursor = db.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return null;
        }
        cursor.moveToFirst();
        for(int i=0;i<columnindex.length;i++) {
            rowData[i] = cursor.getString(cursor.getColumnIndex(columnindex[i]));
        }
        cursor.close();
        return rowData;
    }
}
