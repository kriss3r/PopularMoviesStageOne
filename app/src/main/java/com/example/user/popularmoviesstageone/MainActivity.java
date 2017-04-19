package com.example.user.popularmoviesstageone;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.JsonReader;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.user.popularmoviesstageone.Utilities.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerAdapter mRecyclerViewAdapter;
    private GridLayoutManager mGridLayoutManager;
    private static final int SPAN_COUNT = 2;
    private List<JSONObject> listOfJsons;
    private Movie moviesList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // FetchClass test.
        String location = "sort_by_top_rated";
        String result = null;
        try {
            moviesList = new FetchMoviesData().execute(location).get();
            List<Movie.ResultsBean> mv = moviesList.getResults();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_images);
        mGridLayoutManager = new GridLayoutManager(this.getApplicationContext(),SPAN_COUNT,LinearLayoutManager.VERTICAL,false);
        Log.i("span size",mGridLayoutManager.getSpanSizeLookup().toString());
        Log.i("next",String.valueOf(mGridLayoutManager.getSpanCount()));
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        moviesList = null;
        mRecyclerView.setHasFixedSize(true);
        mRecyclerViewAdapter = new RecyclerAdapter(moviesList);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
    }

 // used to link main_menu.xml as options menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
// used to get info about which sort option to use
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==R.id.it_most_popular){
        }else {
        }
        return super.onOptionsItemSelected(item);
    }

/*Class used to download data outside of main thread*/
    public class FetchMoviesData extends AsyncTask<String, String,Movie> {

    @Override
    protected Movie doInBackground(String... strings) {
        Movie movieList;
        URL movieRequest = null;

        String responseFromHttpRequest = null;
        if (strings[0].isEmpty()) {
            return null;
        }

        try {
            movieRequest = NetworkUtils.buildUrl(strings[0]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            responseFromHttpRequest = NetworkUtils.getResponseFromHTTPUrl(movieRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }

            // create Movie object which contains List of Movies from String HTTP response.
            Type collectionType = new TypeToken<Movie>(){}.getType();
            movieList = new Gson().fromJson(responseFromHttpRequest, collectionType);
        return movieList;
        }
    }
}
