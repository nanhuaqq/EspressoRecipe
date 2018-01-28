package com.xty.espressorecipe.network.mvp.data;

import com.xty.espressorecipe.network.RetrofitSingleton;
import com.xty.espressorecipe.network.model.Movie;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/1/28 0028.
 */

public class MovieRemoteDataSource implements MovieDataSource {

    private static MovieRemoteDataSource INSTANCE;
    @Override
    public Flowable<Movie> getMovies(int start, int count) {
        return RetrofitSingleton.getInstance()
                .getDoubanMovieInterface()
                .movieTop250(start,count)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private MovieRemoteDataSource(){}

    public static MovieRemoteDataSource getInstance(){
        if (INSTANCE == null){
            INSTANCE = new MovieRemoteDataSource();
        }
        return INSTANCE;
    }

}
