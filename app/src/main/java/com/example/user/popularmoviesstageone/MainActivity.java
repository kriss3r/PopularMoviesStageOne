package com.example.user.popularmoviesstageone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.user.popularmoviesstageone.Utilities.NetworkUtils;

import java.io.IOException;

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
}
