package com.example.teest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mydatabase_rev.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "Reviews";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_IMAGE = "image";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_COMMENT = "comment";
    private static final String COLUMN_CATEGORY = "category";
    private static final String COLUMN_RATING = "rating";
    private static final String COLUMN_AUTHOR = "author";


    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the database schema
        String query= String.format("CREATE TABLE %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT,%s TEXT,%s TEXT,%s TEXT,%s TEXT,%s INTEGER,%s TEXT)", TABLE_NAME, COLUMN_ID, COLUMN_NAME, COLUMN_IMAGE, COLUMN_ADDRESS, COLUMN_COMMENT, COLUMN_CATEGORY, COLUMN_RATING,COLUMN_AUTHOR);
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Upgrade the database schema if necessary

    }
    public void delete(SQLiteDatabase db) {
        // Upgrade the database schema if necessary
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    // Perform Database Operations
    public void insertData(ArrayList<String> data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, data.get(0));
        values.put(COLUMN_IMAGE, data.get(1));
        values.put(COLUMN_ADDRESS, data.get(2));
        values.put(COLUMN_COMMENT, data.get(3));
        values.put(COLUMN_CATEGORY, data.get(4));
        values.put(COLUMN_RATING, data.get(5));
        values.put(COLUMN_AUTHOR, data.get(6));
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<Review> getAllData() {
        List<Review> dataList = new ArrayList<>();

        SQLiteDatabase database = null;
        Cursor cursor = null;

        Review rev;
        try {
            // Create or open the database
            database = this.getReadableDatabase();

            // Define the columns you want to retrieve
            String[] projection = { COLUMN_ID, COLUMN_NAME, COLUMN_IMAGE, COLUMN_ADDRESS, COLUMN_COMMENT, COLUMN_CATEGORY, COLUMN_RATING, COLUMN_AUTHOR };

            // Execute the query
            cursor = database.query(TABLE_NAME, projection, null, null, null, null, null);

            // Iterate over the cursor to retrieve the data
            while (cursor.moveToNext()) {
                String column1 = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME));
                String column2 = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ID));
                String column3 = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ADDRESS));
                String column4 = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COMMENT));
                String column5 = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CATEGORY));
                String column6 = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RATING));
                String column7 = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_AUTHOR));
                String column8 = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_IMAGE));
                // Create a MyData object and add it to the list
                dataList.add(new Review( Integer.parseInt(column2),column1,column3,column4,column5,column7, Float.parseFloat(column6),column8));

            }
        } catch (Exception e) {
            // Handle any exceptions
            e.printStackTrace();
        } finally {
            // Close the cursor and the database
            if (cursor != null) {
                cursor.close();
            }
            if (database != null) {
                database.close();
            }
        }

        return dataList;
    }
    public int deleteData(int id) {
        SQLiteDatabase database = null;
        // Perform the deletion
        try {
            // Create or open the database
            database = this.getWritableDatabase();
            String condition = COLUMN_ID + " = " + Integer.toString(id);
            int rowsAffected = database.delete(TABLE_NAME, condition, null);
            return rowsAffected;
        } catch (Exception e) {
            // Handle any exceptions
            e.printStackTrace();
        } finally {
            // Close the cursor and the database
            if (database != null) {
                database.close();
            }
        }
        return 0;
    }
    public Review getDataById(int id) {
        List<Review> dataList = new ArrayList<>();

        SQLiteDatabase database = null;
        Cursor cursor = null;

        Review rev=new Review();
        try {
            // Create or open the database
            database = this.getReadableDatabase();

            // Define the columns you want to retrieve
            String[] projection = { COLUMN_ID, COLUMN_NAME, COLUMN_IMAGE, COLUMN_ADDRESS, COLUMN_COMMENT, COLUMN_CATEGORY, COLUMN_RATING, COLUMN_AUTHOR };

            String selection = COLUMN_ID + " = ?";
            String[] selectionArgs = {String.valueOf(id)};

            // Execute the query
            cursor = database.query(TABLE_NAME, projection, selection, selectionArgs, null, null, null);

            // Iterate over the cursor to retrieve the data
            while (cursor.moveToNext()) {
                String column1 = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME));
                String column2 = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ID));
                String column3 = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ADDRESS));
                String column4 = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COMMENT));
                String column5 = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CATEGORY));
                String column6 = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RATING));
                String column7 = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_AUTHOR));
                String column8 = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_IMAGE));
                // Create a MyData object and add it to the list
                rev=new Review( Integer.parseInt(column2),column1,column3,column4,column5,column7, Float.parseFloat(column6),column8);

            }
        } catch (Exception e) {
            // Handle any exceptions
            e.printStackTrace();
        } finally {
            // Close the cursor and the database
            if (cursor != null) {
                cursor.close();
            }
            if (database != null) {
                database.close();
            }
        }

        return rev;
    }
}