package com.example.heldersilva.pontosinteresse;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.heldersilva.pontosinteresse.data.CityContract;

/**
 * Created by Helder Silva on 20/04/2018.
 */

public class CustomCursorAdapter extends RecyclerView.Adapter<CustomCursorAdapter.CityViewHolder> {


    private Cursor mCursor;
    private Context mContext;



    public CustomCursorAdapter(Context mContext) {
        this.mContext = mContext;
    }



    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.visited_layout, parent, false);

        return new CityViewHolder(view);
    }



    @Override
    public void onBindViewHolder(CityViewHolder holder, int position) {


        int idIndex = mCursor.getColumnIndex(CityContract.TaskEntry._ID);
        int descriptionIndex = mCursor.getColumnIndex(CityContract.TaskEntry.COLUMN_DESCRIPTION);


        mCursor.moveToPosition(position);


        final int id = mCursor.getInt(idIndex);
        String description = mCursor.getString(descriptionIndex);



        holder.itemView.setTag(id);
        holder.taskDescriptionView.setText(description);

    }


    @Override
    public int getItemCount() {
        if (mCursor == null) {
            return 0;
        }
        return mCursor.getCount();
    }



    public Cursor swapCursor(Cursor c) {

        if (mCursor == c) {
            return null;
        }
        Cursor temp = mCursor;
        this.mCursor = c;


        if (c != null) {
            this.notifyDataSetChanged();
        }
        return temp;
    }



    class CityViewHolder extends RecyclerView.ViewHolder {


        TextView taskDescriptionView;



        public CityViewHolder(View itemView) {
            super(itemView);

            taskDescriptionView = (TextView) itemView.findViewById(R.id.taskDescription);

        }
    }
}
