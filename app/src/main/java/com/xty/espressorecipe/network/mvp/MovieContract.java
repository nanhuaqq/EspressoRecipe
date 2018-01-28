package com.xty.espressorecipe.network.mvp;

import com.xty.espressorecipe.network.model.Movie;

import java.util.List;

/**
 * Created by Administrator on 2018/1/28 0028.
 */
public interface MovieContract {
    interface View extends BaseView<Presenter>{
        void setLoadingIndicator(boolean active);
        void showMovies(List<Movie.SubjectsBean> movies);
    }

    interface Presenter extends BasePresenter{
        void loadMovie(int start,int count);
    }
}
