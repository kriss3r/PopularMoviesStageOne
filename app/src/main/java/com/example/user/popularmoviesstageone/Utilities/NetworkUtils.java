package com.example.user.popularmoviesstageone.Utilities;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import static java.lang.System.in;

/**
 * Created by User on 10.04.2017.
 */

/*
Class used to connect/gather all necessary data from Network.
*/
public class NetworkUtils {


    public static final String BASE_URL = "http://image.tmdb.org/3/";
    public static final String DEFAULT_PHONE_SIZE = "w185";
    protected static final String API_KEY = "d1f9bfcfade6bf4b6d859f76f24be3dc";

 /*   It’s constructed using 3 parts:
    The base URL will look like: http://image.tmdb.org/t/p/.
    Then you will need a ‘size’, which will be one of the following: "w92", "w154", "w185", "w342", "w500", "w780", or "original". For most phones we recommend using “w185”.
    And finally the poster path returned by the query, in this case “/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg”*/

 /*Used to build valid URL according to way which used chosed ( most popular or top rated)*/
public static URL buildUrl(String userSelection) throws MalformedURLException{
    String topRated = BASE_URL + "movie?api_key=" + API_KEY + "&sort_by=toprated";
    String mostPopular = BASE_URL + "movie?api_key=" + API_KEY + "&sort_by=popularity";
    String selection;
    URL mainURL = null;

    if(userSelection == "sort_by_top_rated"){
        selection = topRated;
    }else {
        selection = mostPopular;
    }
    try {
        Uri buildUri = Uri.parse(selection).buildUpon().build();
         mainURL = new URL(buildUri.toString());
    } catch (MalformedURLException e){
        e.printStackTrace();
    }
    return mainURL;
    }

/*Method used to receive all http respone content as a String value.*/
public static String getResponseFromHTTPUrl(URL url) throws IOException {
    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

    Scanner sc = new Scanner(in);
    String requestResult = "";
    StringBuilder builder = new StringBuilder(requestResult);
    InputStream inputStream = urlConnection.getInputStream();

    while(sc.hasNext()==true){
        requestResult = builder.append(sc.next()).toString();
    }
    return requestResult;
    }

}
