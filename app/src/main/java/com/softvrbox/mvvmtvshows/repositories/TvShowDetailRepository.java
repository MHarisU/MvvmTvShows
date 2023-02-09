package com.softvrbox.mvvmtvshows.repositories;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.softvrbox.mvvmtvshows.network.ApiClient;
import com.softvrbox.mvvmtvshows.network.ApiService;
import com.softvrbox.mvvmtvshows.responses.ShowDetailResponse;
import com.softvrbox.mvvmtvshows.responses.TvShowResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvShowDetailRepository {

    private ApiService apiService;

    public TvShowDetailRepository(){
        apiService = ApiClient.getRetrofit().create(ApiService.class);

    }


    public LiveData<ShowDetailResponse> getTvShowDetails(int tvShowId){
        MutableLiveData<ShowDetailResponse> data = new MutableLiveData<>();
        apiService.getTvShowDetail(tvShowId).enqueue(new Callback<ShowDetailResponse>() {
            @Override
            public void onResponse(@NonNull Call<ShowDetailResponse> call, @NonNull Response<ShowDetailResponse> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<ShowDetailResponse> call,@NonNull Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}
