package com.hobarb.sountry.models;

import java.util.List;


public class UploadVideosModel {
    public UploadVideosModel(Long user_id, String user_gender, String video_url, String video_date, List<String> video_genres) {
        this.user_id = user_id;
        this.user_gender = user_gender;
        this.video_url = video_url;
        this.video_date = video_date;
        this.video_genres = video_genres;
    }

    private Long user_id;

    private String user_gender;

    private String video_url;

    private String video_date;

    public List<String> video_genres;
}
