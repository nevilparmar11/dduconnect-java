package com.dduconnect.dduconnect.interfaces;
import com.dduconnect.dduconnect.WPPost;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WPbuzzContent {
    @GET("/dduconnectposts/buzz.json")
    Call<List<WPPost>> getPostInfo();
}
