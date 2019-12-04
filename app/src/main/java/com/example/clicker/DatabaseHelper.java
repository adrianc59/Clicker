package com.example.clicker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    //Logcat tag
    static final String LOG = "DatabaseHelper";

    //Db version
    static final int DATABASE_VERSION = 1;

    //Db name
    public static final String DATABASE_NAME = "RepClicker";

    //Table names
    public static final String TABLE_USER = "user";

    //Common column names
    public static final String USER_ID = "userId";
    public static final String USERNAME = "username";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String REP_COUNT = "repCount";
    public static final String TAP_COUNT = "tapCount";

    //Table create statements
    private static final String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_USER + " (" +
            USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            USERNAME + " TEXT," +
            EMAIL + " TEXT," +
            PASSWORD + " TEXT," +
            String.valueOf(REP_COUNT) + " INTEGER," +
            String.valueOf(TAP_COUNT) + " INTEGER)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating required tables
        db.execSQL(CREATE_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

        // create new tables
        onCreate(db);
    }
}
