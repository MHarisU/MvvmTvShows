package com.softvrbox.mvvmtvshows.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.softvrbox.mvvmtvshows.repositories.MostPopularTvShowsRepository;
import com.softvrbox.mvvmtvshows.responses.TvShowResponse;

public class MostPopularTvShowsViewModel extends ViewModel {

    private MostPopularTvShowsRepository mostPopularTvShowsRepository;

    public MostPopularTvShowsViewModel(){
        mostPopularTvShowsRepository = new MostPopularTvShowsRepository();

    }

    public LiveData<TvShowResponse> getMostPopularTvShows(int page){
        return mostPopularTvShowsRepository.getMostPopularTvShows(page);
    }
}
