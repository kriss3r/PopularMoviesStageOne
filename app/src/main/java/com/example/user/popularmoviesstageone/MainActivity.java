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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mRecyclerViewAdapter;
    private GridLayoutManager mGridLayoutManager;
    private static final int SPAN_COUNT = 2;
    private List<JSONObject> listOfJsons;
    private List<Movie> moviesList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // link
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_images);
        mGridLayoutManager = new GridLayoutManager(this,SPAN_COUNT);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        listOfJsons = new LinkedList<JSONObject>();
        moviesList = Collections.EMPTY_LIST;

        //  mRecyclerViewAdapter = new RecyclerAdapter.PhotoViewHolder(this);
        // FetchClass test.
        String location = "sort_by_top_rated";
        String result = null;
        try {
            listOfJsons = new FetchMoviesData().execute(location).get();
          //  moviesList = new ConvertToObjects().execute(listOfJsons).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

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
    public class FetchMoviesData extends AsyncTask<String, String,List<JSONObject>> {

    @Override
    protected List<JSONObject> doInBackground(String... strings) {
        List<JSONObject> listOfJsons = new LinkedList<JSONObject>();
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

        try {
            JSONObject jObject = new JSONObject(responseFromHttpRequest);
            JSONArray jsonArray = jObject.getJSONArray("results");
            for (int i = 0; i < jsonArray.length(); i++) {
                listOfJsons.add(new JSONObject(String.valueOf(jsonArray.getJSONObject(i))));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listOfJsons;
        }
    }
}
