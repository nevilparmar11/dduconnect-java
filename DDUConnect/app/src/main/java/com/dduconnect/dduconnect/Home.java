package com.dduconnect.dduconnect;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.dduconnect.dduconnect.interfaces.WPbuzzContent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    LinearLayout techlayout,nontechlayout,morelayout;
    private AdapterRecyclerView adapter;
    private AdapterBuzzView buzzAdapter;
    private RecyclerView recyclerView, buzzRecyvlerView;
    public static ArrayList<Model> list,buzzlist;
    public static List<WPPost> mListPost;

    ProgressBar progressBar;
    int techflag=1;
    int nontechflag=1;
    int moreflag=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       // toolbar.setLogo(R.drawable.toolbar_logo);
      //  toolbar.setSubtitle("Transcending Ingenuity");

        FavouriteHandler.loadFavouriteList(getApplicationContext());
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Connections.isOnline(getApplicationContext())) {
                    progressBar = (ProgressBar) findViewById(R.id.progressBar);
                    progressBar.setVisibility(View.VISIBLE );


                    buzzlist=new ArrayList<Model>();
                    buzzRecyvlerView=findViewById(R.id.buzz_view_rc);
                    buzzRecyvlerView.setLayoutManager(new LinearLayoutManager(Home.this,LinearLayoutManager.VERTICAL,false));
                    getBuzz();
                    buzzAdapter=new AdapterBuzzView(buzzlist,Home.this);
                    buzzRecyvlerView.setAdapter(buzzAdapter);

                    LinearLayoutManager mLayoutManager = new LinearLayoutManager(Home.this, LinearLayoutManager.HORIZONTAL, false);
                    recyclerView.setLayoutManager(mLayoutManager);
                    list = new ArrayList<Model>();
                    getRetrofit();
                    adapter = new AdapterRecyclerView(list, Home.this);
                    recyclerView.setAdapter(adapter);


                }else{
                    Toast.makeText(Home.this, "You are not connected to Internet", Toast.LENGTH_LONG).show();
                    //Snackbar.make(view, "You are not connected to Internet", Snackbar.LENGTH_LONG)
                        //    .setAction("Action", null).show();
                }
            }
        });



        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        ImageView past_year_paper_img=findViewById(R.id.past_year_paper);
        past_year_paper_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Connections.isOnline(getApplicationContext())) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://drive.google.com/drive/folders/1nhXCmxn7HTWP6QFCi59KP9D0JreAB3Sw")));
                }else{
                    Toast.makeText(Home.this, "You are not connected to Internet", Toast.LENGTH_SHORT).show();
                }
            }
        });
        ImageView tech_img=findViewById(R.id.image_tech);
        ImageView non_tech_img=findViewById(R.id.image_non_tech);
        ImageView more_img=findViewById(R.id.image_more);
        techlayout=findViewById(R.id.tech_layout);
        nontechlayout=findViewById(R.id.non_tech_layout);
        morelayout=findViewById(R.id.more_layout);
        non_tech_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nontechflag==1) {
                    expand(nontechlayout,750);
                    //techlayout.setVisibility(View.VISIBLE);
                    //  techlayout.setMinimumWidth(1);
                    nontechflag*=-1;
                }else
                {
                    collapse(nontechlayout);
                    nontechflag*=-1;
                }

            }
        });
        more_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(moreflag==1) {
                    expand(morelayout,450);
                    //techlayout.setVisibility(View.VISIBLE);
                    //  techlayout.setMinimumWidth(1);
                    moreflag*=-1;
                }else
                {
                    collapse(morelayout);
                    moreflag*=-1;
                }
//
            }
        });
        tech_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(techflag==1) {
                    expand(techlayout,350);
                    //techlayout.setVisibility(View.VISIBLE);
                  //  techlayout.setMinimumWidth(1);
                    techflag*=-1;
                }else
                {
                    collapse(techlayout);
                    techflag*=-1;
                }
