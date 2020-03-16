package com.dduconnect.dduconnect;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;



public class MeetOurTeam extends AppCompatActivity {

    boolean ExpandedActionBar = true;

    private Button editorial, designing, event, finance, photography, webdev, supporting;
    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meet_our_team);

        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar_meet);
        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_meet);
//        assert getSupportActionBar() != null;
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setTitle("PostWebView");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_meet);
        setSupportActionBar(toolbar);

        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        MyOnClick();


        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {

                if (Math.abs(i) > 150) {
                    ExpandedActionBar = false;
                    collapsingToolbarLayout.setTitle("Meet Our Team");
                    collapsingToolbarLayout.setCollapsedTitleTextColor(Color.LTGRAY);
//                    Color.parseColor("#5c3434")
                    invalidateOptionsMenu();
                } else {
                    ExpandedActionBar = true;
                    collapsingToolbarLayout.setTitle("   ");
                    invalidateOptionsMenu();
                }
            }
        });

    }

    private void MyOnClick() {
        editorial = findViewById(R.id.btn_editorial);
        editorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                index = 1;
                Intent members = new Intent(getApplicationContext(), MembersActivity.class);
                members.putExtra("index",1);

                startActivity(members);


            }
        });

        designing = findViewById(R.id.btn_designing);
        designing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                index =0;
                Intent members = new Intent(getApplicationContext(), MembersActivity.class);
                members.putExtra("index",0);

                startActivity(members);
            }
        });

        event = findViewById(R.id.btn_event);
        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent members = new Intent(getApplicationContext(), MembersActivity.class);

                members.putExtra("index",2);
                startActivity(members);

            }
        });

        finance = findViewById(R.id.btn_finance);
        finance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent members = new Intent(getApplicationContext(), MembersActivity.class);

                members.putExtra("index",3);
                startActivity(members);

            }
        });

        photography = findViewById(R.id.btn_photography);
        photography.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent members = new Intent(getApplicationContext(), MembersActivity.class);

                members.putExtra("index",4);
                startActivity(members);
            }
        });
        webdev = findViewById(R.id.btn_webdev);
        webdev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent members = new Intent(getApplicationContext(), MembersActivity.class);

                members.putExtra("index",5);
                startActivity(members);

            }
        });
        supporting = findViewById(R.id.btn_supporting);
        supporting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent members = new Intent(getApplicationContext(), MembersActivity.class);

                members.putExtra("index",6);
                startActivity(members);

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}


