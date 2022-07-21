package com.androidnc.hoctap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidnc.hoctap.facebook.FacebookActivity;
import com.androidnc.hoctap.khoahoc.subject.SubjectActivity;
import com.androidnc.hoctap.tintuc.TinTucActivity;

public class MainActivity extends AppCompatActivity {

    ImageView imgKhoaHoc, imgBanDo, imgTinTuc, imgXaHoi;
    Toolbar toolbar;
    Intent intent;
    TextView tvUser;
    String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        imgKhoaHoc = findViewById(R.id.imgKhoaHoc);
        imgBanDo = findViewById(R.id.imgBanDo);
        imgTinTuc = findViewById(R.id.imgTinTuc);
        imgXaHoi = findViewById(R.id.imgXaHoi);

        intent = getIntent();
        user = intent.getStringExtra("user");
        try {
            tvUser.setText("Xin chào "+user);
        }catch (Exception e){
            e.printStackTrace();
        }

        imgKhoaHoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, SubjectActivity.class);
                startActivity(intent);
            }
        });
        imgBanDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });
        imgTinTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, TinTucActivity.class);
                startActivity(intent);
            }
        });
        imgXaHoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, FacebookActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.gioiThieu:
                startActivity(new Intent(getApplicationContext(),GioiThieuActivity.class));
                break;
            case R.id.dangXuat:
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}