package com.haui.phamdai.menukpt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //hàm tạo menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_demo, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // bắt sự kiện của menu
        switch (item.getItemId()) {
            case R.id.menuSetting:
                Toast.makeText(this, "Bạn chọn setting", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menuShare:
                Toast.makeText(this, "Bạn chọn share", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menuPhone:
                Toast.makeText(this, "Bạn chọn phone", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menuEmail:
                Toast.makeText(this, "Bạn chọn email", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menuExit:
                Toast.makeText(this, "Bạn chọn exit", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}