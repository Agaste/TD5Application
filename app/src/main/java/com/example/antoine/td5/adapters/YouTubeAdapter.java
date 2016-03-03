package com.example.antoine.td5.adapters;

import android.content.Context;
import android.util.Log;

import com.example.antoine.td5.R;
import com.example.antoine.td5.models.YouTubeVideo;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Antoine on 26/02/2016.
 */
public class YouTubeAdapter {
    private YouTube youtube;
    private YouTube.Search.List query;

    // Your developer key goes here
    public static final String KEY
            = "AIzaSyCS5JjXJB7bGpTtTQpslhxjVpwv48It9oc";

    public YouTubeAdapter(Context context) {
        youtube = new YouTube.Builder(new NetHttpTransport(),
                new JacksonFactory(), new HttpRequestInitializer() {
            @Override
            public void initialize(HttpRequest hr) throws IOException {}
        }).setApplicationName(context.getString(R.string.app_name)).build();

        try{
            query = youtube.search().list("id,snippet");
            query.setKey(KEY);
            query.setType("video");
            query.setFields("items(id/videoId,snippet/title,snippet/description,snippet/thumbnails/default/url)");
        }catch(IOException e){
            Log.d("YC", "Could not initialize: " + e);
        }
    }

    public List<YouTubeVideo> search(String keywords){
        query.setQ(keywords);
        try{
            SearchListResponse response = query.execute();
            List<SearchResult> results = response.getItems();

            List<YouTubeVideo> items = new ArrayList<YouTubeVideo>();
            for(SearchResult result:results){
                YouTubeVideo item = new YouTubeVideo();
                item.setTitle(result.getSnippet().getTitle());
                item.setDescription(result.getSnippet().getDescription());
                item.setThumbnailsURL(result.getSnippet().getThumbnails().getDefault().getUrl());
                item.setId(result.getId().getVideoId());
                item.setPublishedAt(result.getSnippet().getPublishedAt());
                items.add(item);
            }
            return items;
        }catch(IOException e){
            Log.d("YC", "Could not search: "+e);
            return null;
        }
    }
}
