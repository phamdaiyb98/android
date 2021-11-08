package com.haui.phamdai.volleystringrequestkpt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://online.khoapham.vn";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    Toast.makeText(this, response, Toast.LENGTH_SHORT).show();
                }, error -> {
            Toast.makeText(this, "Lỗi!", Toast.LENGTH_SHORT).show();
            Log.d("AAA", "Lỗi\n" + error.toString());
        });

        requestQueue.add(stringRequest);
    }


}