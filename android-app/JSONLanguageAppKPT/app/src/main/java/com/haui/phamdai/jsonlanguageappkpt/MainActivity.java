package com.haui.phamdai.jsonlanguageappkpt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    ImageButton ibtnVN;
    ImageButton ibtnEN;
    TextView txtContent;
    String content = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        map();
        new ReadJSON().execute("https://khoapham.vn/KhoaPhamTraining/json/tien/demo3.json");

        ibtnEN.setOnClickListener(v -> {
            ReadJSONLanguage("en");
        });

        ibtnVN.setOnClickListener(v -> {
            ReadJSONLanguage("vn");
        });
    }

    private class ReadJSON extends AsyncTask<String, Void, String> {

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
            content = s;
            ReadJSONLanguage("vn");
        }
    }

    private void ReadJSONLanguage(String lang){
        try {
            JSONObject object = new JSONObject(content);
            JSONObject languageObj = object.getJSONObject("language");
            JSONObject vnLangObj = languageObj.getJSONObject(lang);
            String name = vnLangObj.getString("name");
            String address = vnLangObj.getString("address");
            String course1 = vnLangObj.getString("course1");
            String course2 = vnLangObj.getString("course2");
            String course3 = vnLangObj.getString("course3");
            txtContent.setText(name+"\n"+address+"\n"+course1+"\n"+course2+"\n"+course3);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void map() {
        ibtnVN = findViewById(R.id.imageButtonVN);
        ibtnEN = findViewById(R.id.imageButtonEN);
        txtContent = findViewById(R.id.textViewContent);
    }
}