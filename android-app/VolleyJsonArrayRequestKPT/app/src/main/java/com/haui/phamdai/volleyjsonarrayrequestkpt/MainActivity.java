package com.haui.phamdai.volleyjsonarrayrequestkpt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        String url = "https://khoapham.vn/KhoaPhamTraining/json/tien/demo4.json";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject object = response.getJSONObject(i);
                            String khoaHoc = object.getString("khoahoc");
                            String hocPhi = object.getString("hocphi");
                            Toast.makeText(this, khoaHoc + " - " + hocPhi, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, error -> {
            Toast.makeText(this, "Lỗi" + error.toString(), Toast.LENGTH_SHORT).show();

        });

        requestQueue.add(jsonArrayRequest);

    }
}