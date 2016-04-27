package edu.cmu.supermandy.isight.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mandy on 4/11/16.
 */
public class DBHelper extends SQLiteOpenHelper {
    private final static String DB_NAME ="iSight.db";
    private final static int VERSION = 1;

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

        db.execSQL(sql.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DB_NAME);
        onCreate(db);
    }
}
