package com.example.android.mymovies;

import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by sadat on 2/10/18.
 * Sample Query's:
 * https://api.themoviedb.org/3/movie/popular?api_key=e6119fc0e6963d6ee2300a97c6d1cf22
 * https://api.themoviedb.org/3/movie/top_rated?api_key=e6119fc0e6963d6ee2300a97c6d1cf22
 * url = new URL("http://api.themoviedb.org/3/movie/popular?api_key=" + NetworkUtils.KEY); //sort by popularity by default
 * url = new URL("http://api.themoviedb.org/3/movie/top_rated?api_key=" + NetworkUtils.KEY);  //sort by ratings
 * Reference: https://github.com/udacity/ud843-QuakeReport/tree/14541da929b771249ea8209698d324b61bbeee7e/app/src/main/java/com/example/android/quakereport
 */

public class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(TAG, "Problem building the URL ", e);
        }
        return url;

    }


//    private static final String Authority = "api.themoviedb.org";
//
//    private static final String BASE_URL = ("https://"+Authority);
//
//    final static String BASE_PATH = "/3/movie/";
//
//    final static String PATH = "popular?";
//
//    final static String API_KEY = "api_key=";
//
//    final static String KEY = "e6119fc0e6963d6ee2300a97c6d1cf22";
//
//    //URL PopularURL = new URL("http://api.themoviedb.org/3/movie/popular?api_key=" + NetworkUtils.KEY); //sort by popularity by default
//
//    public static URL buildUrl(String popularquery) {
//        Uri builtUri = Uri.parse(BASE_URL).buildUpon()
//                .appendQueryParameter(BASE_PATH, PATH)
//                .appendQueryParameter(API_KEY, KEY)
//                .build();
//
//        URL url = null;
//        try {
//            url = new URL(builtUri.toString());
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//
//        Log.v(TAG, "Built URI " + url);
//
//        return url;
//    }
//
//    public NetworkUtils() throws MalformedURLException {
//    }

    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

//    private static List<String> extractMoviesFromJson(String moviesJSON){
//        if (TextUtils.isEmpty(moviesJSON)) {
//            return null;
//        }
//        List<String> Movies = new ArrayList<>();
//
//        try {
//
//            // Create a JSONObject from the JSON response string
//            JSONObject baseJsonResponse = new JSONObject(moviesJSON);
//            JSONArray baseArray = baseJsonResponse.getJSONArray("results");
//            for (int i = 0; i < 20; i++) {
//                String Jobj = baseArray.getString(Integer.parseInt("backdrop_path"));
//                Movies.add(Jobj);
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return Movies;
//    }

}