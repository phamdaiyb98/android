package com.haui.phamdai.gridviewkpt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GridView gvTen;
    ArrayList<HinhAnh> arrayImage;
    HinhAnhAdapter hinhAnhAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();

        hinhAnhAdapter = new HinhAnhAdapter(this,R.layout.dong_hinh_anh,arrayImage);

        gvTen.setAdapter(hinhAnhAdapter);

        gvTen.setOnItemClickListener((parent, view, position, id) -> {
            Toast.makeText(this, arrayImage.get(position).getTen(), Toast.LENGTH_SHORT).show();
        });
    }

    private void AnhXa(){
        gvTen = findViewById(R.id.gridviewTen);
        arrayImage = new ArrayList<>();

        arrayImage.add(new HinhAnh("Cây 1",R.drawable.cay1));
        arrayImage.add(new HinhAnh("Cây 2",R.drawable.cay2));
        arrayImage.add(new HinhAnh("Cây 3",R.drawable.cay3));
        arrayImage.add(new HinhAnh("Cây 4",R.drawable.cay4));
        arrayImage.add(new HinhAnh("Cây 5",R.drawable.cay5));
        arrayImage.add(new HinhAnh("Cây 6",R.drawable.cay6));
        arrayImage.add(new HinhAnh("Cây 7",R.drawable.cay7));
        arrayImage.add(new HinhAnh("Cây 8",R.drawable.cay8));
        arrayImage.add(new HinhAnh("Cây 9",R.drawable.cay9));
    }
}