package com.dduconnect.dduconnect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.dduconnect.dduconnect.interfaces.WPpostsByCategory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostsByCategory extends AppCompatActivity {
    public ArrayList<Model> list;
    public static HashMap<String,ArrayList<Model>> categoryMap=new HashMap<>();
    public ProgressBar progressBar;
    private PostByCategoryAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts_by_category);
        list = new ArrayList<Model>();
        progressBar =findViewById(R.id.progressbar3);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rc_view_cat);
        Intent i=getIntent();
        String catid =i.getExtras().getString("category");
        ImageView imageView=findViewById(R.id.no_internet);
        imageView.setVisibility(View.GONE);
        setTitle(Constants.CATEFORY_NAMES.get(catid));
        if(categoryMap.containsKey(catid)){
            list=categoryMap.get(catid);
            progressBar.setVisibility(View.GONE);
        }else {
            list=new ArrayList<>();
            if(Connections.isOnline(getApplicationContext()))
            {

                getRetrofit(catid);
            }
            else {
                progressBar.setVisibility(View.GONE);

                imageView.setVisibility(View.VISIBLE);
                Toast.makeText(this, "You are not connected to Internet", Toast.LENGTH_LONG).show();
            }

        }
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        adapter = new PostByCategoryAdapter(list, PostsByCategory.this);
        recyclerView.setAdapter(adapter);
    }


    public void getRetrofit(final String catId) {
        OkHttpClient okHttpClint=  new OkHttpClient.Builder().
                connectTimeout(3, TimeUnit.MINUTES).
                readTimeout(60,TimeUnit.SECONDS).
                writeTimeout(50,TimeUnit.SECONDS).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL).client(okHttpClint)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WPpostsByCategory service = retrofit.create(WPpostsByCategory.class);
        Call<List<WPPost>> call = service.getPostInfo("0",catId,"id,date,categories,link,title,excerpt.rendered,_embedded.wp:featuredmedia");

        call.enqueue(new Callback<List<WPPost>>() {
            @Override
            public void onResponse(Call<List<WPPost>> call, Response<List<WPPost>> response) {
                Log.e("mainactivyt", " response " + response.body());

                progressBar.setVisibility(View.GONE);
                String datevalue;
                String img;
                for (int i = 0; i < response.body().size(); i++) {
                    Log.e("main ", " title " + response.body().get(i).getTitle().getRendered() + " " +
                            response.body().get(i).getId());

                    String tempdetails = response.body().get(i).getExcerpt().getRendered().toString();
                    String title = response.body().get(i).getTitle().getRendered();
                    tempdetails = tempdetails.replace("<p>", "");
                    tempdetails = tempdetails.replace("</p>", "");
                    tempdetails = tempdetails.replace("[&hellip;]", "");
                    tempdetails = tempdetails.replace("&#8211;", "-");
                    tempdetails = tempdetails.replace("&#8220;", "\"");
                    tempdetails = tempdetails.replace("&#8221;", "\"");
                    tempdetails = tempdetails.replace("&#8216;", "\'");
                    tempdetails = tempdetails.replace("&#8217;", "\'");
                    tempdetails = tempdetails.replace("&nbsp;", " ");
                    tempdetails = tempdetails.replace("&#038;", "&");
                    title = title.replace("&#8211;", "-");
                    title = title.replace("&#8220;", "\"");
                    title = title.replace("&#8221;", "\"");
                    title = title.replace("&#8216;", "\'");
                    title = title.replace("&#8217;", "\'");
                    title = title.replace("&#038;", "&");
                    title = title.replace("&nbsp;", " ");
                    datevalue=response.body().get(i).getDate().substring(0,10);
                    if(response.body().get(i).getEmbedded()==null)
                    {
                        img="null";
                    }
                    else {
                        img=response.body().get(i).getEmbedded().getWpFeaturedmedia().get(0).getSourceUrl();
                    }


                    list.add(new Model(response.body().get(i).getId(),Model.IMAGE_TYPE,
                            title,
                            tempdetails,
                            img,datevalue,
                            response.body().get(i).getCategories(),
                            Constants.CATEFORY_NAMES.get(catId),
                            response.body().get(i).getLink()));

                }
               // saveData();

                categoryMap.put(catId,list);

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<WPPost>> call, Throwable t) {

            }
        });
    }

}
