package com.example.heldersilva.pontosinteresse.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static com.example.heldersilva.pontosinteresse.data.CityContract.TaskEntry.TABLE_NAME;

/**
 * Created by Helder Silva on 19/04/2018.
 */

public class CityContentProvider extends ContentProvider {

    public static final int CITYS = 100;
    public static final int CITY_WITH_ID = 101;


    private static final UriMatcher sUriMatcher = buildUriMatcher();

    public static UriMatcher buildUriMatcher() {


        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);


        uriMatcher.addURI(CityContract.AUTHORITY, CityContract.PATH_CITYS, CITYS);
        uriMatcher.addURI(CityContract.AUTHORITY, CityContract.PATH_CITYS + "/#", CITY_WITH_ID);

        return uriMatcher;
    }

    private CityDbHelper mCityDbHelper;

    @Override
    public boolean onCreate() {
        Context context = getContext();
        mCityDbHelper = new CityDbHelper(context);
        return true;

    }


    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        final SQLiteDatabase db = mCityDbHelper.getReadableDatabase();


        int match = sUriMatcher.match(uri);
        Cursor retCursor;


        switch (match) {

            case CITYS:
                retCursor =  db.query(TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }


        retCursor.setNotificationUri(getContext().getContentResolver(), uri);


        return retCursor;
    }


    @Override
    public Uri insert(@NonNull Uri uri,  ContentValues values) {

        final SQLiteDatabase db = mCityDbHelper.getWritableDatabase();


        int match = sUriMatcher.match(uri);
        Uri returnUri;

        switch (match) {
            case CITYS:

                long id = db.insert(TABLE_NAME, null, values);
                if ( id > 0 ) {
                    returnUri = ContentUris.withAppendedId(CityContract.TaskEntry.CONTENT_URI, id);
                } else {
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                }
                break;

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }


        getContext().getContentResolver().notifyChange(uri, null);


        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri,  String s,  String[] strings) {

        final SQLiteDatabase db = mCityDbHelper.getWritableDatabase();

        int match = sUriMatcher.match(uri);

        int tasksDeleted;


        switch (match) {

            case CITY_WITH_ID:

                String id = uri.getPathSegments().get(1);

                tasksDeleted = db.delete(TABLE_NAME, "_id=?", new String[]{id});
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }


        if (tasksDeleted != 0) {

            getContext().getContentResolver().notifyChange(uri, null);
        }


        return tasksDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }
}
