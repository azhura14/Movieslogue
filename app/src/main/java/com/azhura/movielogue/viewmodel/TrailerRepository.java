package com.azhura.movielogue.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.azhura.movielogue.BuildConfig;
import com.azhura.movielogue.model.tmdb.Movie;
import com.azhura.movielogue.model.tmdb.Trailer;
import com.azhura.movielogue.model.tmdb.TrailerApiList;
import com.azhura.movielogue.utils.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.azhura.movielogue.view.MoviesInfo.idOfMovie;

public class TrailerRepository {

    private List<Trailer> trailerList = new ArrayList<>();
    private final MutableLiveData<List<Trailer>> mutableLiveData = new MutableLiveData<>();

    public MutableLiveData<List<Trailer>> getMutableLiveData() {
        RetrofitClient.getInstance()
                .getMovieService().getTrailers(idOfMovie, BuildConfig.ApiKey)
                .enqueue(new Callback<TrailerApiList>() {
                    @Override
                    public void onResponse(Call<TrailerApiList> call, Response<TrailerApiList> response) {
                        Log.v("onResponse", "Succeeded Trailers");
                        if (response.body() != null) {
                            trailerList = response.body().getTrailers();
                            mutableLiveData.setValue(trailerList);
                        }
                    }

                    @Override
                    public void onFailure(Call<TrailerApiList> call, Throwable t) {
                        Log.v("onFailure", "Failed to get Trailers");
                    }
                });

        return mutableLiveData;
    }
}
