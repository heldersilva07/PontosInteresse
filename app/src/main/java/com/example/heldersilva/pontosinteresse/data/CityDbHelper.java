package com.example.heldersilva.pontosinteresse.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.heldersilva.pontosinteresse.data.CityContract.TaskEntry;

/**
 * Created by Helder Silva on 19/04/2018.
 */

public class CityDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "citysDb.db";

    private static final int VERSION = 1;

    public CityDbHelper(Context context)  {
        super(context, DATABASE_NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_TABLE = "CREATE TABLE "  + TaskEntry.TABLE_NAME + " (" +
                TaskEntry._ID                + " INTEGER PRIMARY KEY, " +
                TaskEntry.COLUMN_DESCRIPTION + " TEXT NOT NULL);";

        db.execSQL(CREATE_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TaskEntry.TABLE_NAME);
        onCreate(db);
    }
}
