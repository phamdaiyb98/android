package com.haui.phamdai.contextmenukpt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnChonMau;
    ConstraintLayout manHinh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manHinh = findViewById(R.id.manHinh);
        btnChonMau = findViewById(R.id.buttonChonMau);

        // đăng kí view cho context menu
        registerForContextMenu(btnChonMau);
    }

    // context menu là long click, popup menu là click
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_context, menu);
        menu.setHeaderTitle("CHỌN MÀU");
        menu.setHeaderIcon(R.mipmap.ic_launcher);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuDo:
                manHinh.setBackgroundColor(Color.RED);
                break;
            case R.id.menuXanh:
                manHinh.setBackgroundColor(Color.BLUE);
                break;
            case R.id.menuVang:
                manHinh.setBackgroundColor(Color.YELLOW);
                break;
        }
        return super.onContextItemSelected(item);
    }
}