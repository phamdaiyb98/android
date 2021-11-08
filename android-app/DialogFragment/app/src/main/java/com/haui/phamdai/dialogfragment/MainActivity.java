package com.haui.phamdai.dialogfragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements DeleteData {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.buttonXoa).setOnClickListener(v -> {
            DialogXacNhanXoaFragment fragment = new DialogXacNhanXoaFragment();
            fragment.show(getSupportFragmentManager(), "dialog");
        });
    }

    @Override
    public void giaTriXoa(boolean delete) {
        if (delete) {
            Toast.makeText(this, "Bạn chọn xóa!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Không xóa", Toast.LENGTH_SHORT).show();
        }
    }
}