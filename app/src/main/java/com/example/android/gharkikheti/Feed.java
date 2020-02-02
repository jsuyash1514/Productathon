package com.example.android.gharkikheti;

public class Feed {

        private String timestamp;
        private String video_description;
        private String videoUrl;

    public Feed(String timestamp, String video_description, String videoUrl) {
        this.timestamp = timestamp;
        this.video_description = video_description;
        this.videoUrl = videoUrl;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getVideo_description() {
        return video_description;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setVideo_description(String video_description) {
        this.video_description = video_description;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }
}
