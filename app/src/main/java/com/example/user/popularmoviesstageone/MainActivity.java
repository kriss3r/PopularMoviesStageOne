package com.example.user.popularmoviesstageone;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.user.popularmoviesstageone.Utilities.NetworkUtils;

import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mRecyclerViewAdapter;
    private GridLayoutManager mGridLayoutManager;
    private static final int SPAN_COUNT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // link
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_images);
        mGridLayoutManager = new GridLayoutManager(this,SPAN_COUNT);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
      //  mRecyclerViewAdapter = new RecyclerAdapter.PhotoViewHolder(this);

        // FetchClass test.
        String location = "sort_by_top_rated";
        String result = null;
        try {
            result = new FetchMoviesData().execute(location).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        Log.i("Result", result);

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
            Log.i("i","most popular");
        }else {
            Log.i("i","top rated");
        }
       // Log.i("ERROR", item.
        return super.onOptionsItemSelected(item);
    }

/*Class used to download data outside of main thread*/
    public class FetchMoviesData extends AsyncTask<String, String, String>{

        @Override
        protected String doInBackground(String... strings) {
            URL movieRequest = null;
            String responseFromHttpRequest = "";
            if (strings[0].isEmpty()){
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

            return responseFromHttpRequest;
        }
    }



}
