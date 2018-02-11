package com.example.android.mymovies;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import com.example.android.mymovies.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private TextView mSearchResultsTextView;

    MovieAdapter adapter;

    static String S1 = "https://api.themoviedb.org/3/movie/popular?api_key=e6119fc0e6963d6ee2300a97c6d1cf22";

    public static URL githubSearchUrl = null;

    static {
        try {
            githubSearchUrl = new URL(S1);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSearchResultsTextView = (TextView) (findViewById(R.id.text_view));
        new GithubQueryTask().execute(githubSearchUrl);
    }

    private void makeGithubSearchQuery() {
    }

    public class GithubQueryTask extends AsyncTask<URL, Void, String> {

        private final String LOG_TAG = GithubQueryTask.class.getSimpleName();

        protected void onPostExecute(Movie[] singleMovie) {
            if (singleMovie != null) {
                adapter.clear();
                for (int i = 0; i < singleMovie.length; i++) {
                    Movie oneMovie = singleMovie[i];
                    Log.v(LOG_TAG, oneMovie.movieTitle + oneMovie.movieImage);
                    adapter.add(oneMovie);
                }
            }
            super.onPostExecute(Movie);
        }

        private Movie[] getmovieData(String movieInfo)
                throws JSONException {
            final String MDB_RESULT = "results";
            final String MDB_TITLE = "title";
            final String MDB_POSTER = "poster_path";
            JSONObject moviejson = new JSONObject(movieInfo);
            JSONArray movieArray = moviejson.getJSONArray(MDB_RESULT);
            String baseURL = "http://image.tmdb.org/t/p/w185/";
            Movie[] movieDetails = new Movie[5];
            for (int i = 0; i < 5; i++) {
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
        protected String doInBackground(URL... params){
            URL searchUrl = params[0];
            String githubSearchResults = null;
            try {
                githubSearchResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return githubSearchResults;
        }


        // COMPLETED (3) Override onPostExecute to display the results in the TextView
        @Override
        protected void onPostExecute(String githubSearchResults) {
            if (githubSearchResults != null && !githubSearchResults.equals("")) {
                mSearchResultsTextView.setText(githubSearchResults);
            }
        }
    }


}

