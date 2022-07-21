package com.androidnc.hoctap.khoahoc.subject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnc.hoctap.R;
import com.androidnc.hoctap.khoahoc.database.DbHelper;
import com.androidnc.hoctap.khoahoc.student.AddStudentActivity;
import com.androidnc.hoctap.khoahoc.student.StudentActivity;

public class UpdateSubjectActivity extends AppCompatActivity {

    EditText edTitle,edCreadit,edTime,edPlace;
    Button btnUpdate, btnCancel;
    DbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_subject);

        edTitle=findViewById(R.id.ed1);
        edCreadit=findViewById(R.id.ed2);
        edTime=findViewById(R.id.ed3);
        edPlace= findViewById(R.id.ed4);

        btnUpdate = findViewById(R.id.btnUpdate);
        btnCancel = findViewById(R.id.btnCancel);
        //lay du lieuu
        Intent intent = getIntent();
        // Lấy id
        int id = intent.getIntExtra("id",0);

        String title = intent.getStringExtra("title");
        int credit = intent.getIntExtra("credit",0);
        String time = intent.getStringExtra("time");
        String place = intent.getStringExtra("place");

        edTitle.setText(title);
        edTime.setText(time);
        edPlace.setText(place);
        edCreadit.setText(credit+"");

        db = new DbHelper(this);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialogupdate(id);
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UpdateSubjectActivity.this, SubjectActivity.class));
                finish();
            }
        });
    }

    private void Dialogupdate(int id) {
        Dialog dialog = new Dialog(this);

        dialog.setContentView(R.layout.dialog_update);

        dialog.setCanceledOnTouchOutside(false);

        Button btnYes= dialog.findViewById(R.id.btnYesUpdate);
        Button btnNo= dialog.findViewById(R.id.btnNoUpdate);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  subjecttitle = edTitle.getText().toString().trim();
                String credit = edCreadit.getText().toString().trim();
                String time = edTime.getText().toString().trim();
                String place = edPlace.getText().toString().trim();

                if (subjecttitle.equals("")||credit.equals("")||time.equals("")||place.equals("")){
                    Toast.makeText(UpdateSubjectActivity.this,"Bạn nhập chưa đủ thông tin",Toast.LENGTH_SHORT).show();
                }else {
                    Subject subject = new Subject(
                            edTitle.getText().toString(),
                            Integer.parseInt(edCreadit.getText().toString()),
                            edTime.getText().toString(),
                            edPlace.getText().toString()
                    );

//                    Subject subject = updatesubject();
                    db.UpdateSubject(subject,id);

                    Toast.makeText(UpdateSubjectActivity.this,"Sửa thành công",Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(UpdateSubjectActivity.this, SubjectActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
    }


    // luu du lieu edit cap nhat
    private Subject updatesubject(){
        String  subjecttitle = edTitle.getText().toString().trim();
        int credit =Integer.parseInt(edCreadit.getText().toString().trim());
        String time = edTime.getText().toString().trim();
        String place = edPlace.getText().toString().trim();

        Subject subject = new Subject(subjecttitle,credit,time,place);
        return subject;
    }
}