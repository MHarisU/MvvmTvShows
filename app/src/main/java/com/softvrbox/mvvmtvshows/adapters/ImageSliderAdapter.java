package com.softvrbox.mvvmtvshows.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.softvrbox.mvvmtvshows.R;
import com.softvrbox.mvvmtvshows.databinding.ItemSliderImageBinding;
import com.softvrbox.mvvmtvshows.databinding.ItemTvViewBinding;

public class ImageSliderAdapter extends RecyclerView.Adapter<ImageSliderAdapter.ImageSliderViewHolder>{

    private String[] sliderImages;
    private LayoutInflater layoutInflater;


    public ImageSliderAdapter(String[] sliderImages) {
        this.sliderImages = sliderImages;
    }

    @NonNull
    @Override
    public ImageSliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null){
            layoutInflater = LayoutInflater.from(parent.getContext());

        }
        ItemSliderImageBinding itemSliderImageBinding = DataBindingUtil.inflate(
                layoutInflater, R.layout.item_slider_image, parent, false
        );
        return new ImageSliderViewHolder(itemSliderImageBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageSliderViewHolder holder, int position) {
        holder.bindSliderImage(sliderImages[position]);

    }

    @Override
    public int getItemCount() {
        return sliderImages.length;
    }


    static class ImageSliderViewHolder extends RecyclerView.ViewHolder{

        private ItemSliderImageBinding itemSliderImageBinding;

        public ImageSliderViewHolder(ItemSliderImageBinding itemSliderImageBinding){
            super(itemSliderImageBinding.getRoot());
            this.itemSliderImageBinding = itemSliderImageBinding;
        }

        public void bindSliderImage(String imageURL){
            itemSliderImageBinding.setImageURL(imageURL);
        }
    }
}
