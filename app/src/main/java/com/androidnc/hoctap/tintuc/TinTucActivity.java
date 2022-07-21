package com.androidnc.hoctap.tintuc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.androidnc.hoctap.R;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class TinTucActivity extends AppCompatActivity {

    EditText edSearch;
    ImageView icSearch;
    ListView listView;
    ArrayList<String> arrayList, arraylink;
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tin_tuc);

        edSearch = findViewById(R.id.edSearch);
        icSearch = findViewById(R.id.icSearch);
        listView = findViewById(R.id.lv);

        icSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link = edSearch.getText().toString();
                if(link.isEmpty()){
                    edSearch.setError("Vui lòng nhập đường dẫn");
                }else {
// Thiếu lấy link để hiển webview
                    arrayList = new ArrayList<>();
                    arrayAdapter = new ArrayAdapter(TinTucActivity.this, android.R.layout.simple_list_item_1,arrayList);
                    listView.setAdapter(arrayAdapter);
                    new ReadRSS().execute(link);
                }
            }
        });

        // Chạy link
        new ReadRSS().execute("https://caodang.fpt.edu.vn/category/tin-tuc-poly/tin-da-nang/feed");
//        https://vnexpress.net/rss/giao-duc.rss

        // tạo arrlist chứa dữ liệu
        arrayList = new ArrayList<>();
        arraylink = new ArrayList<>();
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);

        // Khi click vào các title trên listview sẽ đổ sang webview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TinTucActivity.this,WebViewActivity.class);
                intent.putExtra("openLink",arraylink.get(position));
                startActivity(intent);
            }
        });
    }

    // phương thức đọc RSS
    private class ReadRSS extends AsyncTask<String,Void,String>{
        //lay du lieu tu sever
        @Override
        protected String doInBackground(String... strings) {
            StringBuilder content = new StringBuilder();
            try {
                // Khởi tạo 2 URL
                URL url = new URL(strings[0]);
                // Khởi tạo cầu nối chuyển dữ liệu
                InputStreamReader reader = new InputStreamReader(url.openConnection().getInputStream());
                // Đọc dữ liệu chuyển vào
                BufferedReader bufferedReader = new BufferedReader(reader);

                String line ="";

                while ((line = bufferedReader.readLine())!=null){
                    content.append(line);
                }
                bufferedReader.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return content.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            XMLParse xmlParse = new XMLParse();
            Document document = xmlParse.getDocument(s);
            NodeList nodeList = document.getElementsByTagName("item");
            String title = "";//lay ve title

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i); //lay ve item i
                title = xmlParse.getValue(element, "title");

                //thêm thông tin vào arraylist
                arrayList.add(title);
                // thêm link
                arraylink.add(xmlParse.getValue(element, "link"));

            }
            arrayAdapter.notifyDataSetChanged();
        }
    }
}