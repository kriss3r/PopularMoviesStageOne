package com.example.user.popularmoviesstageone;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.popularmoviesstageone.Utilities.DetailedActivity;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 05.04.2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>  {
    public int mCurrentId;
    private static Movie mMoviesList;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private int mCurrentId;
        public TextView mItemTittle,mItemPlotSynopsis;
        public ImageView mItemImage, mUserRating, mDetailedItem;

        public ViewHolder(View v) {
            super(v);
            mItemImage = (ImageView) v.findViewById(R.id.movie_item);
            itemView.setOnClickListener(this);
        }

// used to move to correct Activity thorough implicit intent.
        @Override
        public void onClick(View view) {
            Log.i("overwiew",mMoviesList.getResults().get(getAdapterPosition()).getOverview());
            mCurrentId = getAdapterPosition();
            Context context = itemView.getContext();
            Intent showPhotoIntent = new Intent(context, DetailedActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("value",mMoviesList);
            showPhotoIntent.putExtras(bundle);
            showPhotoIntent.putExtra("id",mCurrentId);
            context.startActivity(showPhotoIntent);
        }
    }

    public RecyclerAdapter(Movie mMoviesList) {
        RecyclerAdapter.mMoviesList = mMoviesList;
    }
    public static final String URL_BASE = "http://image.tmdb.org/t/p/";
    public static final String SIZE = "w154";

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movies_list,parent,false);

        // set correct height programmatically
        int height = parent.getMeasuredHeight()/4;
        inflatedView.setMinimumHeight(height);
        return new ViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        mCurrentId = position;
        String httpRequestAddress = URL_BASE+SIZE+mMoviesList.getResults().get(position).getPoster_path();
        Picasso.with(holder.itemView.getContext()).load(httpRequestAddress).into(holder.mItemImage);
}

    @Override
    public int getItemCount() {
        return mMoviesList.getResults().size();
    }


}
