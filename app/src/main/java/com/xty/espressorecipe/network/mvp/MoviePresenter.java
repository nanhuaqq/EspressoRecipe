package com.xty.espressorecipe.network.mvp;

import com.xty.espressorecipe.network.model.Movie;
import com.xty.espressorecipe.network.mvp.data.MovieRepository;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2018/1/28 0028.
 */

public class MoviePresenter implements MovieContract.Presenter {

    private final MovieRepository mMovieRepository;

    private final MovieContract.View mMovieView;

    private CompositeDisposable mCompositeDisposable;

    public MoviePresenter(MovieRepository mMovieRepository, MovieContract.View mMovieView) {
        this.mMovieRepository = mMovieRepository;
        this.mMovieView = mMovieView;
        mMovieView.setPresenter(this);
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void subscribe() {
        loadMovie(0,10);
    }

    @Override
    public void unsubscribe() {
        mCompositeDisposable.clear();
    }

    @Override
    public void loadMovie(int start, int count) {
        mCompositeDisposable.clear();
        Disposable disposable = mMovieRepository
                .getMovies(start, count)
                .subscribe(movie -> {
                    processMovie(movie);
                });
        mCompositeDisposable.add(disposable);
    }

    private void processMovie(Movie movie){
        List<Movie.SubjectsBean> subjectsBeans = movie.getSubjects();
        mMovieView.showMovies(subjectsBeans);
    }
}
