package com.dduconnect.dduconnect;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Favourite extends AppCompatActivity {
    ConstraintLayout empty_list_fav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.fav_rec);

        empty_list_fav=findViewById(R.id.error_empty_fav);
        empty_list_fav.setVisibility(View.VISIBLE);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(Favourite.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        FavouriteHandler.loadFavouriteList(getApplicationContext());
        if(FavouriteHandler.favouriteList.size() != 0) {
            empty_list_fav.setVisibility(View.GONE);
          PostByCategoryAdapter adapter = new PostByCategoryAdapter(FavouriteHandler.favouriteList, this);
            recyclerView.setAdapter(adapter);
        }
    }

}
