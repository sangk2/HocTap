package com.androidnc.hoctap.khoahoc.subject;

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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.androidnc.hoctap.MainActivity;
import com.androidnc.hoctap.R;
import com.androidnc.hoctap.khoahoc.database.DbHelper;
import com.androidnc.hoctap.khoahoc.student.StudentActivity;

import java.util.ArrayList;

public class SubjectActivity extends AppCompatActivity {

    Toolbar toolbar;
    ListView lvSubject;
    ArrayList<Subject> listSubject;
    DbHelper db;
    SubjectAdapter adapter;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

        lvSubject =  findViewById(R.id.lvSubject);

        toolbar = findViewById(R.id.toolbarSubject);
        toolbar.setTitle("");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = new DbHelper(this);


        capNhat();
//        listSubject = new ArrayList<>();
//
//        Cursor cursor = db.getDataSubject();
//
//        while (cursor.moveToNext()){
//            int id = cursor.getInt(0);
//            String title = cursor.getString(1);
//            int credit =  cursor.getInt(2);
//            String time = cursor.getString(3);
//            String place = cursor.getString(4);
//
//            listSubject.add(new Subject(id,title,credit,time,place));
//        }
//        adapter = new SubjectAdapter(SubjectActivity.this,listSubject);
//        lvSubject.setAdapter(adapter);
//
//        cursor.moveToFirst();
//        cursor.close();


        lvSubject.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                intent = new Intent(SubjectActivity.this, StudentActivity.class);
                int id_subject = listSubject.get(i).getId();
                // Toast tên Khóa học
                String title = listSubject.get(i).getSubject_title();
                Toast.makeText(getApplicationContext(),"Chọn Khóa Học: "+title,
                        Toast.LENGTH_SHORT).show();

                intent.putExtra("id_subject",id_subject);
                startActivity(intent);
                finish();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuadd,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuadd:
                intent = new Intent(SubjectActivity.this, AddSubjectActivity.class);
                startActivity(intent);
                finish();
                break;
            default:
                intent = new Intent(SubjectActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }


    public void information(final int Id){
        Cursor cursor = db.getDataSubject();

        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            if (id == Id){
                intent = new Intent(SubjectActivity.this, InformationSubjectActivity.class);

                String title =cursor.getString(1);
                int credit =cursor.getInt(2);
                String time = cursor.getString(3);
                String place = cursor.getString(4);

                // Gửi dữ liệu qua Activity Information
                intent.putExtra("id",id);
                intent.putExtra("title",title);
                intent.putExtra("credit",credit);
                intent.putExtra("time",time);
                intent.putExtra("place",place);

                startActivity(intent);
            }
        }
        cursor.close();
    }

    public void update(final int Id){
        Cursor cursor = db.getDataSubject();

        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            if (id == Id){
                intent = new Intent(SubjectActivity.this, UpdateSubjectActivity.class);

                String title=cursor.getString(1);
                int credit = cursor.getInt(2);
                String time = cursor.getString(3);
                String place = cursor.getString(4);

                // Gửi dữ liệu qua Activity Update
                intent.putExtra("id",id);
                intent.putExtra("title",title);
                intent.putExtra("credit",credit);
                intent.putExtra("time",time);
                intent.putExtra("place",place);

                startActivity(intent);
            }
        }
        cursor.close();
    }

    public void delete(final int id){
        Dialog dialog = new Dialog(this);
        //nap layout
        dialog.setContentView(R.layout.dialog_delete);

        // Khi chạm bên ngoài dialog sẽ ko đóng
        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = dialog.findViewById(R.id.btnYesDel);
        Button btnNo = dialog.findViewById(R.id.btnNoDel);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xóa Subject
                db.DeleteSubject(id);
                // Xóa Student
                db.DeleteStudent(id);

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

        listSubject = new ArrayList<>();

        Cursor cursor = db.getDataSubject();

        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            int credit =  cursor.getInt(2);
            String time = cursor.getString(3);
            String place = cursor.getString(4);

            listSubject.add(new Subject(id,title,credit,time,place));
        }
        adapter = new SubjectAdapter(SubjectActivity.this,listSubject);
        lvSubject.setAdapter(adapter);

        cursor.moveToFirst();
        cursor.close();
    }
}