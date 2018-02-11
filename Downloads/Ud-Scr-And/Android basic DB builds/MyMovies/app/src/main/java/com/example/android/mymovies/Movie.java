package com.example.android.mymovies;

import android.widget.TextView;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by sadat on 2/11/18.
 */

public class Movie {
    String movieImage;
    String movieTitle;

    public Movie(String image, String title){
        this.movieImage = image;
        this.movieTitle = title;
    }

}
