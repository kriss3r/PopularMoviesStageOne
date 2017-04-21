package com.example.user.popularmoviesstageone.utilities;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.example.user.popularmoviesstageone.Movie;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by User on 21.04.2017.
 */

/*Class used to download data outside of main thread & convert it to objects using Gson*/
public class FetchMoviesData extends AsyncTask<Boolean, String, Movie> {
    ProgressDialog dialog;
    private PostTaskListener<Movie> postTaskListener;

    public FetchMoviesData(PostTaskListener<Movie> postTaskListener) {
        this.postTaskListener = postTaskListener;
    }


    @Override
    protected Movie doInBackground(Boolean... userSelection) {
        Movie movieList;
        URL movieRequest = null;

        String responseFromHttpRequest = null;

        try {
            movieRequest = NetworkUtils.buildUrl(userSelection[0]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            responseFromHttpRequest = NetworkUtils.getResponseFromHTTPUrl(movieRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // create Movie object which contains List of Movies from String HTTP response.
        Type collectionType = new TypeToken<Movie>() {
        }.getType();
        Gson gson = new Gson();
        movieList = gson.fromJson(responseFromHttpRequest, collectionType);
        return movieList;
    }

    @Override
    protected void onPostExecute(Movie movie) {
        if (movie != null && postTaskListener != null)
            postTaskListener.onPostTask(movie);
    }

}