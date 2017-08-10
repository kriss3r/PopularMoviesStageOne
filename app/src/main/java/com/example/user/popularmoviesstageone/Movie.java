package com.example.user.popularmoviesstageone;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 19.04.2017.
 */

// Class generated by GSON formatter.
public class Movie implements Parcelable {

    private List<ResultsBean> results;

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }


    public static class ResultsBean implements Parcelable {

        /**
         * poster_path : /tWqifoYuwLETmmasnGHO7xBjEtt.jpg
         * adult : false
         * overview : A live-action adaptation of Disney's version of the classic 'Beauty and the Beast' tale of a cursed prince and a beautiful young woman who helps him break the spell.
         * release_date : 2017-03-16
         * genre_ids : [14,10749]
         * id : 321612
         * original_title : Beauty and the Beast
         * original_language : en
         * title : Beauty and the Beast
         * backdrop_path : /6aUWe0GSl69wMTSWWexsorMIvwU.jpg
         * popularity : 156.872106
         * vote_count : 1812
         * video : false
         * vote_average : 6.9
         */

        private String poster_path;
        private String overview;
        private String release_date;
        private String original_title;
        private double vote_average;
        private boolean video;

        public String getPoster_path() {
            return poster_path;
        }

        public void setPoster_path(String poster_path) {
            this.poster_path = poster_path;
        }

        public String getOverview() {
            return overview;
        }

        public void setOverview(String overview) {
            this.overview = overview;
        }

        public String getRelease_date() {
            return release_date;
        }

        public void setRelease_date(String release_date) {
            this.release_date = release_date;
        }

        public String getOriginal_title() {
            return original_title;
        }

        public void setOriginal_title(String original_title) {
            this.original_title = original_title;
        }

        public double getVote_average() {
            return vote_average;
        }


        public boolean getVideoAvailability(){return video;}


        public void setVote_average(double vote_average) {
            this.vote_average = vote_average;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.poster_path);
            dest.writeString(this.overview);
            dest.writeString(this.release_date);
            dest.writeString(this.original_title);
            dest.writeDouble(this.vote_average);
            dest.writeString(String.valueOf(this.vote_average));
        }

        public ResultsBean() {
        }

        protected ResultsBean(Parcel in) {
            this.poster_path = in.readString();
            this.overview = in.readString();
            this.release_date = in.readString();
            this.original_title = in.readString();
            this.vote_average = in.readDouble();
            this.video = Boolean.parseBoolean(in.readString());
        }

        public static final Creator<ResultsBean> CREATOR = new Creator<ResultsBean>() {
            @Override
            public ResultsBean createFromParcel(Parcel source) {
                return new ResultsBean(source);
            }

            @Override
            public ResultsBean[] newArray(int size) {
                return new ResultsBean[size];
            }
        };
    }

    public Movie() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.results);
    }

    protected Movie(Parcel in) {
        this.results = new ArrayList<ResultsBean>();
        in.readList(this.results, ResultsBean.class.getClassLoader());
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
