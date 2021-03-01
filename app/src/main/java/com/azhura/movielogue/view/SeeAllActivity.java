package com.azhura.movielogue.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.azhura.movielogue.R;
import com.azhura.movielogue.adapter.TrailerAdapter;
import com.azhura.movielogue.databinding.ActivitySeeAllBinding;
import com.azhura.movielogue.model.tmdb.Trailer;
import com.azhura.movielogue.viewmodel.TrailerViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class SeeAllActivity extends AppCompatActivity {

    private ActivitySeeAllBinding binding;
    private TrailerAdapter trailerAdapter;
    private TrailerViewModel trailerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_see_all);

        trailerViewModel = new ViewModelProvider(this).get(TrailerViewModel.class);

        setupRecyclerViews();

        getTrailers();

    }

    private void setupRecyclerViews() {
        // Trailers
        binding.listOfTrailers.setHasFixedSize(true);
        binding.listOfTrailers.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    public void getTrailers() {
        trailerViewModel.getAllTrailers().observe(this, new Observer<List<Trailer>>() {
            @Override
            public void onChanged(@Nullable List<Trailer> trailers) {
                trailerAdapter = new TrailerAdapter(getApplicationContext(), trailers);

                if (trailers != null && trailers.isEmpty()) {
                    binding.listOfTrailers.setVisibility(View.GONE);
                    binding.noTrailers.setVisibility(View.VISIBLE);
                }
                Snackbar.make(findViewById(android.R.id.content), trailers.toString(), Snackbar.LENGTH_SHORT).show();

                binding.listOfTrailers.setAdapter(trailerAdapter);
            }
        });
    }
}