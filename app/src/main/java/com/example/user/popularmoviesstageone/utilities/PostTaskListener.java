package com.example.user.popularmoviesstageone.utilities;

/**
 * Created by User on 21.04.2017.
 */

// public interface used to receive data from AsyncTask
public interface PostTaskListener<Movie> {

    void onPostTask(Movie m);

}