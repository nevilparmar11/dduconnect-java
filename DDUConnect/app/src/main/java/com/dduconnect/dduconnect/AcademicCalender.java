package com.dduconnect.dduconnect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class AcademicCalender extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_calender);
        PDFView p = (PDFView)findViewById(R.id.pdfView);
        p.fromAsset("academiccalender.pdf").load();
    }
}
