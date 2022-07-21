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
import com.androidnc.hoctap.khoahoc.subject.AddSubjectActivity;
import com.androidnc.hoctap.khoahoc.subject.SubjectActivity;

public class AddStudentActivity extends AppCompatActivity {

    Button btnSave, btnCancel;
    EditText editextStudentName,editextstudentcode,edittextdateofbirth;
    RadioButton radioButtonMale,RadioButtonFeMale;
    DbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);
        edittextdateofbirth = findViewById(R.id.EditTextStudentBirthday);
        editextstudentcode = findViewById(R.id.EditTextStudentCode);
        editextStudentName = findViewById(R.id.EditTextStudentName);

        radioButtonMale = findViewById(R.id.radiobuttonMale);
        RadioButtonFeMale=findViewById(R.id.radiobuttonFeMale);

        Intent intent = getIntent();
        int id_subject  = intent.getIntExtra("id_subject",0);

        db = new DbHelper(this);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddStudentActivity.this, StudentActivity.class));
                finish();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogAdd(id_subject);
            }
        });
    }

    private void DialogAdd(int id_subject) {
        Dialog dialog = new Dialog(this);

        dialog.setContentView(R.layout.dialog_add);
        dialog.setCanceledOnTouchOutside(false);

        Button btnyes = dialog.findViewById(R.id.btnYesAdd);
        Button btnno = dialog.findViewById(R.id.btnNoAdd);

        btnyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editextStudentName.getText().toString().trim();
                String code = editextstudentcode.getText().toString().trim();
                String birtday = edittextdateofbirth.getText().toString().trim();
                String sex = "";
                if (radioButtonMale.isChecked()){
                    sex="Nam";
                }else if (RadioButtonFeMale.isChecked()){
                    sex="Nữ";
                }
                if (name.equals("")|| code.equals("")||birtday.equals("")||sex.equals("")){
                    Toast.makeText(AddStudentActivity.this,"Bạn phải nhập đủ thông tin",Toast.LENGTH_SHORT).show();
                }else {
                    Student student = Createstudent(id_subject);
                    db.Addstudent(student);
                    Toast.makeText(AddStudentActivity.this,"Lưu thành công ",Toast.LENGTH_SHORT).show();

                    Intent intent= new Intent(AddStudentActivity.this,StudentActivity.class);
                    intent.putExtra("id_subject",id_subject);
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
    private Student Createstudent(int id_subject){
        String name = editextStudentName.getText().toString().trim();
        String code = editextstudentcode.getText().toString().trim();
        String birtday = edittextdateofbirth.getText().toString().trim();
        String sex = "";
        if (radioButtonMale.isChecked()){
            sex="Nam";
        }else if (RadioButtonFeMale.isChecked()){
            sex="Nữ";
        }
        Student student = new Student(name,sex,code,birtday,id_subject);
        return student;
    }
}