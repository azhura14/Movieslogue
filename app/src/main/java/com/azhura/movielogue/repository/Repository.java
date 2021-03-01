package com.azhura.movielogue.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.azhura.movielogue.db.Database;
import com.azhura.movielogue.db.FavouriteMoviesDAO;
import com.azhura.movielogue.model.tmdb.Movie;

import java.util.List;

public class Repository {
    private FavouriteMoviesDAO favouriteMoviesDAO;

    public Repository(Application application) {
        Database database = Database.getInstance(application);
        favouriteMoviesDAO = database.getFDAO();

    }

    public LiveData<List<Movie>> getAllFMovies() {
        return favouriteMoviesDAO.getAllFMovies();
    }

    public Movie getMovie(String id) {
        return favouriteMoviesDAO.getMovie(id);
    }

    public void AddMovie(Movie movie) {

        new AddFavouriteMovie(favouriteMoviesDAO).execute(movie);
    }

    public void DeleteMovie(Movie movie) {
        new DeleteFavouriteMovie(favouriteMoviesDAO).execute(movie);
    }

}
