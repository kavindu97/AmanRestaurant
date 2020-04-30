package com.lazyboys.login_registration_customerorder_it18399066_aman.data;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.bumptech.glide.load.engine.Resource;
import com.lazyboys.login_registration_customerorder_it18399066_aman.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FoodDbHelper extends SQLiteOpenHelper {

    private static final String TAG = FoodDbHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "food.db";
    private static final int DATABASE_VERSION = 1;
    Context context;
    SQLiteDatabase db;
    ContentResolver mContentResolver;

    private Resources mResources;

    public FoodDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        mResources = context.getResources();
        mContentResolver = context.getContentResolver();

        db = this.getWritableDatabase();

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_FOOD_TABLE = " CREATE TABLE " + FoodContract.FoodEntry.TABLE_NAME + "(" +
                FoodContract.FoodEntry._ID + "INTEGER PRIMARY KEY AUTOINCREMENT," +
                FoodContract.FoodEntry.COLUMN_NAME + "TEXT UNIQUE NOT NULL," +
                FoodContract.FoodEntry.COLUMN_DESCRIPTION + "TEXT NOT NULL," +
                FoodContract.FoodEntry.COLUMN_IMAGE + "TEXT NOT NULL," +
                FoodContract.FoodEntry.COLUMN_PRICE + "REAL NOT NULL," +
                FoodContract.FoodEntry.COLUMN_USERRATING + "INTEGER NOT NULL" + ");";

        final String SQL_CREATE_CART_FOOD_TABLE = " CREATE TABLE " + FoodContract.FoodEntry.CART_TABLE + "(" +
                FoodContract.FoodEntry._CARTID + "INTEGER PRIMARY KEY AUTOINCREMENT," +
                FoodContract.FoodEntry.COLUMN_CART_NAME + "TEXT UNIQUE NOT NULL," +
                FoodContract.FoodEntry.COLUMN_CART_IMAGE + "TEXT NOT NULL," +
                FoodContract.FoodEntry.COLUMN_CART_QUANTITY + "INTEGER NOT NULL," +
                FoodContract.FoodEntry.COLUMN_CART_TOTAL_PRICE + "REAL NOT NULL" + ");";

        db.execSQL(SQL_CREATE_FOOD_TABLE);
        db.execSQL(SQL_CREATE_CART_FOOD_TABLE);
        Log.d(TAG, "Database Created Successfully");

        try{
            readFoodsFromResources(db);
        } catch(IOException e){
            e.printStackTrace();
        } catch (JSONException e){
            e.printStackTrace();
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + FoodContract.FoodEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + FoodContract.FoodEntry.CART_TABLE);
        onCreate(db);
    }

    private void readFoodsFromResources(SQLiteDatabase db) throws IOException, JSONException {

        StringBuilder builder = new StringBuilder();
        InputStream in = mResources.openRawResource(R.raw.foodlist);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        String line;
        while((line = reader.readLine()) != null){
            builder.append(line + "\n");
        }

        final String rawJson = builder.toString();

        final String BGS_FOODS = "foods";

        final String BGS_FOODNAME = "foodName";
        final String BGS_DESCRIPTION = "description";
        final String BGS_IMAGEURL = "imageURL";
        final String BGS_PRICE = "price";
        final String BGS_USERRATING = "userRating";

        try{
            JSONObject foodlistJson = new JSONObject(rawJson);
            JSONArray foodlistArray = foodlistJson.getJSONArray(BGS_FOODS);

            for (int i=0; i<foodlistArray.length(); i++) {

                String foodName;
                String description;
                String imageUrl;
                Double price;
                int userRating;

                JSONObject foodDetails = foodlistArray.getJSONObject(i);

                foodName = foodDetails.getString(BGS_FOODNAME);
                description = foodDetails.getString(BGS_DESCRIPTION);
                imageUrl = foodDetails.getString(BGS_IMAGEURL);
                price = foodDetails.getDouble(BGS_PRICE);
                userRating = foodDetails.getInt(BGS_USERRATING);

                ContentValues foodValues = new ContentValues();

                foodValues.put(FoodContract.FoodEntry.COLUMN_NAME, foodName);
                foodValues.put(FoodContract.FoodEntry.COLUMN_DESCRIPTION, description);
                foodValues.put(FoodContract.FoodEntry.COLUMN_IMAGE, imageUrl);
                foodValues.put(FoodContract.FoodEntry.COLUMN_PRICE, price);
                foodValues.put(FoodContract.FoodEntry.COLUMN_USERRATING, userRating);

                db.insert(FoodContract.FoodEntry.TABLE_NAME, null, foodValues);

                Log.d(TAG, "Inserted Successfully" + foodValues);
            }

            Log.d(TAG, "Inserted Successfully" + foodlistArray.length());

        }catch (JSONException e){
            Log.e(TAG, e.getMessage(), e);
            e.printStackTrace();

        }

    }
}