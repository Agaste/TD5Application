package com.example.antoine.td5.models;

import com.google.api.client.util.DateTime;

/**
 * Created by Antoine on 26/02/2016.
 */
public class YouTubeVideo {
    private String title;
    private String description;
    private DateTime publishedAt;
    private String id;
    private String thumbnailsURL;

    public String getTitle() {return title;}
    public String getDescription() {return description;}
    public DateTime getPublishedAt() {return publishedAt;}
    public String getId() {return id;}
    public String getThumbnailsURL() {return thumbnailsURL;}

    public void setTitle(String title) {this.title = title;}
    public void setDescription(String description) {this.description=description;}
    public void setPublishedAt(DateTime publishedAt) {this.publishedAt=publishedAt;}
    public void setId(String id) {this.id=id;}
    public void setThumbnailsURL(String thumbnailsURL) {this.thumbnailsURL=thumbnailsURL;}

}
