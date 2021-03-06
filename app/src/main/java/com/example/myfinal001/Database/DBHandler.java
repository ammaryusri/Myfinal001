package com.example.myfinal001.Database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Database.db";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Userprofile.Users.TABLE_NAME + " (" +
                    Userprofile.Users._ID + " INTEGER PRIMARY KEY," +
                    Userprofile.Users.COLUMN_1 + " TEXT,"+
                    Userprofile.Users.COLUMN_1 + " TEXT,"+
                    Userprofile.Users.COLUMN_1 + " TEXT,"+
                    Userprofile.Users.COLUMN_2 + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Userprofile.Users.TABLE_NAME;


    public long addinfo(String username,String dateofbirth, String password, String gender){
        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(Userprofile.Users.COLUMN_1, username);
        values.put(Userprofile.Users.COLUMN_2, dateofbirth);
        values.put(Userprofile.Users.COLUMN_3, password);
        values.put(Userprofile.Users.COLUMN_4, gender);

// Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(Userprofile.Users.TABLE_NAME, null, values);
        return newRowId;
    }

    public boolean updateinfo(String username,String dateofbirth, String password, String gender)
    {
        SQLiteDatabase db = getWritableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
        values.put(Userprofile.Users.COLUMN_2, dateofbirth);
        values.put(Userprofile.Users.COLUMN_3, password);
        values.put(Userprofile.Users.COLUMN_4, gender);

        // Which row to update, based on the title
        String selection = Userprofile.Users.COLUMN_1 + " LIKE ?";
        String[] selectionArgs = { username };

        int count = db.update(
                Userprofile.Users.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        if(count>=1)
        {
            return true;
        }
        else
            {
            return false;
        }

    }

    public void deleteinfo(String username){

        SQLiteDatabase db = getWritableDatabase();
        // Define 'where' part of query.
        String selection = Userprofile.Users.COLUMN_1 + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = { username };
        // Issue SQL statement.
        int deletedRows = db.delete(Userprofile.Users.TABLE_NAME, selection, selectionArgs);

    }

    public List readallinfo(){
        String username ="Avishka";
        SQLiteDatabase db = getReadableDatabase();

        // Define a projection that specifies which columns from the database
         // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                Userprofile.Users.COLUMN_1,
                Userprofile.Users.COLUMN_2,
                Userprofile.Users.COLUMN_3,
                Userprofile.Users.COLUMN_4

        };

        // Filter results WHERE "title" = 'My Title'
        String selection = Userprofile.Users.COLUMN_1 + " = ?";
        String[] selectionArgs = { username };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                Userprofile.Users.COLUMN_1 + " ASC";

        Cursor cursor = db.query(
                Userprofile.Users.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        List usernames = new ArrayList<>();
        while(cursor.moveToNext()) {
            long user = cursor.getLong(cursor.getColumnIndexOrThrow(Userprofile.Users._ID));
            usernames.add(user);
        }
        cursor.close();
        return usernames;
    }

    public List readallinfo(String username){

        SQLiteDatabase db = getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                Userprofile.Users.COLUMN_1,
                Userprofile.Users.COLUMN_2,
                Userprofile.Users.COLUMN_3,
                Userprofile.Users.COLUMN_4

        };

        // Filter results WHERE "title" = 'My Title'
        String selection = Userprofile.Users.COLUMN_1 + " LIKE ?";
        String[] selectionArgs = { username };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                Userprofile.Users.COLUMN_1 + " ASC";

        Cursor cursor = db.query(
                Userprofile.Users.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        List userinfo = new ArrayList<>();
        while(cursor.moveToNext()) {
            String user = cursor.getString(cursor.getColumnIndexOrThrow(Userprofile.Users.COLUMN_1));
            String dob = cursor.getString(cursor.getColumnIndexOrThrow(Userprofile.Users.COLUMN_2));
            String pass = cursor.getString(cursor.getColumnIndexOrThrow(Userprofile.Users.COLUMN_3));
            String gen = cursor.getString(cursor.getColumnIndexOrThrow(Userprofile.Users.COLUMN_4));
            userinfo.add(user);//0
            userinfo.add(dob);//1
            userinfo.add(pass);//2
            userinfo.add(gen);//3
        }
        cursor.close();
        return userinfo;
    }
}