//
            }
        });
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        recyclerView = (RecyclerView) findViewById(R.id.rc_view_top5);


       buzzRecyvlerView=findViewById(R.id.buzz_view_rc);
        buzzRecyvlerView.setLayoutManager(new LinearLayoutManager(Home.this,LinearLayoutManager.VERTICAL,false));
        if(buzzlist==null) {
            if(Connections.isOnline(getApplicationContext()))
            {
                buzzlist = new ArrayList<Model>();
                getBuzz();
                buzzAdapter = new AdapterBuzzView(buzzlist, this);
                buzzRecyvlerView.setAdapter(buzzAdapter);

            }else
            {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(Home.this, "You are not connected to Internet", Toast.LENGTH_LONG).show();
            }

        }else {
            buzzAdapter = new AdapterBuzzView(buzzlist, this);
            buzzRecyvlerView.setAdapter(buzzAdapter);
        }
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(Home.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        if(list==null) {
            if(Connections.isOnline(getApplicationContext())) {
                progressBar.setVisibility(View.VISIBLE);
                list = new ArrayList<Model>();
                getRetrofit();
                adapter = new AdapterRecyclerView(list, Home.this);
                recyclerView.setAdapter(adapter);
            }else {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(Home.this, "You are not connected to Internet", Toast.LENGTH_LONG).show();
            }
        }else {
            progressBar.setVisibility(View.GONE);
            adapter = new AdapterRecyclerView(list, Home.this);
            recyclerView.setAdapter(adapter);
        }

//        RecyclerView buzzview=findViewById(R.id.rc_view_buzz);
//        LinearLayoutManager buzzLayoutManager = new LinearLayoutManager(Home.this, LinearLayoutManager.VERTICAL, false);
//        buzzview.setLayoutManager(buzzLayoutManager);
//        PostByCategoryAdapter ad=new PostByCategoryAdapter(list,this);
//        buzzview.setAdapter(ad);

    }

    public void getRetrofit() {
        OkHttpClient okHttpClint=  new OkHttpClient.Builder().
                connectTimeout(3, TimeUnit.MINUTES).
                readTimeout(60,TimeUnit.SECONDS).
                writeTimeout(50,TimeUnit.SECONDS).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL).client(okHttpClint)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitArrayApi service = retrofit.create(RetrofitArrayApi.class);
        Call<List<WPPost>> call = service.getPostInfo();

        call.enqueue(new Callback<List<WPPost>>() {
            @Override
            public void onResponse(Call<List<WPPost>> call, Response<List<WPPost>> response) {
                Log.e("HomeActivity", " response " + response.body());
                mListPost = response.body();
                progressBar.setVisibility(View.GONE);
                String datevalue;
                String img;
                for (int i = 0; i < response.body().size(); i++) {
                    Log.e("main ", " title " + response.body().get(i).getTitle().getRendered() + " " +
                            response.body().get(i).getId());

                    String title = response.body().get(i).getTitle().getRendered();

                    title = title.replace("&#8211;", "-");
                    title = title.replace("&#8220;", "\"");
                    title = title.replace("&#8221;", "\"");
                    title = title.replace("&#8216;", "\'");
                    title = title.replace("&#8217;", "\'");
                    title = title.replace("&#038;", "&");
                    title = title.replace("&nbsp;", " ");
                   // datevalue=response.body().get(i).getDate().substring(0,10);
                    if(response.body().get(i).getEmbedded()==null)
                    {
                        img="null";
                    }
                    else {
                        img=response.body().get(i).getEmbedded().getWpFeaturedmedia().get(0).getSourceUrl();
                    }


                    list.add(new Model(response.body().get(i).getId(),Model.IMAGE_TYPE,
                            title,
                            "tempdetails",
                            img,response.body().get(i).getDate().substring(0,10),
                    response.body().get(i).getCategories(),
                            response.body().get(i).getEmbedded().getWpTerms().get(0).get(0).getName(),
                            response.body().get(i).getLink()));

                }
               // saveData();
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<WPPost>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_feedback) {
            Intent meet = new Intent(Home.this,FeedbackActivity.class);
            startActivity(meet);
        }else if(id == R.id.action_t_and_c){
            Intent i=new Intent(Home.this, PrivacyPolicy.class);
            startActivity(i);

        }else if(id == R.id.action_copyrights){
            Intent meet = new Intent(Home.this,AboutApp.class);
            startActivity(meet);

        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_gallery) {
            Intent meet = new Intent(Home.this, PostWebView.class);
            meet.putExtra("itemlink","https://dduconnect.in/19th-convocation-1/");
            meet.putExtra("allowed","true");
            startActivity(meet);

        } else if (id == R.id.nav_favourite) {
            Intent intent = new Intent(Home.this, Favourite.class);
            startActivity(intent);
        } else if (id == R.id.nav_academic_calender) {
            Intent intent = new Intent(Home.this, AcademicCalender.class);
            startActivity(intent);

        } else if (id == R.id.nav_meet_team) {
            Intent meet = new Intent(Home.this,MeetOurTeam.class);
            startActivity(meet);

        } else if (id == R.id.nav_contact) {
            Intent meet = new Intent(Home.this,ContactUs.class);
            startActivity(meet);

        } else if (id == R.id.nav_about_us) {
            Intent i = new Intent(Home.this, AboutUs.class);
            startActivity(i);

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void categoryCall(View view){
        int id=view.getId();
        Intent intent=new Intent(Home.this,PostsByCategory.class);
        if(id==R.id.tech_it_easy){
            intent.putExtra("category","79");

        }else if(id==R.id.dentistry_around_the_globe){
            intent.putExtra("category","111");

        }else if(id==R.id.pharmacy_then_now){
            intent.putExtra("category","129");

        }else if(id==R.id.connect_ions){
            intent.putExtra("category","82");

        }else if(id==R.id.fiction){
            intent.putExtra("category","83");

        }else if(id==R.id.open_letter){
            intent.putExtra("category","90");

        }else if(id==R.id.verses){
            intent.putExtra("category","91");

        }else if(id==R.id.writers_lounge){
            intent.putExtra("category","81");

        }else if(id==R.id.alumni_speaks){
            intent.putExtra("category","133");

        }else if(id==R.id.ddu_speaks){
            intent.putExtra("category","134");

        }else if(id==R.id.hall_of_fame){
            intent.putExtra("category","135");

        }else if(id==R.id.interview){
            intent.putExtra("category","132");

        }
        startActivity(intent);
    }
    public static void expand(final View v,int width) {
        int matchParentMeasureSpec = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY);

        int wrapContentMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        v.measure(matchParentMeasureSpec, wrapContentMeasureSpec);
        final int targetHeight = v.getMeasuredWidth();

        // Older versions of android (pre API 21) cancel animations for views with a height of 0.
        v.getLayoutParams().width = 1;

        v.setVisibility(View.VISIBLE);
        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().width = interpolatedTime == 1
                        ? LinearLayout.LayoutParams.WRAP_CONTENT
                        : (int)(targetHeight * (interpolatedTime));
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // Expansion speed of 1dp/ms
        a.setDuration((int)(targetHeight / v.getContext().getResources().getDisplayMetrics().density)+200);
        v.startAnimation(a);

    }

    public static void collapse(final View v) {
        final int initialHeight = v.getMeasuredWidth();

        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if(interpolatedTime == 1){
                    v.setVisibility(View.GONE);
                }else{
                    v.getLayoutParams().width = initialHeight - (int)(initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // Collapse speed of 1dp/ms
        a.setDuration((int)(initialHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }

public void fromEditorsdesk(View view){
        if(view.getId() == R.id.btn_editors_desk){
            Intent i=new Intent(Home.this,EditorsDesk.class);
            startActivity(i);
        }
}

    // buzz
    public void getBuzz() {
        OkHttpClient okHttpClint=  new OkHttpClient.Builder().
                connectTimeout(3, TimeUnit.MINUTES).
                readTimeout(60,TimeUnit.SECONDS).
                writeTimeout(50,TimeUnit.SECONDS).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.NEVIL_URL).client(okHttpClint)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WPbuzzContent service = retrofit.create(WPbuzzContent.class);
        Call<List<WPPost>> call = service.getPostInfo();

        call.enqueue(new Callback<List<WPPost>>() {
            @Override
            public void onResponse(Call<List<WPPost>> call, Response<List<WPPost>> response) {
                Log.e("HomeActivity", " response " + response.body());
                mListPost = response.body();
                String datevalue;
                String img;
                for (int i = 0; i < response.body().size(); i++) {
                    Log.e("main ", " title " + response.body().get(i).getTitle().getRendered() + " " +
                            response.body().get(i).getId());

                    String title = response.body().get(i).getTitle().getRendered();

                    title = title.replace("&#8211;", "-");
                    title = title.replace("&#8220;", "\"");
                    title = title.replace("&#8221;", "\"");
                    title = title.replace("&#8216;", "\'");
                    title = title.replace("&#8217;", "\'");
                    title = title.replace("&#038;", "&");
                    title = title.replace("&nbsp;", " ");
                    // datevalue=response.body().get(i).getDate().substring(0,10);

                        img=response.body().get(i).getLink();


                    buzzlist.add(new Model(title,
                            img,
                            response.body().get(i).getDate(),
                            1,
                            response.body().get(i).getContent().getRendered()));

                }
                // saveData();
                buzzAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<WPPost>> call, Throwable t) {

            }
        });
    }

}
