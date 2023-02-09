package com.softvrbox.mvvmtvshows.responses;

import com.google.gson.annotations.SerializedName;
import com.softvrbox.mvvmtvshows.models.TvShowDetail;

public class ShowDetailResponse {

    @SerializedName("tvShow")
    private TvShowDetail tvShowDetail;

    public TvShowDetail getTvShowDetail() {
        return tvShowDetail;
    }
}
