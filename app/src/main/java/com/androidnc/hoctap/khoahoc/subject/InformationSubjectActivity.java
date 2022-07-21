package com.androidnc.hoctap.khoahoc.subject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.androidnc.hoctap.R;

public class InformationSubjectActivity extends AppCompatActivity {

    TextView txtTitle,txtCredit,txtTime,txtPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_subject);


        txtTitle = findViewById(R.id.txtSubjectTitle);
        txtCredit = findViewById(R.id.txtSubjectCredit);
        txtTime = findViewById(R.id.txtSubjectTime);
        txtPlace = findViewById(R.id.txtSubjectPlace);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        int credit  =intent.getIntExtra("credit",0);
        String time = intent.getStringExtra("time");
        String place = intent.getStringExtra("place");

        txtTitle.setText(title);
        txtCredit.setText(credit+"");
        txtTime.setText(time);
        txtPlace.setText(place);
    }
}