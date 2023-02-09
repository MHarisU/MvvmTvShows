package com.softvrbox.mvvmtvshows.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvShowDetail {

  /*  @SerializedName("id")
    private int id;*/

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("image_path")
    private String image_path;

    @SerializedName("pictures")
    private String[] pictures;

    @SerializedName("episodes")
    private List<Episodes> episodes;

   /* public int getId() {
        return id;
    }*/

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImage_path() {
        return image_path;
    }

    public String[] getPictures() {
        return pictures;
    }

    public List<Episodes> getEpisodes() {
        return episodes;
    }
}
