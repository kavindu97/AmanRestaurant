package com.lazyboys.login_registration_customerorder_it18399066_aman.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class FoodProvider extends ContentProvider {

    public static final String LOG_TAG = FoodProvider.class.getSimpleName();

    private static final int FOODS = 100;

    private static final int FOOD_ID = 101;

    private static final int CART = 102;

    private static final int CART_ID = 103;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static{

        sUriMatcher.addURI(FoodContract.CONTENT_AUTHORITY, FoodContract.PATH_FOOD, FOODS);
        sUriMatcher.addURI(FoodContract.CONTENT_AUTHORITY, FoodContract.PATH_CART, CART);


        sUriMatcher.addURI(FoodContract.CONTENT_AUTHORITY, FoodContract.PATH_FOOD + "/#", FOOD_ID);
        sUriMatcher.addURI(FoodContract.CONTENT_AUTHORITY, FoodContract.PATH_CART + "/#", CART_ID);

    }

    private FoodDbHelper foodDbHelper;


    @Override
    public boolean onCreate() {
        foodDbHelper = new FoodDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        SQLiteDatabase database = foodDbHelper.getReadableDatabase();

        Cursor cursor;

        int match = sUriMatcher.match(uri);
        switch(match){
            case FOODS:

                cursor = database.query(FoodContract.FoodEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;

            case CART:

                cursor = database.query(FoodContract.FoodEntry.CART_TABLE, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;

            case CART_ID:
                selection = FoodContract.FoodEntry._CARTID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};

                cursor = database.query(FoodContract.FoodEntry.CART_TABLE, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;

            case FOOD_ID:
                selection = FoodContract.FoodEntry._ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};

                cursor = database.query(FoodContract.FoodEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI" + uri);

        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        final int match = sUriMatcher.match(uri);
        switch (match){
            case FOODS:
                return insertCart(uri, contentValues);
            default:
                throw new IllegalArgumentException("Insertion is not supported for" + uri);
        }
    }

    private Uri insertCart(Uri uri, ContentValues values){

        SQLiteDatabase database = foodDbHelper.getReadableDatabase();

        Long id = database.insert(FoodContract.FoodEntry.CART_TABLE, null, values);

        if (id == -1){
            Log.e(LOG_TAG, "Failed to insert row for" + uri);
            return null;
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {

        final SQLiteDatabase db = foodDbHelper.getWritableDatabase();

        int match = sUriMatcher.match(uri);
        int cartDeleted;

        switch (match){
            case CART_ID:

                String id = uri.getPathSegments().get(1);

                cartDeleted = db.delete(FoodContract.FoodEntry.CART_TABLE, "_id=?", new String[]{id});
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri:" + uri);
        }

        if (cartDeleted !=0){
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return cartDeleted;

    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
