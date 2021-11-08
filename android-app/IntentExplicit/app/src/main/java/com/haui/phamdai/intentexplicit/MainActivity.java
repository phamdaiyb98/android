package com.haui.phamdai.intentexplicit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import java.io.Serializable;

public class MainActivity extends Activity {

    Button btnMain;
    EditText edtNoiDung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNoiDung = findViewById(R.id.editTextNoiDung);
        btnMain = findViewById(R.id.buttonMain);
        btnMain.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
//            intent.putExtra("duLieu",edtNoiDung.getText().toString());
//            intent.putExtra("duLieuSo",2017);
//            String[] arrayCourse = {"Android","iOS", "PHP", "NodeJs", "Unity"};
//            intent.putExtra("duLieuMang",arrayCourse);

//             gửi đối tượng thì cần implements interface Serializable
//            HocSinh hocSinh = new HocSinh("Minh Tien",2017);
//            intent.putExtra("duLieuDoiTuong", hocSinh);


            String[] arrayCourse = {"Android", "iOS", "PHP", "NodeJs", "Unity"};
            HocSinh hocSinh = new HocSinh("Minh Tien", 2017);
//            dùng bundle để gửi một khối dữ liệu lớn
            Bundle bundle = new Bundle();
            bundle.putString("chuoi", "Khoa Pham Training");
            bundle.putInt("conSo", 12345);
            bundle.putStringArray("mangTen", arrayCourse);
            bundle.putSerializable("doiTuong", hocSinh);
            intent.putExtra("duLieu", bundle);

            startActivity(intent);
        });

//        Log.d("AAA","onCreate Main");
    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        Log.d("AAA","onStart Main");
//    }
//
//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        Log.d("AAA","onRestart Main");
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        Log.d("AAA","onResume Main");
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        Log.d("AAA","onPause Main");
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        Log.d("AAA","onStop Main");
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        Log.d("AAA","onDestroy Main");
//    }
}