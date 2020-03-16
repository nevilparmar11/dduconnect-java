package com.dduconnect.dduconnect;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        final Intent back=new Intent(this,WelcomeActivity.class);

        Thread timer =new Thread(){
            public void run(){
                try{
                    sleep(1000);

                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                finally {
                    // We n rmally won't show the welcome slider again in real app
                    // but this is for testin


                    startActivity(back);

                    finish();
                }
            }
        };
        timer.start();
    }
}

