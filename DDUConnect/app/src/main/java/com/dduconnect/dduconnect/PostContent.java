package com.dduconnect.dduconnect;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.dduconnect.dduconnect.interfaces.WPpostContent;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
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

public class PostContent extends AppCompatActivity {
    ProgressBar progressBar;
    Model post;
    ImageView vImage;
    TextView tvContent,tvTitel,tvDate,tvCategory;
    public static HashMap<String,Model> postMap=new HashMap<>();
    public static String id,title,category,date,imgUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_content);

        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setTitle("Content");
        Intent i=getIntent();
        id =i.getExtras().getString("id");
        title=i.getExtras().getString("title");
        date=i.getExtras().getString("date");
        imgUrl=i.getExtras().getString("img");
        category=i.getExtras().getString("catid");

        tvContent=findViewById(R.id.tv_content);
        tvTitel=findViewById(R.id.tv_title);
        tvCategory=findViewById(R.id.tv_cat);
        tvDate=findViewById(R.id.tv_date);
        vImage=findViewById(R.id.iv_icon);
        progressBar = findViewById(R.id.progressBar2);
        tvCategory.setText(category);
        tvDate.setText(date);
        tvTitel.setText(title.toUpperCase());
        if(postMap.containsKey(id))
        {
            progressBar.setVisibility(View.GONE);
            tvContent.setText(Html.fromHtml(Html.fromHtml(postMap.get(id).content).toString()));
            ImageView imageView=findViewById(R.id.by_img);
            Picasso.with(getApplicationContext()).load(postMap.get(id).Image).into(imageView);

        }else {

            getRetrofit2();
        }
        Picasso.with(getApplicationContext()).load(imgUrl).into(vImage);


    }


    public void getRetrofit2() {
        OkHttpClient okHttpClint=  new OkHttpClient.Builder().
                connectTimeout(3, TimeUnit.MINUTES).
                readTimeout(60,TimeUnit.SECONDS).
                writeTimeout(50,TimeUnit.SECONDS).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL).client(okHttpClint)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WPpostContent service = retrofit.create(WPpostContent.class);
        Call<List<WPPost>> call = service.getPostInfo(id);

        call.enqueue(new Callback<List<WPPost>>() {
            @Override
            public void onResponse(Call<List<WPPost>> call, Response<List<WPPost>> response) {
                Log.e("HomeActivity", " response " + response.body());
                progressBar.setVisibility(View.GONE);
                String datevalue;
                String img;
                String mHtmlString=response.body().get(0).getContent().getRendered();
                Spanned con=(Html.fromHtml(Html.fromHtml(mHtmlString).toString()));
                String all =mHtmlString; // shortened it
                String s = "<img src=\"";
                int ix = all.indexOf(s)+s.length();
                String url=all.substring(ix, all.indexOf("\"", ix+1));
                System.out.println("url="+url);
               // ConstraintLayout constraintLayout=findViewById(R.id.written_by_layout);
                //constraintLayout.setVisibility(View.VISIBLE);
                ImageView imageView=findViewById(R.id.by_img);
              //  TextView byTv=findViewById(R.id.by_name);
                //byTv.setText(Html.fromHtml(Html.fromHtml(all.substring(ix+url.length()-10,all.length()-1)).toString()));
                Picasso.with(getApplicationContext()).load(url).into(imageView);
                Log.e("HomeActivity", " response:: " + url);
                tvContent.setText(con);

                postMap.put(id,new Model("",url,"",0,mHtmlString));
            }

            @Override
            public void onFailure(Call<List<WPPost>> call, Throwable t) {

            }
        });
    }


@Override
public boolean onSupportNavigateUp(){
    finish();
    return true;
}
}
