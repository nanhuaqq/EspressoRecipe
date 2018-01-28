package com.xty.espressorecipe.network;

import com.xty.espressorecipe.network.model.Movie;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2018/1/27 0027.
 */

public interface DoubanMovieApiInterface {
    String HOST = "https://api.douban.com";

    //top250
    @GET("/v2/movie/top250")
    Flowable<Movie> movieTop250(@Query("start") int start, @Query("count") int count);

    //北美票房榜  Requied Scope movie_basic_r
//    @GET("/v2/movie/us_box")
//    Flowable<> movieUsBox();
}
