package com.example.user.popularmoviesstageone;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.OrientationEventListener;
import android.widget.Toast;

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
    public Movie mMoviesList;
    private OrientationEventListener mOrientationListener;
    private boolean sortOrder = false; // false for top_rated, true for most_popular
    @BindView(R.id.rv_images)
    RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRecyclerView();
        performConnectionAttempt(sortOrder);

    }

    // triggered to obtain data from AsyncTask.
    public void fetchMoviesData(boolean Order){
        PostTaskListener<Movie> postTaskListener = new PostTaskListener<Movie>() {
            @Override
            public void onPostTask(Movie m) {
                mMoviesList = m;
                mRecyclerViewAdapter.swapDataSet(mMoviesList);
            }
        };
// Create the async task and pass it the post task listener.
        new FetchMoviesData(postTaskListener).execute(Order);

    }

    public void performConnectionAttempt(boolean sortOrder) {
        if (isConnectedToInternet()) {
            fetchMoviesData(sortOrder);
        } else {
            Toast.makeText(this, "No internet connection, to try again go to settings and choose sort type", Toast.LENGTH_LONG).show();
        }
    }

    // method used to check if there is a network connection, additional permissions required in manifest file(Access.Network.State).
    public boolean isConnectedToInternet() {
        ConnectivityManager connectivity = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }

        }
        return false;
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
            performConnectionAttempt(sortOrder);
            setRecyclerView();

        }else if((item.getItemId()==R.id.it_most_rated &&sortOrder!=false)) {
            sortOrder = false;
            performConnectionAttempt(sortOrder);
            setRecyclerView();
        }
        return super.onOptionsItemSelected(item);
    }


}
