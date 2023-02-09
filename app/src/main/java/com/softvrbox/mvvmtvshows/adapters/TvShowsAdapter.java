package com.softvrbox.mvvmtvshows.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.softvrbox.mvvmtvshows.DataBinderMapperImpl;
import com.softvrbox.mvvmtvshows.R;
import com.softvrbox.mvvmtvshows.databinding.ItemTvViewBinding;
import com.softvrbox.mvvmtvshows.listeners.TvShowListener;
import com.softvrbox.mvvmtvshows.models.TvShow;

import java.util.List;

public class TvShowsAdapter extends RecyclerView.Adapter<TvShowsAdapter.TvShowViewHolder>{

    private List<TvShow> tvShows;
    private LayoutInflater layoutInflater;
    TvShowListener tvShowListener;

    public TvShowsAdapter(List<TvShow> tvShows, TvShowListener tvShowListener) {
        this.tvShows = tvShows;
        this.tvShowListener = tvShowListener;
    }

    @NonNull
    @Override
    public TvShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null){
            layoutInflater = LayoutInflater.from(parent.getContext());

        }
        ItemTvViewBinding tvViewBinding = DataBindingUtil.inflate(
                layoutInflater, R.layout.item_tv_view, parent, false
        );
        return new TvShowViewHolder(tvViewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowViewHolder holder, int position) {
        holder.bindTvShows(tvShows.get(position));
    }

    @Override
    public int getItemCount() {
        return tvShows.size();
    }

     class TvShowViewHolder extends RecyclerView.ViewHolder{

        private ItemTvViewBinding itemTvViewBinding;

        public TvShowViewHolder(ItemTvViewBinding itemTvViewBinding){
            super(itemTvViewBinding.getRoot());
            this.itemTvViewBinding = itemTvViewBinding;
        }

        public void bindTvShows(TvShow tvShow){
            itemTvViewBinding.setTvShow(tvShow);
            itemTvViewBinding.executePendingBindings();
            itemTvViewBinding.getRoot().setOnClickListener(view -> tvShowListener.onTvShowClicked(tvShow));
        }
    }
}
