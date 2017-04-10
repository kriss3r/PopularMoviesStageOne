package com.example.user.popularmoviesstageone;

import android.content.Context;
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

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by User on 05.04.2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    private ArrayList<Photo> mPhotos;


    public RecyclerAdapter(ArrayList<Photo> mPhotos) {
        this.mPhotos = mPhotos;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.detailed_view,parent,false);

        return new PhotoViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public void onClick(View view) {
        //?


    }

    public static class PhotoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mItemTittle
                        ,mItemPlotSynopsis;
        private ImageView mItemImage
                        , mUserRating;

        public PhotoViewHolder(View v) {
            super(v);
            mItemTittle = (TextView) v.findViewById(R.id.tv_tittle);
            mItemPlotSynopsis = (TextView) v.findViewById(R.id.et_plot_synopsis);
            mItemImage = (ImageView) v.findViewById(R.id.iv_thumbnail);
            mUserRating = (ImageView) v.findViewById(R.id.iv_user_rating);
            v.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            Context context = view.getContext();

            Log.i("E","button clicked");
        }
    }


}
