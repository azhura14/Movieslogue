package com.azhura.movielogue.model.tmdb;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TrailerApiList {

    @SerializedName("id")
    private Integer id;
    @SerializedName("results")
    private List<Trailer> trailers = null;

    public List<Trailer> getTrailer() {
        return trailers;
    }

    public Integer getId() {
        return id;
    }

    public void setTrailer(List<Trailer> trailer) {
        this.trailers = trailer;
    }

    public List<Trailer> getTrailers() {
        return trailers;
    }
}
