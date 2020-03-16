package com.dduconnect.dduconnect;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ContactUs extends AppCompatActivity {
    private EditText name,email,message;
    private Button send;
    private Button insta,fb,twitter,linkedin,youtube;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        name = findViewById(R.id.contact_name);
        email = findViewById(R.id.contact_email);
        message = findViewById(R.id.contact_message);
        send = findViewById(R.id.contact_send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendEmail();

            }
        });
        insta = findViewById(R.id.btn_insta);
        insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.instagram.com/dduconnect/"));
                startActivity(browserIntent);
            }
        });
        fb = findViewById(R.id.btn_fb);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.facebook.com/dduconnect/"));
                startActivity(browserIntent);
            }
        });
        twitter=findViewById(R.id.twitter_btn);
        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://twitter.com/dduconnect"));
                startActivity(browserIntent);
            }
        });

        linkedin=findViewById(R.id.btn_linked);
        linkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.linkedin.com/company/ddu-connect/"));
                startActivity(browserIntent);
            }
        });
        youtube=findViewById(R.id.btn_youtube);
        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.youtube.com/channel/UCL_HeXMG8OnytZZtIRgkpQA"));
                startActivity(browserIntent);
            }
        });

    }

    private void sendEmail() {

        String[] TO = {
                "dduconnect@gmail.com"
        };

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);

        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Contact-App");
        emailIntent.putExtra(Intent.EXTRA_TEXT, name.getText() + "\n" + message.getText());
        try {
            startActivity(Intent.createChooser(emailIntent, "Send"));
            finish();

        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
