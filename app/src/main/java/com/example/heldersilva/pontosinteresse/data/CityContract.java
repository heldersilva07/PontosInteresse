package com.example.heldersilva.pontosinteresse.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Helder Silva on 19/04/2018.
 */

public class CityContract {

    public static final String AUTHORITY = "com.example.heldersilva.pontosinteresse";

    // The base content URI = "content://" + <authority>
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    // Define the possible paths for accessing data in this contract
    // This is the path for the "tasks" directory
    public static final String PATH_CITYS = "citys";

    /* TaskEntry is an inner class that defines the contents of the task table */
    public static final class TaskEntry implements BaseColumns {

        // TaskEntry content URI = base content URI + path
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_CITYS).build();


        // Task table and column names
        public static final String TABLE_NAME = "citys";

        // Since TaskEntry implements the interface "BaseColumns", it has an automatically produced
        // "_ID" column in addition to the two below
        public static final String COLUMN_DESCRIPTION = "description";

    }
}
