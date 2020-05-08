package com.example.mad_kavindu.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    public static final String ROW_ID="row_id";
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Database_Res";
    public static final String TABLE_PROMOTION = "Promotions";
    public static final String TABLE_Customer = "Customer";
    public static final String TABLE_Seller = "Seller";
    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_PRICE = "Price";
    public static final String COLUMN_DETAILS = "Details";
    public static final String COLUMN_PHOTO = "Photo";
    private static final String COLUMN_ID = "_id";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createQuery = "create table " + TABLE_PROMOTION + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME + " TEXT, " + COLUMN_PRICE + " TEXT, " + COLUMN_DETAILS + " TEXT, " + COLUMN_PHOTO + " TEXT);";
        String sellerQuery = "create table " + TABLE_Seller + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME + " TEXT, " + COLUMN_PRICE + " TEXT, " + COLUMN_DETAILS + " TEXT, " + COLUMN_PHOTO + " TEXT);";
        String customerrQuery = "create table " + TABLE_Customer + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME + " TEXT, " + COLUMN_PRICE + " TEXT, " + COLUMN_DETAILS + " TEXT, " + COLUMN_PHOTO + " TEXT);";

        sqLiteDatabase.execSQL(createQuery);
        sqLiteDatabase.execSQL(sellerQuery);
        sqLiteDatabase.execSQL(customerrQuery);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    /*get a cursor containing all the rows of this table*/
    public Cursor getAllRecords() {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("select * from " + TABLE_PROMOTION, null);
    }

    public boolean updateRecord(String rowId, String name, String price, String details, String photoUrl) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues updatedValues = new ContentValues();
        updatedValues.put(COLUMN_NAME, name);
        updatedValues.put(COLUMN_PRICE, price);
        updatedValues.put(COLUMN_DETAILS, details);
        updatedValues.put(COLUMN_PHOTO, photoUrl);
        int rowsAffected = db.update(TABLE_PROMOTION, updatedValues, COLUMN_ID + "=?", new String[]{rowId});
        return rowsAffected != 0;
    }

    /*this will return false if the deletion was not successful, true otherwise*/
    public boolean deleteRecord(String rowId) {
        SQLiteDatabase db = getWritableDatabase();
        /*delete method return 0 if deletion was not successful*/
        int result = db.delete(TABLE_PROMOTION, COLUMN_ID + "=?", new String[]{rowId});
        if (result == 0) {
            return false;
        } else {
            return true;
        }
    }

    public boolean insertData(String name, String price, String details, String photoUrl) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues newRow = new ContentValues();
        newRow.put(COLUMN_NAME, name);
        newRow.put(COLUMN_PRICE, price);
        newRow.put(COLUMN_DETAILS, details);
        newRow.put(COLUMN_PHOTO, photoUrl);
        /*the insert method return -1 if the record insertion is not successful,
         * so , if the insertion was not successful, we return false, otherwise we return true*/
        long result = db.insert(TABLE_PROMOTION, null, newRow);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    public Cursor getOneRecord(String rowIDtoEdit) {
        SQLiteDatabase db=getReadableDatabase();
        return db.query(TABLE_PROMOTION,new String[]{" * "}, COLUMN_ID+"=? ", new String[]{rowIDtoEdit}, null,null,null);
    }
    public boolean insertSellerData(String name, String price, String details, String photoUrl) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues newRow = new ContentValues();
        newRow.put(COLUMN_NAME, name);
        newRow.put(COLUMN_PRICE, price);
        newRow.put(COLUMN_DETAILS, details);
        newRow.put(COLUMN_PHOTO, photoUrl);
        /*the insert method return -1 if the record insertion is not successful,
         * so , if the insertion was not successful, we return false, otherwise we return true*/
        long result = db.insert(TABLE_Seller, null, newRow);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    public Cursor getSellerAllRecords() {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("select * from " + TABLE_Seller, null);
    }
    public boolean deletesellerrecord(String rowId) {
        SQLiteDatabase db = getWritableDatabase();
        /*delete method return 0 if deletion was not successful*/
        int result = db.delete(TABLE_Seller, COLUMN_ID + "=?", new String[]{rowId});
        if (result == 0) {
            return false;
        } else {
            return true;
        }
    }
    public boolean updatesellerRecord(String rowId, String name, String price, String details, String photoUrl) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues updatedValues = new ContentValues();
        updatedValues.put(COLUMN_NAME, name);
        updatedValues.put(COLUMN_PRICE, price);
        updatedValues.put(COLUMN_DETAILS, details);
        updatedValues.put(COLUMN_PHOTO, photoUrl);
        int rowsAffected = db.update(TABLE_Seller, updatedValues, COLUMN_ID + "=?", new String[]{rowId});
        return rowsAffected != 0;
    }
    public Cursor SearchData(String product_id){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String search_query = "SELECT * FROM " + TABLE_Seller + " WHERE _id LIKE '"+product_id +"'";
        Cursor cursor = sqLiteDatabase.rawQuery(search_query,null);
        Log.d("@@@Cursor","cccc"+cursor);
        return cursor;
    }


    public boolean insertCustomerData(String name, String price, String details, String photoUrl) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues newRow = new ContentValues();
        newRow.put(COLUMN_NAME, name);
        newRow.put(COLUMN_PRICE, price);
        newRow.put(COLUMN_DETAILS, details);
        newRow.put(COLUMN_PHOTO, photoUrl);
        /*the insert method return -1 if the record insertion is not successful,
         * so , if the insertion was not successful, we return false, otherwise we return true*/
        long result = db.insert(TABLE_Customer, null, newRow);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    public Cursor getCustomerAllRecords() {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("select * from " + TABLE_Customer, null);
    }
    public boolean deleteCustomerrecord(String rowId) {
        SQLiteDatabase db = getWritableDatabase();
        /*delete method return 0 if deletion was not successful*/
        int result = db.delete(TABLE_Customer, COLUMN_ID + "=?", new String[]{rowId});
        if (result == 0) {
            return false;
        } else {
            return true;
        }
    }
    public boolean updatecustomerRecord(String rowId, String name, String price, String details, String photoUrl) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues updatedValues = new ContentValues();
        updatedValues.put(COLUMN_NAME, name);
        updatedValues.put(COLUMN_PRICE, price);
        updatedValues.put(COLUMN_DETAILS, details);
        updatedValues.put(COLUMN_PHOTO, photoUrl);
        int rowsAffected = db.update(TABLE_Customer, updatedValues, COLUMN_ID + "=?", new String[]{rowId});
        return rowsAffected != 0;
    }


    /*CRUD HUH ?????????*/
}
