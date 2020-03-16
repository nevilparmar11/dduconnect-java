package com.dduconnect.dduconnect;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.dduconnect.dduconnect.interfaces.GetTeamService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MembersActivity extends AppCompatActivity {
    private MeetOurTeamAdapter meetOurTeamAdapter;
    private RecyclerView recyclerView;
    ProgressBar progressBar;
    private RecyclerView.LayoutManager layoutManager;
    private static ArrayList<AllTeamModel> list;
    int c;
    ImageView no_internet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members);

        progressBar = (ProgressBar) findViewById(R.id.progressbar_members);
        progressBar.setVisibility(View.VISIBLE);
        no_internet=findViewById(R.id.no_internet3);
        no_internet.setVisibility(View.VISIBLE);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if(Connections.isOnline(getApplicationContext())){
            no_internet.setVisibility(View.GONE);
            list = new ArrayList<AllTeamModel>();
//
            myRetrofit();


            switch(getIntent().getExtras().getInt("index")){
                case 0:
                    c=0;
                    break;
                case 1:
                    c=1;
                    break;
                case 2:
                    c=2;
                    break;
                case 3:
                    c=3;
                    break;
                case 4:
                    c=4;
                    break;
                case 5:
                    c=5;
                    break;
                case 6:
                    c=6;
                    break;

            }
        }else
            {
            progressBar.setVisibility(View.GONE);
            }



    }



    private void myRetrofit() {
        OkHttpClient okHttpClint=  new OkHttpClient.Builder().
                connectTimeout(3, TimeUnit.MINUTES).
                readTimeout(60,TimeUnit.SECONDS).
                writeTimeout(50,TimeUnit.SECONDS).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.KIRAN_URL).client(okHttpClint)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetTeamService service = retrofit.create(GetTeamService.class);

        Call<List<AllTeamModel>> call = service.getAllTeams();
        call.enqueue(new Callback<List<AllTeamModel>>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onResponse(Call<List<AllTeamModel>> call, Response<List<AllTeamModel>> response) {
                progressBar.setVisibility(View.INVISIBLE);

                for(int i=0;i<response.body().size();i++){
                    String title = response.body().get(c).getTitle().toString();
                    String memberscount = response.body().get(c).getMembersCount().toString();
                    List<Member> memberList = response.body().get(c).getMembers();
                    list.add(new AllTeamModel(title,memberscount,memberList));
                }
                Toast.makeText(MembersActivity.this,list.get(3).getTitle().toString(),1);


                generateDataList(list);
            }

            @Override
            public void onFailure(Call<List<AllTeamModel>> call, Throwable t) {

                Toast.makeText(MembersActivity.this,"Something Is Wrong",Toast.LENGTH_LONG);
                progressBar.setVisibility(View.INVISIBLE);
            }
        });


    }

    private void generateDataList(ArrayList<AllTeamModel> body) {

        recyclerView = (RecyclerView) findViewById(R.id.recyclerviewmembers);
        meetOurTeamAdapter = new MeetOurTeamAdapter(body, MembersActivity.this,c);
        layoutManager = new LinearLayoutManager(MembersActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(meetOurTeamAdapter);

    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
