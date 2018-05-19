package com.example.heldersilva.pontosinteresse.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Helder Silva on 19/04/2018.
 */

public class CityContract {

    public static final String AUTHORITY = "com.example.heldersilva.pontosinteresse";


    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);


    public static final String PATH_CITYS = "citys";


    public static final class TaskEntry implements BaseColumns {


        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_CITYS).build();



        public static final String TABLE_NAME = "citys";


        public static final String COLUMN_DESCRIPTION = "description";

    }
}
