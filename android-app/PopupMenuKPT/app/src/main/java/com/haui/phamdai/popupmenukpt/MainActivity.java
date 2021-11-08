package com.haui.phamdai.popupmenukpt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.PopupMenu;

public class MainActivity extends AppCompatActivity {

    Button btnPopupMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPopupMenu = findViewById(R.id.buttonMenu);

        btnPopupMenu.setOnClickListener(v -> {
            showMenu();
        });
    }

    private void showMenu() {
        PopupMenu popupMenu = new PopupMenu(this, btnPopupMenu);
        popupMenu.getMenuInflater().inflate(R.menu.menu_popup, popupMenu.getMenu());

        // bắt sự kiện click trên menu
        popupMenu.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.menuThem:
                    btnPopupMenu.setText("Thêm");
                    break;
                case R.id.menuSua:
                    btnPopupMenu.setText("Sửa");
                    break;
                case R.id.menuXoa:
                    btnPopupMenu.setText("Xóa");
                    break;
            }
            return false;
        });
        popupMenu.show();
    }
}