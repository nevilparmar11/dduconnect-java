package com.dduconnect.dduconnect;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitArrayApi {

    @GET("/wp-json/wp/v2/posts/?_embed&per_page=5&fields=id,_links,title,date,categories,link")
    Call<List<WPPost>> getPostInfo();

}
