package com.xty.espressorecipe.network.ui;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xty.espressorecipe.R;
import com.xty.espressorecipe.network.mvp.MoviePresenter;
import com.xty.espressorecipe.network.mvp.data.MovieRemoteDataSource;
import com.xty.espressorecipe.network.mvp.data.MovieRepository;

public class MovieActivity extends AppCompatActivity implements MovieFragment.OnFragmentInteractionListener{

    private MoviePresenter mMoviePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        MovieFragment movieFragment = (MovieFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (movieFragment == null){
            movieFragment = MovieFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.contentFrame,movieFragment).commitAllowingStateLoss();
        }

        mMoviePresenter = new MoviePresenter(
                MovieRepository.getInstance(MovieRemoteDataSource.getInstance())
                ,movieFragment);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
