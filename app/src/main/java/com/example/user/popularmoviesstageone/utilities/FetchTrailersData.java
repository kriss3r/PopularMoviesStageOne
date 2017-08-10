package com.example.user.popularmoviesstageone.utilities;

import android.os.AsyncTask;

import com.example.user.popularmoviesstageone.Movie;
import com.example.user.popularmoviesstageone.Trailers;

/**
 * Created by krymarowicz on 10.08.2017.
 *
 * public class FetchTrailersData which downloads trailers info required to run videos from youtube in detailed_View.
 */

public class FetchTrailersData extends AsyncTask<Boolean, String, Trailers> {

    private PostTaskListenerTrailers<Trailers> PostTaskListener;

    public FetchTrailersData(PostTaskListenerTrailers<Trailers> postTaskListener) {
        this.PostTaskListener = postTaskListener;
    }




    @Override
    protected Trailers doInBackground(Boolean... booleen) {
        return null;
    }
}
