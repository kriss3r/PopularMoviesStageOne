package com.example.user.popularmoviesstageone;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by krymarowicz on 10.08.2017.
 */

public class Trailers implements Parcelable {

    /**
     * id : 1399
     * results : [{"id":"572003f992514121f30003c6","iso_639_1":"en","iso_3166_1":"US","key":"BpJYNVhGf1s","name":"\"The Game Begins\"","site":"YouTube","size":720,"type":"Trailer"},{"id":"55311e34c3a368411700235c","iso_639_1":"en","iso_3166_1":"US","key":"iGp_N3Ir7Do","name":"Trailer","site":"YouTube","size":720,"type":"Trailer"},{"id":"5335ee1dc3a36826430000e6","iso_639_1":"en","iso_3166_1":"US","key":"s7L2PVdrb_8","name":"Intro","site":"YouTube","size":720,"type":"Opening Credits"}]
     */

    private int id;

    private List<ResultsBean> results;

    public Trailers() {
    }

    @Override
    public int describeContents() {
        return 0;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.results);
    }

    protected Trailers(Parcel in) {
        this.results = new ArrayList<Trailers.ResultsBean>();
        in.readList(this.results, Trailers.ResultsBean.class.getClassLoader());
    }




    public static class ResultsBean implements Parcelable {
        /**
         * id : 572003f992514121f30003c6
         * iso_639_1 : en
         * iso_3166_1 : US
         * key : BpJYNVhGf1s
         * name : "The Game Begins"
         * site : YouTube
         * size : 720
         * type : Trailer
         */

        private String id;
        private String iso_639_1;
        private String iso_3166_1;
        private String key;
        private String name;
        private String site;
        private int size;
        private String type;

        public ResultsBean() {
        }
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIso_639_1() {
            return iso_639_1;
        }

        public void setIso_639_1(String iso_639_1) {
            this.iso_639_1 = iso_639_1;
        }

        public String getIso_3166_1() {
            return iso_3166_1;
        }

        public void setIso_3166_1(String iso_3166_1) {
            this.iso_3166_1 = iso_3166_1;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSite() {
            return site;
        }

        public void setSite(String site) {
            this.site = site;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.key);
            dest.writeString(this.name);
            dest.writeString(this.type);
            dest.writeString(this.type);
        }

        protected ResultsBean(Parcel in) {
            this.id = in.readString();
            this.key = in.readString();
            this.name = in.readString();
            this.site = in.readString();
            this.type = in.readString();
        }

        public static final Creator<Trailers.ResultsBean> CREATOR = new Creator<Trailers.ResultsBean>() {
            @Override
            public Trailers.ResultsBean createFromParcel(Parcel source) {
                return new Trailers.ResultsBean(source);
            }

            @Override
            public Trailers.ResultsBean[] newArray(int size) {
                return new Trailers.ResultsBean[size];
            }
        };
    }




    public static final Creator<Trailers> CREATOR = new Creator<Trailers>() {
        @Override
        public Trailers createFromParcel(Parcel source) {
            return new Trailers();
        }

        @Override
        public Trailers[] newArray(int size) {
            return new Trailers[size];
        }
    };

}

