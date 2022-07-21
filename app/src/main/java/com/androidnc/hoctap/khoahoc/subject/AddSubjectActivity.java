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

public class AddSubjectActivity extends AppCompatActivity {

    Button btnSave, btnCancel;
    EditText edtSubjectTitle,edtSubjectCredit,edtSubjectTime,edtSubjectplace;
    DbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);

        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);
        edtSubjectCredit = findViewById(R.id.EditTextSubjectCredit);
        edtSubjectTitle = findViewById(R.id.EditTextSubjectTitle);
        edtSubjectTime = findViewById(R.id.EditTextSubjectTime);
        edtSubjectplace = findViewById(R.id.EditTextSubjectPlace);

        db = new DbHelper(this);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogAdd();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddSubjectActivity.this,SubjectActivity.class));
                finish();
            }
        });
    }

    private void DialogAdd() {
        Dialog dialog = new Dialog(this);

        dialog.setContentView(R.layout.dialog_add);

        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = dialog.findViewById(R.id.btnYesAdd);
        Button btnNo = dialog.findViewById(R.id.btnNoAdd);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subjecttitle = edtSubjectTitle.getText().toString().trim();
                String credit =edtSubjectCredit.getText().toString().trim();
                String time = edtSubjectTime.getText().toString().trim();
                String place = edtSubjectplace.getText().toString().trim();

                //neu du lieu chua nhap day du
                if (subjecttitle.equals("")||credit.equals("")||time.equals("")||place.equals("")){
                    Toast.makeText(AddSubjectActivity.this,"Bạn nhập chưa đủ thông tin",Toast.LENGTH_SHORT).show();
                }
                else {
                    //gan gia tri cho doi tuong nhap vao
                    Subject subject = new Subject(
                            edtSubjectTitle.getText().toString(),
                            Integer.parseInt(edtSubjectCredit.getText().toString()),
                            edtSubjectTime.getText().toString(),
                            edtSubjectplace.getText().toString()
                    );

//                    Subject subject = CreatSubject();

                    //add trong database
                    db.AddSubject(subject);
                    Toast.makeText(AddSubjectActivity.this,"Thêm thành công",Toast.LENGTH_SHORT).show();

                    //them thanh cong chuyen giao dien
                    Intent intent= new Intent(AddSubjectActivity.this, SubjectActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        //neu khong them nua
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
    }
    private Subject CreatSubject(){

        String subjecttitle = edtSubjectTitle.getText().toString().trim();
        int credit = Integer.parseInt(edtSubjectCredit.getText().toString().trim());
        String time = edtSubjectTime.getText().toString().trim();
        String place = edtSubjectplace.getText().toString().trim();
        Subject subject = new Subject(subjecttitle,credit,time,place);
        return subject;
    }
}