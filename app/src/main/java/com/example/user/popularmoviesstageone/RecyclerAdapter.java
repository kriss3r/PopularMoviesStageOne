package com.example.user.popularmoviesstageone;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.popularmoviesstageone.utilities.DetailedActivity;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by User on 05.04.2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>  {
    public int mCurrentId;
    private static Movie mMoviesList;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private int mCurrentId;
        @BindView(R.id.movie_item)
        ImageView mItemImage;


        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
            itemView.setOnClickListener(this);
        }

// used to move to correct Activity thorough implicit intent.
        @Override
        public void onClick(View view) {
            mCurrentId = getAdapterPosition();
            Context context = itemView.getContext();
            Intent showPhotoIntent = new Intent(context, DetailedActivity.class);
            Movie.ResultsBean viewData = mMoviesList.getResults().get(mCurrentId);
            showPhotoIntent.putExtra("value", viewData);
            context.startActivity(showPhotoIntent);
        }
    }

    public RecyclerAdapter(Movie mMoviesList) {
        RecyclerAdapter.mMoviesList = mMoviesList;
    }
    public static final String URL_BASE = "http://image.tmdb.org/t/p/";
    public static final String SIZE = "w154";

    public void swapDataSet(Movie newData) {
        mMoviesList = newData;
        notifyDataSetChanged();
    }


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
        if (mMoviesList == null || mMoviesList.getResults().isEmpty() == true) {
            return 0;
        } else {
            return mMoviesList.getResults().size();
        }
    }
}
