package com.example.android.mymovies;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by sadat on 2/11/18.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    public View view;
    List<Movie> mMovieData;
    Context context;
    Activity activity;

    public MovieAdapter(Activity activity, View view, Context context) {
        this.context = context;
        this.view = view;
        this.activity = activity;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIDForListItem = R.layout.grid_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIDForListItem, viewGroup, shouldAttachToParentImmediately);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MovieViewHolder holder, int position) {
        Picasso.with(this.context).load(mMovieData.get(position).movieImage).into(holder.mIView);
    }

    @Override
    public int getItemCount() {
        if (mMovieData == null) return 0;
        return mMovieData.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        public final ImageView mIView;
        View mView;

        public MovieViewHolder(View view) {
            super(view);
            mView = view;
            mIView = (ImageView) view.findViewById(R.id.movie_poster_image);
        }
/*        void bind(int gridIndex){
            mImageView.setId(Integer.parseInt(String.valueOf(gridIndex)));
        }*/
    }

}