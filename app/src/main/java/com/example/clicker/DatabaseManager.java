package com.example.clicker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.SQLException;

public class DatabaseManager {

    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public DatabaseManager(Context c) {
        context = c;
    }

    public DatabaseManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public boolean checkUsernameExist(String username){
        String query = "Select count(*)" +
                " FROM " + DatabaseHelper.TABLE_USER +
                " WHERE " + DatabaseHelper.USERNAME + " = '" + username + "'";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor mCount= db.rawQuery(query, null);
        mCount.moveToFirst();
        int count= mCount.getInt(0);
        mCount.close();
        if (count == 0) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkEmailExist(String email){
        String query = "Select count(*)" +
                       " FROM " + DatabaseHelper.TABLE_USER +
                       " WHERE " + DatabaseHelper.EMAIL + " = '" + email + "'";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor mCount= db.rawQuery(query, null);
        mCount.moveToFirst();
        int count= mCount.getInt(0);
        mCount.close();
        if (count == 0) {
            return false;
        } else {
            return true;
        }
    }

    public void addUser(User user) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.EMAIL, user.getEmail());
        values.put(DatabaseHelper.PASSWORD, user.getPassword());
        values.put(DatabaseHelper.CURR_COUNT, user.getCurrCount());
        values.put(DatabaseHelper.TOTAL_COUNT, user.getTotalCount());
        database.insert(DatabaseHelper.TABLE_USER, null, values);
    }

    public User findAccount(String email, String password) {
        String query = "Select "+ DatabaseHelper.USERNAME + ", " + DatabaseHelper.EMAIL + ", " + DatabaseHelper.CURR_COUNT + ", " + DatabaseHelper.TOTAL_COUNT +
                       " FROM " + DatabaseHelper.TABLE_USER +
                       " WHERE " + DatabaseHelper.EMAIL + " = " + "'" + email + "'" +
                       " AND " + DatabaseHelper.PASSWORD + " = '" + password + "'";

        User user = new User();

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        user.setUsername(cursor.getString(0));
        user.setEmail(cursor.getString(1));
        user.setCurrCount(cursor.getInt(2));
        user.setTotalCount(cursor.getInt(3));

        return user;
    }

    public void updateCount(Session session) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.CURR_COUNT, session.getCurrCount());
        contentValues.put(DatabaseHelper.TOTAL_COUNT, session.getTotalCount());
        database.update(DatabaseHelper.TABLE_USER, contentValues, DatabaseHelper.EMAIL + " = ?", new String[]{session.getEmail()});
    }


}
