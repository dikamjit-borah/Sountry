package com.hobarb.sountry.models;


public class VideosModel {


    private String video_id;
    private String video_url;
    private String video_creator_id;
    private String video_date_created;


    public String getVideoId() {
        return video_id;
    }

    public void setVideoId(String video_id) {
        this.video_id = video_id;
    }

    public String getVideoUrl() {
        return video_url;
    }

    public void setVideoUrl(String video_url) {
        this.video_url = video_url;
    }

    public String getVideoCreatorId() {
        return video_creator_id;
    }

    public void setVideoCreatorId(String video_creator_id) {
        this.video_creator_id = video_creator_id;
    }

    public String getVideoDateCreated() {
        return video_date_created;
    }

    public void setVideoDateCreated(String video_date_created) {
        this.video_date_created = video_date_created;
    }

}
