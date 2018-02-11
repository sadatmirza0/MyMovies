package com.example.android.mymovies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by sadat on 2/11/18.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private String[] mMovieData;

    public MovieAdapter() {
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
    public void onBindViewHolder(MovieViewHolder movieAdapterViewHolder, int position) {
        String MovieForThisDay = mMovieData[position];
        movieAdapterViewHolder.mImageView.setImageResource(Integer.parseInt(MovieForThisDay));
    }

    @Override
    public int getItemCount() {
        if (mMovieData == null) return 0;
        return mMovieData.length;
    }

    public void setMovieData(String[] movieData) {
        mMovieData = movieData;
        notifyDataSetChanged();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        public final ImageView mImageView;

        public MovieViewHolder(View view) {
            super(view);
            mImageView = (ImageView) (view.findViewById(R.id.image_item));
        }
/*        void bind(int gridIndex){
            mImageView.setId(Integer.parseInt(String.valueOf(gridIndex)));
        }*/
    }

}