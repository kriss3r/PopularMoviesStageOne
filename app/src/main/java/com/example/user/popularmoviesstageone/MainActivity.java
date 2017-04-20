package com.example.user.popularmoviesstageone;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.OrientationEventListener;

import com.example.user.popularmoviesstageone.utilities.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    private RecyclerAdapter mRecyclerViewAdapter;
    private GridLayoutManager mGridLayoutManager;
    private static final int SPAN_COUNT = 2;
    private Movie mMoviesList;
    private OrientationEventListener mOrientationListener;
    private boolean sortOrder = false; // false for top_rated, true for most_popular
    @BindView(R.id.rv_images)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // FetchClass test.
        fetchMoviesData(sortOrder);
        setRecyclerView();
    }

    // triggered to obtain data from AsyncTask.
    public void fetchMoviesData(boolean Order){
        try {
            mMoviesList = new FetchMoviesData().execute(Order).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    // used to set RecyclerView
    public void setRecyclerView(){
        ButterKnife.bind(this);
        mGridLayoutManager = new GridLayoutManager(this,SPAN_COUNT);
        mGridLayoutManager.setAutoMeasureEnabled(true);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerViewAdapter = new RecyclerAdapter(mMoviesList);
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

        if (item.getItemId()==R.id.it_most_popular &&sortOrder!=true){
            sortOrder = true;
            fetchMoviesData(sortOrder);
            setRecyclerView();

        }else if((item.getItemId()==R.id.it_most_rated &&sortOrder!=false)) {
            sortOrder = false;
            fetchMoviesData(sortOrder);
            setRecyclerView();
        }
        return super.onOptionsItemSelected(item);
    }

/*Class used to download data outside of main thread & convert it to objects using Gson*/
    public class FetchMoviesData extends AsyncTask<Boolean, String,Movie> {

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
            Type collectionType = new TypeToken<Movie>(){}.getType();
            Gson gson = new Gson();
            movieList = gson.fromJson(responseFromHttpRequest, collectionType);
        return movieList;
        }
    }
}
