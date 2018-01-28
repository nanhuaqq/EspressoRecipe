package com.xty.espressorecipe.network.mvp.data;

import com.xty.espressorecipe.network.model.Movie;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by Administrator on 2018/1/28 0028.
 */

public interface MovieDataSource {

    Flowable<Movie> getMovies(int start, int count);
}
