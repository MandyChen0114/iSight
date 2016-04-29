package edu.cmu.supermandy.isight.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mandy on 4/11/16.
 */
public class DBHelper extends SQLiteOpenHelper {
    private final static String DB_NAME = "iSight.db";
    private final static String User_Table = "UserTable";
    private final static String Quiz_Table = "QuizTable";
    private final static int VERSION = 2;

    /**
     * constructors
     */
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBHelper(Context context) {
        this(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create user info table
        StringBuilder sql = new StringBuilder();
        sql.append("create table if not exists UserTable(")
                .append("Id integer primary key autoincrement,")
                .append("Username text,")
                .append("Email text,")
                .append("Password text,")
                .append("Age int,")
                .append("PhoneNum text);");
        StringBuilder sql_quiz = new StringBuilder();
        // Create quiz info table
        sql_quiz.append("create table if not exists QuizTable(")
                .append("Id integer primary key autoincrement,")
                .append("Question text,")
                .append("Answer text);");
        System.out.println("---------------------->success1");
        db.execSQL(sql.toString());
        System.out.println("---------------------->success2");
        db.execSQL(sql_quiz.toString());
        System.out.println("---------------------->success3");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + User_Table);
        System.out.println("---------------------->success4");
        db.execSQL("DROP TABLE IF EXISTS " + Quiz_Table);
        System.out.println("---------------------->success5");
        onCreate(db);
        System.out.println("---------------------->success6");
    }
}
