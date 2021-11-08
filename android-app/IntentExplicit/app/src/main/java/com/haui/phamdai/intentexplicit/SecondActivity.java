package com.haui.phamdai.intentexplicit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends Activity {

    Button btnSecond;
    TextView txtKetQua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        txtKetQua = findViewById(R.id.textViewKetQua);

        Intent intentSecond = getIntent();
//        String noiDung = intentSecond.getStringExtra("duLieu");

//        default value được sử dụng khi sai tên hoặc sai kiểu dữ liệu
//        int noiDungSo = intentSecond.getIntExtra("duLieuSo",1234);
//        String[] arrayCourse = intentSecond.getStringArrayExtra("duLieuMang");
//        HocSinh hocSinh = (HocSinh) intentSecond.getSerializableExtra("duLieuDoiTuong");
        Bundle bundle = intentSecond.getBundleExtra("duLieu");

        // kiểm tra xem bundle có dữ liệu không mới get dữ liệu ra
        if(bundle!=null){
            String ten = bundle.getString("chuoi");
            int so = bundle.getInt("conSo",123);
            String[] mang = bundle.getStringArray("mangTen");
            HocSinh hocSinh = (HocSinh) bundle.getSerializable("doiTuong");
            txtKetQua.setText(ten+"\n"+so+"\n"+mang[0]+"\n"+hocSinh.getHoTen());
        }

        btnSecond = findViewById(R.id.buttonSecond);
        btnSecond.setOnClickListener(v -> {
            Intent intent = new Intent(SecondActivity.this, MainActivity.class);
            startActivity(intent);
        });

//        Log.d("AAA","onCreate Second");
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        Log.d("AAA","onStart Second");
//    }
//
//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        Log.d("AAA","onRestart Second");
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        Log.d("AAA","onResume Second");
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        Log.d("AAA","onPause Second");
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        Log.d("AAA","onStop Second");
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        Log.d("AAA","onDestroy Second");
//    }
}