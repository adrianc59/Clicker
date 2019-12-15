package com.example.clicker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    //Logcat tag
    static final String LOG = "DatabaseHelper";

    //Db version
    static final int DATABASE_VERSION = 1;
    static final int DATABASE_VERSION2 = 2;
    static final int DATABASE_VERSION3 = 3;
    static final int DATABASE_VERSION_4 = 4;
    static final int DATABASE_VERSION_5 = 5;
    static final int DATABASE_VERSION_6 = 6;
    static final int DATABASE_VERSION_7 = 7;

    //Db name
    public static final String DATABASE_NAME = "RepClicker";

    //Table names
    public static final String TABLE_USER = "user";

    //Common column names
    public static final String USER_ID = "userId";
    public static final String USERNAME = "username";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String CURR_COUNT = "currCount";
    public static final String TOTAL_COUNT = "totalCount";
    public static final String MULTIPLIER = "multiplier";
    public static final String ITEM_1 = "item1";
    public static final String ITEM_2 = "item2";
    public static final String ITEM_3 = "item3";
    public static final String ITEM_4 = "item4";
    public static final String ITEM_5 = "item5";

    //Table create statements
    private static final String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_USER + " (" +
            USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            USERNAME + " TEXT," +
            EMAIL + " TEXT," +
            PASSWORD + " TEXT," +
            String.valueOf(CURR_COUNT) + " INTEGER," +
            String.valueOf(TOTAL_COUNT) + " INTEGER," +
            String.valueOf(MULTIPLIER) + " INTEGER," +
            String.valueOf(ITEM_1) + " INTEGER," +
            String.valueOf(ITEM_2) + " INTEGER," +
            String.valueOf(ITEM_3) + " INTEGER," +
            String.valueOf(ITEM_4) + " INTEGER," +
            String.valueOf(ITEM_5) + " INTEGER)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION_7);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }
}
