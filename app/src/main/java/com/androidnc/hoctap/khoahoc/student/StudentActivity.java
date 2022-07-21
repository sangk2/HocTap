package com.androidnc.hoctap.khoahoc.student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.androidnc.hoctap.R;
import com.androidnc.hoctap.khoahoc.database.DbHelper;
import com.androidnc.hoctap.khoahoc.subject.SubjectActivity;

import java.util.ArrayList;

public class StudentActivity extends AppCompatActivity {

    Toolbar toolbar;
    ListView listViewstudent;

    ArrayList<Student> ArrayListStudent;
    DbHelper database;
    StudentAdapter adapter;

    Intent intent;

    int id_subject = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        listViewstudent = findViewById(R.id.lvStudent);

        toolbar = findViewById(R.id.toolbarStudent);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent intent = getIntent();
        id_subject = intent.getIntExtra("id_subject",0);
        database = new DbHelper(this);

        capNhat();

//        ArrayListStudent = new ArrayList<>();
//        ArrayListStudent.clear();
//
//        Cursor cursor = database.getDataStudent(id_subject);
//        while (cursor.moveToNext()){
//            int id_sub = cursor.getInt(5);
//            int id = cursor.getInt(0);
//            String name = cursor.getString(1);
//            String sex = cursor.getString(2);
//            String code = cursor.getString(3);
//            String birthday  =cursor.getColumnName(4);
//
//            ArrayListStudent.add(new Student(id,name,sex,code,birthday,id_sub));
//
//        }
//        adapter = new StudentAdapter(StudentActivity.this,ArrayListStudent);
//        //hien thi
//        listViewstudent.setAdapter(adapter);
//        cursor.moveToFirst();
//        cursor.close();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuaddstudent,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuaddStudent:
                intent = new Intent(StudentActivity.this, AddStudentActivity.class);
                intent.putExtra("id_subject",id_subject);
                startActivity(intent);
                break;
            default:
                intent = new Intent(StudentActivity.this, SubjectActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public void information(final int pos){
        Cursor cursor = database.getDataStudent(id_subject);
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);

            if (id == pos){
                intent = new Intent(StudentActivity.this, InformationStudentActivity.class);
                intent.putExtra("id",pos);

                String name = cursor.getString(1);
                String sex = cursor.getString(2);
                String code = cursor.getString(3);
                String birthday = cursor.getString(4);
                int id_subject = cursor.getInt(5);

                intent.putExtra("name",name);
                intent.putExtra("sex",sex);
                intent.putExtra("code",code);
                intent.putExtra("birtday",birthday);
                intent.putExtra("id_subject",id_subject);

                startActivity(intent);
                finish();
            }
        }
        cursor.close();
    }

    //update

    public void update(final int id_student){
        Cursor cursor =database.getDataStudent(id_subject);
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);

            if (id == id_student){
                intent = new Intent(StudentActivity.this, UpdateStudentActivity.class);
                intent.putExtra("id",id_student);

                String name = cursor.getString(1);
                String sex = cursor.getString(2);
                String code = cursor.getString(3);
                String birthday = cursor.getString(4);
                int id_subject = cursor.getInt(5);

                intent.putExtra("name",name);
                intent.putExtra("sex",sex);
                intent.putExtra("code",code);
                intent.putExtra("birtday",birthday);
                intent.putExtra("id_subject",id_subject);

                startActivity(intent);
                finish();
            }
        }
        cursor.close();
    }

    public void delete(final int id_student){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_delete);

        // Khi chạm bên ngoài dialog sẽ ko đóng
        dialog.setCanceledOnTouchOutside(false);

        Button btnYes =dialog. findViewById(R.id.btnYesDel);
        Button btnNo = dialog.findViewById(R.id.btnNoDel);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //xoa khoi database
                database.DeleteStudent(id_student);
                capNhat();
                dialog.cancel();
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

    void capNhat(){
        ArrayListStudent = new ArrayList<>();
//        ArrayListStudent.clear();

        Cursor cursor = database.getDataStudent(id_subject);
        while (cursor.moveToNext()){
            int id_sub = cursor.getInt(5);
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String sex = cursor.getString(2);
            String code = cursor.getString(3);
            String birthday  =cursor.getColumnName(4);

            ArrayListStudent.add(new Student(id,name,sex,code,birthday,id_sub));

        }
        adapter = new StudentAdapter(StudentActivity.this,ArrayListStudent);
        //hien thi
        listViewstudent.setAdapter(adapter);
        cursor.moveToFirst();
        cursor.close();

    }
}