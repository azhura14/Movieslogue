package com.azhura.movielogue.repository;

import android.os.AsyncTask;

import com.azhura.movielogue.db.FavouriteMoviesDAO;
import com.azhura.movielogue.model.tmdb.Movie;

public class DeleteFavouriteMovie extends AsyncTask<Movie, Void, Void> {
    private FavouriteMoviesDAO favouriteMoviesDAO;

    public DeleteFavouriteMovie(FavouriteMoviesDAO favouriteMoviesDAO) {
        this.favouriteMoviesDAO = favouriteMoviesDAO;
    }

    @Override
    protected Void doInBackground(Movie... movies) {
        favouriteMoviesDAO.deleteFMovie(movies[0]);
        return null;
    }
}
