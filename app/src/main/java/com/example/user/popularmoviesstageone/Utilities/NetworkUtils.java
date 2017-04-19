package com.example.user.popularmoviesstageone.Utilities;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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


    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static final String DEFAULT_PHONE_SIZE = "w185";
    protected static final String API_KEY = "d1f9bfcfade6bf4b6d859f76f24be3dc&";

 /*Used to build valid URL according to way which used chosed ( most popular or top rated)*/
public static URL buildUrl(Boolean userSelection) throws MalformedURLException{
    String topRated = BASE_URL + "movie/popular?api_key=" + API_KEY;
    String mostPopular = BASE_URL + "movie/top_rated?api_key=" + API_KEY;
    String selection = "";
    URL mainURL = null;

    if(userSelection == false){
        selection = topRated;
    }else if(userSelection == true) {
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

    InputStream is = urlConnection.getInputStream();
    InputStreamReader inputReader = new InputStreamReader(is);
    Scanner sc = new Scanner(is);
    sc.useDelimiter(System.getProperty("line.separator"));
    String requestResult = "";
    String line;
    StringBuilder builder = new StringBuilder(requestResult);
    while(sc.hasNext()==true){
        builder.append(sc.next());
    }
   requestResult = builder.toString();
    return requestResult;
    }

}
