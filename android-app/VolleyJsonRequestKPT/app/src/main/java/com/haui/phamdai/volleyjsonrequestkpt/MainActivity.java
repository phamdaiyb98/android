package com.haui.phamdai.volleyjsonrequestkpt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://khoapham.vn/KhoaPhamTraining/json/tien/demo1.json";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET
        ,url,null,
        response -> {
            try {
                String monhoc = response.getString("monhoc");
                Toast.makeText(this, monhoc, Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        },error -> Toast.makeText(this, "Lỗi", Toast.LENGTH_SHORT).show());

        requestQueue.add(jsonObjectRequest);
    }
}