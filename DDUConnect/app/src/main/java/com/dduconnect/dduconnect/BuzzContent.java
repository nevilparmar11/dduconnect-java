package com.dduconnect.dduconnect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class BuzzContent extends AppCompatActivity {
    ProgressBar progressBar;
    Model post;
    ImageView vImage;
    TextView tvContent,tvTitel,tvDate,tvCategory;
    public static String id,title,category,date,imgUrl,content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buzz_content);


        Intent i=getIntent();
        id =i.getExtras().getString("id");
        title=i.getExtras().getString("title");
        date=i.getExtras().getString("date");
        imgUrl=i.getExtras().getString("img");
        category=i.getExtras().getString("catid");
        content=i.getExtras().getString("content");

        tvContent=findViewById(R.id.tv_content);
        tvTitel=findViewById(R.id.tv_title);
        tvCategory=findViewById(R.id.tv_cat);
        tvDate=findViewById(R.id.tv_date);
        vImage=findViewById(R.id.iv_icon);

        tvCategory.setText(category);
        tvDate.setText(date);
        tvTitel.setText(title.toUpperCase());
        tvContent.setText(content);
        progressBar=findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.GONE);
        Picasso.with(getApplicationContext()).load(imgUrl).into(vImage);
    }
}
