package com.softvrbox.mvvmtvshows.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.softvrbox.mvvmtvshows.R;
import com.softvrbox.mvvmtvshows.adapters.ImageSliderAdapter;
import com.softvrbox.mvvmtvshows.databinding.ActivityTvShowDetailBinding;
import com.softvrbox.mvvmtvshows.models.TvShow;
import com.softvrbox.mvvmtvshows.viewmodels.TvShowDetailViewModel;

public class TvShowDetailActivity extends AppCompatActivity {

    private ActivityTvShowDetailBinding activityTvShowDetailBinding;
    private TvShowDetailViewModel tvShowDetailViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityTvShowDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_tv_show_detail);
        doInitialization();
    }

    private void doInitialization() {
        tvShowDetailViewModel = new ViewModelProvider(this).get(TvShowDetailViewModel.class);
        getTvShowDetails();
    }

    private void getTvShowDetails(){
        activityTvShowDetailBinding.setIsLoading(true);
        Gson gson = new Gson();
        TvShow tvShow = gson.fromJson(getIntent().getStringExtra("tvShowJson"), TvShow.class);
        tvShowDetailViewModel.getTvShowDetail(tvShow.getId()).observe(
                this,
                tvShowDetailResponse ->{
                    if (tvShowDetailResponse != null){
                        Log.d("elseError", ""+tvShowDetailResponse);
                        activityTvShowDetailBinding.setIsLoading(false);

                        if (tvShowDetailResponse.getTvShowDetail().getPictures() != null){
                            loadImageSlider(tvShowDetailResponse.getTvShowDetail().getPictures());
                        }

                        activityTvShowDetailBinding.tvShowName.setText(tvShowDetailResponse.getTvShowDetail().getName());


                    }else {
                        Log.d("elseError", "Null----"+tvShowDetailResponse);

                    }
                }
        );
    }

    private void loadImageSlider(String[] sliderImages){
        activityTvShowDetailBinding.sliderViewPager.setOffscreenPageLimit(1);
        activityTvShowDetailBinding.sliderViewPager.setAdapter(new ImageSliderAdapter(sliderImages));
        activityTvShowDetailBinding.sliderViewPager.setVisibility(View.VISIBLE);
    }
}