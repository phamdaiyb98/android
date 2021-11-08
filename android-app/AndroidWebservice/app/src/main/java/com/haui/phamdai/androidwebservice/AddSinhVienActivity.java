package com.haui.phamdai.androidwebservice;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class AddSinhVienActivity extends AppCompatActivity {

    TextView txtTitle;
    EditText edtHoTen, edtNamSinh, edtDiaChi;
    Button btnThem, btnHuy;
    String urlInsert = "https://delible-batches.000webhostapp.com/insert.php";
    String urlUpdate = "https://delible-batches.000webhostapp.com/update.php";
    boolean isInsert;
    int updateId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sinh_vien);

        mapWidget();

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        if (bundle == null) {
            isInsert = true;
        } else {
            //update
            isInsert = false;
            SinhVien sinhVien = (SinhVien) bundle.getSerializable("sinhVien");
            updateId = sinhVien.getId();
            txtTitle.setText("Cập nhật thông tin");
            edtHoTen.setText(sinhVien.getHoTen());
            edtNamSinh.setText(String.valueOf(sinhVien.getNamSinh()));
            edtDiaChi.setText(sinhVien.getDiaChi());
            btnThem.setText("Cập nhật");
        }

        btnThem.setOnClickListener(v -> {
            String hoten = edtHoTen.getText().toString().trim();
            String namsinh = edtNamSinh.getText().toString().trim();
            String diachi = edtDiaChi.getText().toString().trim();
            if (hoten.isEmpty() || namsinh.isEmpty() || diachi.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
            } else {
                if (isInsert) {
                    themSinhVien(urlInsert);
                } else {
                    capNhapSinhVien(urlUpdate);
                }
            }
        });

        btnHuy.setOnClickListener(v -> finish());
    }

    private void capNhapSinhVien(String url) {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST, url,
                response -> {
                    if (response.trim().equals("success")) {
                        Toast.makeText(AddSinhVienActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AddSinhVienActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(AddSinhVienActivity.this, "Lỗi cập nhật!", Toast.LENGTH_SHORT).show();
                    }
                }, error -> {
            Toast.makeText(AddSinhVienActivity.this, "Lỗi không mong muốn!", Toast.LENGTH_SHORT).show();
            Log.d("AAA", error.toString());
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("idSV", String.valueOf(updateId));
                params.put("hotenSV", edtHoTen.getText().toString().trim());
                params.put("namsinhSV", edtNamSinh.getText().toString().trim());
                params.put("diachiSV", edtDiaChi.getText().toString().trim());
                return params;
            }
        };

        queue.add(stringRequest);
    }

    private void themSinhVien(String url) {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST, url,
                response -> {
                    if (response.trim().equals("success")) {
                        Toast.makeText(AddSinhVienActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AddSinhVienActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(AddSinhVienActivity.this, "Lỗi thêm!", Toast.LENGTH_SHORT).show();
                    }
                }, error -> {
            Toast.makeText(AddSinhVienActivity.this, "Lỗi không mong muốn!", Toast.LENGTH_SHORT).show();
            Log.d("AAA", error.toString());
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("hotenSV", edtHoTen.getText().toString().trim());
                params.put("namsinhSV", edtNamSinh.getText().toString().trim());
                params.put("diachiSV", edtDiaChi.getText().toString().trim());
                return params;
            }
        };

        queue.add(stringRequest);
    }

    private void mapWidget() {
        edtHoTen = findViewById(R.id.textViewHoTen_AddSinhVien);
        edtDiaChi = findViewById(R.id.textViewDiaChi_AddSinhVien);
        edtNamSinh = findViewById(R.id.textViewNamSinh_AddSinhVien);
        btnThem = findViewById(R.id.buttonThem_AddSinhVien);
        btnHuy = findViewById(R.id.buttonHuy_AddSinhVien);
        txtTitle = findViewById(R.id.textViewTitle_AddSinhVien);
    }
}