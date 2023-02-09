package com.softvrbox.mvvmtvshows.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.softvrbox.mvvmtvshows.R;
import com.softvrbox.mvvmtvshows.adapters.TvShowsAdapter;
import com.softvrbox.mvvmtvshows.databinding.ActivityMainBinding;
import com.softvrbox.mvvmtvshows.listeners.TvShowListener;
import com.softvrbox.mvvmtvshows.models.TvShow;
import com.softvrbox.mvvmtvshows.viewmodels.MostPopularTvShowsViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TvShowListener {

    private ActivityMainBinding activityMainBinding;
    private MostPopularTvShowsViewModel mostPopularTvShowsViewModel;
    private List<TvShow> tvShows = new ArrayList<>();
    private TvShowsAdapter tvShowsAdapter;
    private int currentPage = 1;
    private int totalPages = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        doInitialization();

    }

    private void doInitialization(){
        activityMainBinding.tvshowsRecyclerview.setHasFixedSize(true);
        mostPopularTvShowsViewModel = new ViewModelProvider(this).get(MostPopularTvShowsViewModel.class);
        tvShowsAdapter = new TvShowsAdapter(tvShows, this);
        activityMainBinding.tvshowsRecyclerview.setAdapter(tvShowsAdapter);
        activityMainBinding.tvshowsRecyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!activityMainBinding.tvshowsRecyclerview.canScrollVertically(1)){
                    if (currentPage <= totalPages){
                        currentPage += 1;
                        getMostPopularTvShows();
                    }
                }
            }
        });
        getMostPopularTvShows();
    }

    private void getMostPopularTvShows(){
        toggleLoading();
        mostPopularTvShowsViewModel.getMostPopularTvShows(currentPage).observe(this, mostPopularTvShowsResponse ->{
            toggleLoading();
            if (mostPopularTvShowsResponse != null){
                totalPages = mostPopularTvShowsResponse.getPages();
                if (mostPopularTvShowsResponse.getTv_shows() != null){
                    int oldCount = tvShows.size();
                    tvShows.addAll(mostPopularTvShowsResponse.getTv_shows());
                    tvShowsAdapter.notifyItemRangeInserted(oldCount, tvShows.size());
                }
            }

        });
    }

    private void toggleLoading(){
        if (currentPage == 1){
            if (activityMainBinding.getIsLoading() != null && activityMainBinding.getIsLoading()){
                activityMainBinding.setIsLoading(false);
            }else {
                activityMainBinding.setIsLoading(true);
            }
        }else {
            if (activityMainBinding.getIsLoadingMore() != null && activityMainBinding.getIsLoadingMore()){
                activityMainBinding.setIsLoadingMore(false);
            }else {
                activityMainBinding.setIsLoadingMore(true);
            }

        }
    }

    @Override
    public void onTvShowClicked(TvShow tvShow) {
        Intent intent = new Intent(getApplicationContext(), TvShowDetailActivity.class);
        Gson gson = new Gson();
        String tvShowJson = gson.toJson(tvShow);
        intent.putExtra("tvShowJson", tvShowJson);
        startActivity(intent);

    }
}