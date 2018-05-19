package com.example.heldersilva.pontosinteresse;

import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.MenuItem;

import com.example.heldersilva.pontosinteresse.data.CityContract;

public class PointsvisitedActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor> {

private static  final  String TAG = PointsvisitedActivity.class.getSimpleName();
    private static final int CITY_LOADER_ID = 0;


    private CustomCursorAdapter mAdapter;
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pointsvisited);


        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewTasks);


        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        mAdapter = new CustomCursorAdapter(this);
        mRecyclerView.setAdapter(mAdapter);


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }


            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {



                int id = (int) viewHolder.itemView.getTag();


                String stringId = Integer.toString(id);
                Uri uri = CityContract.TaskEntry.CONTENT_URI;
                uri = uri.buildUpon().appendPath(stringId).build();


                getContentResolver().delete(uri, null, null);


                getSupportLoaderManager().restartLoader(CITY_LOADER_ID, null, PointsvisitedActivity.this);

            }
        }).attachToRecyclerView(mRecyclerView);

        getSupportLoaderManager().initLoader(CITY_LOADER_ID, null, this);

        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();


        getSupportLoaderManager().restartLoader(CITY_LOADER_ID, null, this);
    }



    @Override
    public Loader<Cursor> onCreateLoader(int id, final Bundle loaderArgs) {

        return new AsyncTaskLoader<Cursor>(this) {


            Cursor mCityData = null;


            @Override
            protected void onStartLoading() {
                if (mCityData != null) {

                    deliverResult(mCityData);
                } else {

                    forceLoad();
                }
            }


            @Override
            public Cursor loadInBackground() {


                try {
                    return getContentResolver().query(CityContract.TaskEntry.CONTENT_URI,
                            null,
                            null,
                            null,
                            CityContract.TaskEntry._ID);

                } catch (Exception e) {
                    Log.e(TAG, "Failed to asynchronously load data.");
                    e.printStackTrace();
                    return null;
                }
            }


            public void deliverResult(Cursor data) {
                mCityData = data;
                super.deliverResult(data);
            }
        };

    }



    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        // Update the data that the adapter uses to create ViewHolders
        mAdapter.swapCursor(data);
    }



    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }


}
