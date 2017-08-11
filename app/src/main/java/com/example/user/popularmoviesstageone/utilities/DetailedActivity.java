package com.example.user.popularmoviesstageone.utilities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.user.popularmoviesstageone.Movie;
import com.example.user.popularmoviesstageone.R;
import com.example.user.popularmoviesstageone.Trailers;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestHandler;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailedActivity extends AppCompatActivity {
    public static final String URL_BASE = "http://image.tmdb.org/t/p/";
    public static final String SIZE = "w154";
    private Movie mMoviesList;
    private int mItemNumber;
    private List<Trailers.ResultsBean> mTrailersList;
    private Movie.ResultsBean viewData;
    private ListAdapter mAdapter;

    @BindView(R.id.tv_tittle)
    TextView mItemTittle;
    @BindView(R.id.tv_plot_synopsis)
    TextView mItemPlotSynopsis;
    @BindView(R.id.tv_user_rating)
    TextView mUserRating;
    @BindView(R.id.tv_relase_date)
    TextView mReleaseDate;
    @BindView(R.id.iv_thumbnail)
    ImageView mDetailedItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent it = getIntent();
        viewData = it.getExtras().getParcelable("value");
        ButterKnife.bind(this);
//        Trailers.ResultsBean trData = it.getExtras().getParcelable("");


        mItemTittle.setText(viewData.getOriginal_title());
        mUserRating.setText("User Rating \n" + String.valueOf(viewData.getVote_average()));
        mReleaseDate.setText("Release date \n" + String.valueOf(viewData.getRelease_date()));
        mItemPlotSynopsis.setText(viewData.getOverview());
        String httpRequestAddress = URL_BASE + SIZE + viewData.getPoster_path();
        Picasso.with(this).load(httpRequestAddress).into(mDetailedItem);

        fetchTrailersData(viewData.getId());
        ListView lv = (ListView) findViewById(R.id.TrailerListView);

        for(int i=0;i<mTrailersList.size();i++){
            Log.i("trailer",mTrailersList.get(i).getId());
        }


        // need to fill it with Trailers Data... ( asynctask etc)

// Create the async task and pass it the post task listener.


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
        }
        return (super.onOptionsItemSelected(item));
    }

    public void fetchTrailersData(final Integer movieID) {
        PostTaskListenerTrailers<Trailers> postTaskListener = new PostTaskListenerTrailers<Trailers>() {
            @Override
            public void onPostTask(Trailers t) {
                mTrailersList.addAll(t.getResults());
            }
        };
        new FetchTrailersData(postTaskListener).execute(movieID);
    }
}
