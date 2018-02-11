package com.example.android.mymovies;

/**
 * Created by sadat on 2/11/18.
 */

public class Movie {

    public int id;

    public String movieImage;


    public String movieTitle;

    public String movieOverView;

    public String movieRating;

    public String movieReleaseDate;

    public String movieBackDropImage;

    public String language;

    public Movie(String moviePosterURL, String movietitle) {
        movieImage = null;
        movieTitle = "Constructor was called";
    }

    public Movie(String image, String title, String overView, String rating, String releaseDate, String backDropImage, int id, String language) {
        this.movieImage = image;
        this.movieTitle = title;
        this.movieOverView = overView;
        this.movieRating = rating;
        this.movieReleaseDate = releaseDate;
        this.movieBackDropImage = backDropImage;
        this.id = id;
        this.language = language;
    }

}