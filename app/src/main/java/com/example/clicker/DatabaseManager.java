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
        values.put(DatabaseHelper.USERNAME, user.getUsername());
        values.put(DatabaseHelper.EMAIL, user.getEmail());
        values.put(DatabaseHelper.PASSWORD, user.getPassword());
        values.put(DatabaseHelper.CURR_COUNT, user.getCurrCount());
        values.put(DatabaseHelper.TOTAL_COUNT, user.getTotalCount());
        values.put(DatabaseHelper.MULTIPLIER, user.getMultiplier());
        database.insert(DatabaseHelper.TABLE_USER, null, values);
    }

    public User findAccount(String username, String password) {
        String query = "Select "+ DatabaseHelper.USERNAME + ", " + DatabaseHelper.EMAIL + ", " + DatabaseHelper.CURR_COUNT + ", " + DatabaseHelper.TOTAL_COUNT + ", " + DatabaseHelper.MULTIPLIER +
                       " FROM " + DatabaseHelper.TABLE_USER +
                       " WHERE " + DatabaseHelper.USERNAME + " = " + "'" + username + "'" +
                       " AND " + DatabaseHelper.PASSWORD + " = '" + password + "'";

        User user = new User();

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.getCount() == 0) {
            return user;
        }

        cursor.moveToFirst();

        user.setUsername(cursor.getString(0));
        user.setEmail(cursor.getString(1));
        user.setCurrCount(cursor.getInt(2));
        user.setTotalCount(cursor.getInt(3));
        user.setMultiplier(cursor.getInt(4));

        return user;
    }

    public void updateCount(Session session, int totalCount) {
        int multiplier = session.getMultiplier();
        System.out.println(multiplier);

        session.setCurrCount(session.getCurrCount() + (1 * multiplier));
        System.out.println(session.getCurrCount());
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.CURR_COUNT, session.getCurrCount());
        contentValues.put(DatabaseHelper.TOTAL_COUNT, totalCount + (1 * multiplier));
        database.update(DatabaseHelper.TABLE_USER, contentValues, DatabaseHelper.USERNAME + " = ?", new String[]{session.getUsername()});
    }

    public void buyItem(Session session, int multiplier, int price, int item) {
        session.setCurrCount(session.getCurrCount() - price);
        session.setMultiplier(multiplier);
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.CURR_COUNT, session.getCurrCount());
        contentValues.put(DatabaseHelper.MULTIPLIER, multiplier);

        if(item == 1) {
            contentValues.put(DatabaseHelper.ITEM_1, 1);
        }else if(item == 2) {
            contentValues.put(DatabaseHelper.ITEM_2, 1);
        }else if(item == 3) {
            contentValues.put(DatabaseHelper.ITEM_3, 1);
        }else if(item == 4) {
            contentValues.put(DatabaseHelper.ITEM_4, 1);
        }else {
            contentValues.put(DatabaseHelper.ITEM_5, 1);
        }

        database.update(DatabaseHelper.TABLE_USER, contentValues, DatabaseHelper.USERNAME + " = ?", new String[]{session.getUsername()});
    }

    public int[] getItems(Session session){
        String query = "Select "+ DatabaseHelper.ITEM_1 + ", " + DatabaseHelper.ITEM_2 + ", " + DatabaseHelper.ITEM_3 + ", " + DatabaseHelper.ITEM_4 + ", " + DatabaseHelper.ITEM_5 +
                " FROM " + DatabaseHelper.TABLE_USER +
                " WHERE " + DatabaseHelper.USERNAME + " = " + "'" + session.getUsername() + "'";

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        cursor.moveToFirst();

        int[] items = new int[5];
        items[0] = cursor.getInt(0);
        items[1] = cursor.getInt(1);
        items[2] = cursor.getInt(2);
        items[3] = cursor.getInt(3);
        items[4] = cursor.getInt(4);

        return items;
    }

    public int getTotalCount(String username){
        String query = "Select "+ DatabaseHelper.TOTAL_COUNT +
                " FROM " + DatabaseHelper.TABLE_USER +
                " WHERE " + DatabaseHelper.USERNAME + " = " + "'" + username + "'";

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.getCount() == 0) {
            return 0;
        }

        cursor.moveToFirst();

        return cursor.getInt(0);
    }
}
