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
import java.net.URLConnection;
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
    public static URL githubSearchUrl = null;
    static String S1 = "https://api.themoviedb.org/3/movie/popular?api_key=e6119fc0e6963d6ee2300a97c6d1cf22";

    static {
        try {
            githubSearchUrl = new URL(S1);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public class GetMovieTask extends AsyncTask<Void, Void, Movie[]> {

        private final String LOG_TAG = GetMovieTask.class.getSimpleName();
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        // Will contain the raw JSON response as a string.
        String movieinfo = null;

        @Override
        protected void onPostExecute(Movie[] movies) {
            super.onPostExecute(movies);
        }

        private Movie[] getmovieData(String movieInfo)
                throws JSONException {

            final String MDB_RESULT = "results";
            final String MDB_TITLE = "title";
            final String MDB_POSTER = "poster_path";

            JSONObject moviejson = new JSONObject(movieInfo);
            JSONArray movieArray = moviejson.getJSONArray(MDB_RESULT);
            String baseURL = "http://image.tmdb.org/t/p/w185/";
            Movie[] movieDetails = new Movie[10];
            for (int i = 0; i < 10; i++) {
                JSONObject currentMovie = movieArray.getJSONObject(i);
                String movietitle = currentMovie.getString(MDB_TITLE);
                String moviePosterendURL = currentMovie.getString(MDB_POSTER);
                String moviePosterURL = baseURL + moviePosterendURL;
                movieDetails[i] = new Movie(moviePosterURL, movietitle);
            }
            return movieDetails;
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
}