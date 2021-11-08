package com.haui.phamdai.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvTraiCay;
    ArrayList<TraiCay> arrayTraiCay;
    TraiCayAdapter traiCayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();

        traiCayAdapter = new TraiCayAdapter(this,R.layout.dong_trai_cay,arrayTraiCay);
        lvTraiCay.setAdapter(traiCayAdapter);

    }

    private void AnhXa(){
        lvTraiCay = findViewById(R.id.listviewTraiCay);
        arrayTraiCay = new ArrayList<>();

        arrayTraiCay.add(new TraiCay("Chuối","Chuối Hà Nội",R.drawable.chuoi));
        arrayTraiCay.add(new TraiCay("Đu Đủ","Đu Đủ Yên Bái", R.drawable.dudu));
        arrayTraiCay.add(new TraiCay("Măng cụt","Măng cụt Bắc Giang",R.drawable.mangcut));
        arrayTraiCay.add(new TraiCay("Nho","Nho Thái Lan",R.drawable.nho));
        arrayTraiCay.add(new TraiCay("Ổi","Ổi Lạng Sơn",R.drawable.oi));
        arrayTraiCay.add(new TraiCay("Táo","Táo Trung Quốc",R.drawable.tao));

        arrayTraiCay.add(new TraiCay("Chuối","Chuối Hà Nội",R.drawable.chuoi));
        arrayTraiCay.add(new TraiCay("Đu Đủ","Đu Đủ Yên Bái", R.drawable.dudu));
        arrayTraiCay.add(new TraiCay("Măng cụt","Măng cụt Bắc Giang",R.drawable.mangcut));
        arrayTraiCay.add(new TraiCay("Nho","Nho Thái Lan",R.drawable.nho));
        arrayTraiCay.add(new TraiCay("Ổi","Ổi Lạng Sơn",R.drawable.oi));
        arrayTraiCay.add(new TraiCay("Táo","Táo Trung Quốc",R.drawable.tao));

        arrayTraiCay.add(new TraiCay("Chuối","Chuối Hà Nội",R.drawable.chuoi));
        arrayTraiCay.add(new TraiCay("Đu Đủ","Đu Đủ Yên Bái", R.drawable.dudu));
        arrayTraiCay.add(new TraiCay("Măng cụt","Măng cụt Bắc Giang",R.drawable.mangcut));
        arrayTraiCay.add(new TraiCay("Nho","Nho Thái Lan",R.drawable.nho));
        arrayTraiCay.add(new TraiCay("Ổi","Ổi Lạng Sơn",R.drawable.oi));
        arrayTraiCay.add(new TraiCay("Táo","Táo Trung Quốc",R.drawable.tao));

        arrayTraiCay.add(new TraiCay("Chuối","Chuối Hà Nội",R.drawable.chuoi));
        arrayTraiCay.add(new TraiCay("Đu Đủ","Đu Đủ Yên Bái", R.drawable.dudu));
        arrayTraiCay.add(new TraiCay("Măng cụt","Măng cụt Bắc Giang",R.drawable.mangcut));
        arrayTraiCay.add(new TraiCay("Nho","Nho Thái Lan",R.drawable.nho));
        arrayTraiCay.add(new TraiCay("Ổi","Ổi Lạng Sơn",R.drawable.oi));
        arrayTraiCay.add(new TraiCay("Táo","Táo Trung Quốc",R.drawable.tao));
    }

}