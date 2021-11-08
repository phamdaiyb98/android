package com.haui.phamdai.jsonarraykpt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listViewCourses;
    List<String> coursesList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewCourses = findViewById(R.id.listviewCourses);
        coursesList = new ArrayList<>();
        new ReadJSONArray().execute("https://khoapham.vn/KhoaPhamTraining/json/tien/demo2.json");
        adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, coursesList);
        listViewCourses.setAdapter(adapter);
    }

    private class ReadJSONArray extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder content = new StringBuilder();
            try {
                URL url = new URL(strings[0]);
                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    content.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject object = new JSONObject(s);
                JSONArray array = object.getJSONArray("danhsach");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject objectKH = array.getJSONObject(i);
                    String ten = objectKH.getString("khoahoc");
                    coursesList.add(ten);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            adapter.notifyDataSetChanged();
        }
    }
}