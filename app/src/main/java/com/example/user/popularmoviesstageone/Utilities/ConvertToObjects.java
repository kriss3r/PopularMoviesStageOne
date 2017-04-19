package com.example.user.popularmoviesstageone.Utilities;

import android.os.AsyncTask;

import com.example.user.popularmoviesstageone.Movie;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import org.json.JSONObject;

import java.io.Reader;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

/**
 * Created by User on 19.04.2017.
 */

public class ConvertToObjects extends AsyncTask<List<JSONObject>,Void,List<Movie>> {

    @Override
    protected List<Movie> doInBackground(List<JSONObject>... lists) {

        Type collectionType = new TypeToken<Collection<Movie>>(){}.getType();
        Collection<Movie> enums = new Gson().fromJson(String.valueOf(lists), collectionType);
        // create List of Movie objects from list of JSONs



        return null;
    }
}
