package com.example.android.mymovies;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
/**
 * Created by sadat on 2/11/18.
 */

public class GetMovieTask extends AsyncTask<Void, Void, Movie[]> {
    private final String LOG_TAG = GetMovieTask.class.getSimpleName();
    Activity activity;
    ConnectivityManager ConnectivityManager;
    HttpURLConnection urlConnection = null;
    BufferedReader reader = null;
    // Will contain the raw JSON response as a string.
    String movieinfo = null;

    Movie[] moviesList = new Movie[20];

   /* @Override
    protected void onPostExecute(Movie[] movies) {
        return;
    }*/

    @Override
    protected void onPostExecute(Movie[] movies) {

    }

    private Movie[] getmovieData(String movieInfo)
            throws JSONException {

        final String MDB_RESULT = "results";
        final String MDB_TITLE = "title";
        final String MDB_POSTER = "poster_path";

        JSONObject moviejson = new JSONObject(movieInfo);
        JSONArray movieArray = moviejson.getJSONArray(MDB_RESULT);
        String baseURL = "http://image.tmdb.org/t/p/w500/";
        for (int i = 0; i < 20; i++) {
            JSONObject currentMovie = movieArray.getJSONObject(i);
            int movieID = Integer.parseInt(currentMovie.getString("id"));
            String tempbackDropImage = baseURL + currentMovie.getString("backdrop_path");
            String movietitle = currentMovie.getString(MDB_TITLE);
            String moviePosterendURL = currentMovie.getString(MDB_POSTER);
            String moviePosterURL = baseURL + moviePosterendURL;
            moviesList[i] = new Movie(movietitle, moviePosterURL);
        }
        return moviesList;
    }

    // COMPLETED (2) Override the doInBackground method to perform the query. Return the results. (Hint: You've already written the code to perform the query)
    @Override
    protected Movie[] doInBackground(Void... params) {
        try {

            URL url = new URL("https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=e6119fc0e6963d6ee2300a97c6d1cf22");
            String movieDbUrl = url.toString();
            Log.v(LOG_TAG, movieDbUrl);
            // Create the request to OpenWeatherMap, and open the connection
            URLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuilder buffer = new StringBuilder();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }
            movieinfo = buffer.toString();
            Log.v(LOG_TAG, movieinfo);
        } catch (IOException e) {
            Log.e("PlaceholderFragment", "Error ", e);
            // If the code didn't successfully get the weather data, there's no point in attemping
            // to parse it.
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e("PlaceholderFragment", "Error closing stream", e);
                }
            }
        }
        try {
            return getmovieData(movieinfo);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
