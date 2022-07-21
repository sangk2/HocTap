package com.androidnc.hoctap.khoahoc.student;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.androidnc.hoctap.R;
import com.androidnc.hoctap.khoahoc.database.DbHelper;

public class UpdateStudentActivity extends AppCompatActivity {

    EditText edtTextUpdateName,edtTextUpdateCode,edtTextUpdateBirthday;
    RadioButton rdMale,rdFeMale;
    Button btnUpdateStudent, btnCancel;
    DbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);

        edtTextUpdateBirthday = findViewById(R.id.EditTextStudentBirthdayUpdate);
        edtTextUpdateCode = findViewById(R.id.EditTextStudentCodeUpdate);
        edtTextUpdateName = findViewById(R.id.EditTextStudentNameUpdate);

        rdMale = findViewById(R.id.radiobuttonMaleUpdate);
        rdFeMale = findViewById(R.id.radiobuttonFeMaleUpdate);
        btnUpdateStudent = findViewById(R.id.btnUpdateStudent);
        btnCancel = findViewById(R.id.btnCancel);

        Intent intent =getIntent();
        int id = intent.getIntExtra("id",0);
        String name = intent.getStringExtra("name");
        String sex = intent.getStringExtra("sex");
        String code = intent.getStringExtra("code");
        String birtday = intent.getStringExtra("birtday");
        int id_subject = intent.getIntExtra("id_subject",0);

        edtTextUpdateName.setText(name);
        edtTextUpdateCode.setText(code);
        edtTextUpdateBirthday.setText(birtday);

        if (sex.equals("Male")){
            rdMale.setChecked(true);
            rdFeMale.setChecked(false);
        }
        else {
            rdFeMale.setChecked(true);
            rdMale.setChecked(false);
        }
        db = new DbHelper(this);
        btnUpdateStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUpdate(id,id_subject);
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UpdateStudentActivity.this, StudentActivity.class));
                finish();
            }
        });

    }

    private void DialogUpdate(int id,int id_subject) {
        Dialog dialog = new Dialog(this);

        dialog.setContentView(R.layout.dialog_update);

        dialog.setCanceledOnTouchOutside(false);

        Button btnyes = dialog.findViewById(R.id.btnYesUpdate);
        Button btnno = dialog.findViewById(R.id.btnNoUpdate);

        btnyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtTextUpdateName.getText().toString().trim();
                String code = edtTextUpdateCode.getText().toString().trim();
                String birthday = edtTextUpdateBirthday.getText().toString().trim();

                Student student =createstudent();

                if (name.equals("")||code.equals("")||birthday.equals("")){
                    Toast.makeText(UpdateStudentActivity.this,"Bạn phải nhập đủ thông tin",Toast.LENGTH_SHORT).show();
                }
                else {
                    db.updateStudent(student,id);
                    Intent intent= new Intent(UpdateStudentActivity.this,StudentActivity.class);
                    intent.putExtra("id_subject",id_subject);
                    Toast.makeText(UpdateStudentActivity.this,"Sửa thành công",Toast.LENGTH_SHORT).show();

                    startActivity(intent);
                    finish();

                }
            }
        });
        btnno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
    }

    private Student createstudent(){
        String name = edtTextUpdateName.getText().toString().trim();
        String code = edtTextUpdateCode.getText().toString().trim();
        String birthday = edtTextUpdateBirthday.getText().toString().trim();
        String sex = "";
        if (rdMale.isChecked()){
            sex= "Nam";
        }else {
            sex = "Nữ";
        }
        Student student = new Student(name,sex,code,birthday);
        return student;
    }
}