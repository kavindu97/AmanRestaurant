package com.example.mad_kavindu.activities;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DataBaseManager extends SQLiteOpenHelper {


    private static final String DATABASE_NAME ="PaymentDatabase";
    private static final int DATABASE_VERSION =1;
    private static final String TABLE_NAME ="payment";
    private static final String COLUMN_ID ="id";
    private static final String COLUMN_cardholder ="cardholder";
    private static final String COLUMN_cardno ="cardno";
    private static final String COLUMN_cardtype ="cardtype";
    private static final String COLUMN_phoneno ="phoneno";
    private static final String COLUMN_cardexpdate ="cardexpdate";
    private static final String COLUMN_cardccv="cardccv";






    public DataBaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "( \n" +
                "    "+COLUMN_ID+"  INTEGER NOT NULL CONSTRAINT payment_pk PRIMARY KEY AUTOINCREMENT,\n" +
                "   "+COLUMN_cardholder+" varchar(200) NOT NULL,\n" +
                "    "+COLUMN_cardno+" varchar(20) NOT NULL,\n" +
                "    "+COLUMN_cardtype+" varchar(200) NOT NULL,\n" +
                "    "+COLUMN_phoneno+" INTEGER NOT NULL,\n" +
                "    "+COLUMN_cardexpdate+" INTEGER NOT NULL,\n" +
                "    "+COLUMN_cardccv+" INTEGER NOT NULL\n" +
                ");";
        
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
