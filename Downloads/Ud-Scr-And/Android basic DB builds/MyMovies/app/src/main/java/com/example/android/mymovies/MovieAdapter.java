package com.example.android.mymovies;

import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by sadat on 2/11/18.
 */

public class MovieAdapter extends ArrayAdapter<Movie> {
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Movie singleMoviecontent = getItem(position);
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.grid_item, parent, false);
        ImageView poster = (ImageView) rootView.findViewById(R.id.movie_poster_image);
        Picasso.with(getContext()).load(singleMoviecontent.movieImage).into(poster);
        TextView name = (TextView) rootView.findViewById(R.id.movie_name);
        name.setText(singleMoviecontent.movieTitle);
        return rootView;
    }

    public MovieAdapter(FragmentActivity context, List<Movie> resource) {
        super(context, 0, resource);
    }
}