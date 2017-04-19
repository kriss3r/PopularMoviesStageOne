package com.example.user.popularmoviesstageone;

import android.content.Context;
import android.content.Intent;
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

/**
 * Created by User on 05.04.2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> implements View.OnClickListener {

    private Movie mMoviesList;

    @Override
    public void onClick(View view) {

    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView mItemTittle,mItemPlotSynopsis;
        public ImageView mItemImage, mUserRating, mDetailedItem;

        public ViewHolder(View v) {
            super(v);
            mItemTittle = (TextView) v.findViewById(R.id.tv_tittle);
            mItemPlotSynopsis = (TextView) v.findViewById(R.id.et_plot_synopsis);
            mItemImage = (ImageView) v.findViewById(R.id.movie_item);
            mDetailedItem = (ImageView) v.findViewById(R.id.iv_thumbnail);
            mUserRating = (ImageView) v.findViewById(R.id.iv_user_rating);
            v.setOnClickListener(this);
        }

// used to move to correct Activity thorough implicit intent.
        @Override
        public void onClick(View view) {
            Context context = itemView.getContext();
            Intent showPhotoIntent = new Intent(context, DetailedActivity.class);
            context.startActivity(showPhotoIntent);
        }
    }

    public RecyclerAdapter(Movie mMoviesList) {
        this.mMoviesList= mMoviesList;
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
        String httpRequestAddress = URL_BASE+SIZE+mMoviesList.getResults().get(position).getPoster_path();
        Picasso.with(holder.itemView.getContext()).load(httpRequestAddress).into(holder.mItemImage);
    }

    @Override
    public int getItemCount() {
        return this.mMoviesList.getResults().size();
    }


}
