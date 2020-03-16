package com.dduconnect.dduconnect.interfaces;

import com.dduconnect.dduconnect.PostContent;
import com.dduconnect.dduconnect.WPPost;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WPpostContent {

    @GET("/wp-json/wp/v2/posts/")
    Call<List<WPPost>> getPostInfo(@Query("include[]")String query);

}