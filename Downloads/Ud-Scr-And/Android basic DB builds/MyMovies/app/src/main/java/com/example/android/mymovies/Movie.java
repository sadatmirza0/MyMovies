package com.example.android.mymovies;

import android.widget.TextView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by sadat on 2/11/18.
 */

public class Movie {
    String movieImage;
    String movieTitle;

    Movie(String moviePosterURL, String movietitle) {
        this.movieImage = moviePosterURL;
        this.movieTitle = movietitle;
    }
}
