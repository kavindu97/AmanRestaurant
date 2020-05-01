package com.example.mad_kavindu.data;

import android.database.Cursor;

public class Food {

    public int id;

    public String name;
    public String description;
    public String imageURL;
    public Double price;
    public int userRating;

    public Food(Cursor cursor){
        this.name = cursor.getString(cursor.getColumnIndex(FoodContract.FoodEntry.COLUMN_NAME));
        this.description = cursor.getString(cursor.getColumnIndex(FoodContract.FoodEntry.COLUMN_DESCRIPTION));
        this.imageURL = cursor.getString(cursor.getColumnIndex(FoodContract.FoodEntry.COLUMN_IMAGE));
        this.price = cursor.getDouble(cursor.getColumnIndex(FoodContract.FoodEntry.COLUMN_PRICE));
        this.userRating = cursor.getInt(cursor.getColumnIndex(FoodContract.FoodEntry.COLUMN_USERRATING));
    }

}
