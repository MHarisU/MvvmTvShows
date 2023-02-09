package com.softvrbox.mvvmtvshows.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.softvrbox.mvvmtvshows.repositories.TvShowDetailRepository;
import com.softvrbox.mvvmtvshows.responses.ShowDetailResponse;
import com.softvrbox.mvvmtvshows.responses.TvShowResponse;

public class TvShowDetailViewModel extends ViewModel {

    private TvShowDetailRepository tvShowDetailRepository;

    public TvShowDetailViewModel(){
        tvShowDetailRepository = new TvShowDetailRepository();
    }

    public LiveData<ShowDetailResponse> getTvShowDetail(int tvShowId){
        return tvShowDetailRepository.getTvShowDetails(tvShowId);
    }
}
