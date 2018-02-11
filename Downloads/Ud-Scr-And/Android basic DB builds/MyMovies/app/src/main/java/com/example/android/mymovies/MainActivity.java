package com.example.android.mymovies;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    public static URL githubSearchUrl = null;
    static String S1 = "https://api.themoviedb.org/3/movie/popular?api_key=e6119fc0e6963d6ee2300a97c6d1cf22";
    static {
        try {
            githubSearchUrl = new URL(S1);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    MovieAdapter adapter;
    Context context;
    private TextView mSearchResultsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSearchResultsTextView = (TextView) (findViewById(R.id.text_view));
    }

}

