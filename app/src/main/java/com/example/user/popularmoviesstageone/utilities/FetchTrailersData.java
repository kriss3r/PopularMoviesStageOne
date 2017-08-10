package com.example.user.popularmoviesstageone.utilities;

import android.os.AsyncTask;

import com.example.user.popularmoviesstageone.Movie;
import com.example.user.popularmoviesstageone.Trailers;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by krymarowicz on 10.08.2017.
 *
 * public class FetchTrailersData which downloads trailers info required to run videos from youtube in detailed_View.
 */

public class FetchTrailersData extends AsyncTask<Integer, String, Trailers> {

    private PostTaskListenerTrailers<Trailers> PostTaskListener;

    public FetchTrailersData(PostTaskListenerTrailers<Trailers> postTaskListener) {
        this.PostTaskListener = postTaskListener;
    }

    @Override
    protected Trailers doInBackground(Integer... integers) {
        Trailers trailers;
        URL UrlTrailersRequest = null;
        String responseFromHttpRequest = null;

        try {
            UrlTrailersRequest = NetworkUtils.buildTrailerUrl(integers[0]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            responseFromHttpRequest = NetworkUtils.getResponseFromHTTPUrl(UrlTrailersRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // create Movie object which contains List of Movies from String HTTP response.
        Type collectionType = new TypeToken<Trailers>() {
        }.getType();
        Gson gson = new Gson();
        trailers = gson.fromJson(responseFromHttpRequest, collectionType);
        return trailers;
    }

}
