package com.xty.espressorecipe.network.mvp.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.xty.espressorecipe.network.model.Movie;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by Administrator on 2018/1/28 0028.
 */

public class MovieRepository implements MovieDataSource {

    @Nullable
    private static MovieRepository INSTANCE = null;

    @NonNull
    private final MovieDataSource mMovieRemoteDataSource;

    private MovieRepository(@NonNull MovieDataSource mMovieRemoteDataSource) {
        this.mMovieRemoteDataSource = mMovieRemoteDataSource;
    }

    public static MovieRepository getInstance(@NonNull MovieDataSource mMovieRemoteDataSource){
        if (INSTANCE == null){
            INSTANCE = new MovieRepository(mMovieRemoteDataSource);
        }
        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE = null;
    }

    @Override
    public Flowable<Movie> getMovies(int start, int count) {
        return mMovieRemoteDataSource.getMovies(start, count);
    }
}
