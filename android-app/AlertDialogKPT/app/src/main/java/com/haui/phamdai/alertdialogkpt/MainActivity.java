package com.haui.phamdai.alertdialogkpt;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listViewName;
    List<String> arrayName;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewName = findViewById(R.id.listViewName);
        arrayName = new ArrayList<>();
        addArrayName();
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                arrayName);
        listViewName.setAdapter(adapter);

        listViewName.setOnItemLongClickListener((parent, view, position, id) -> {
            xacNhanXoa(position);
            return false;
        });
    }

    private void xacNhanXoa(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông báo!");
        builder.setMessage("Bạn có muốn xóa môn học " + arrayName.get(position) + " không?");
        builder.setIcon(R.drawable.ic_launcher_background);
        builder.setPositiveButton("Có", (dialog, which) -> {
            arrayName.remove(position);
            adapter.notifyDataSetChanged();
        });
        builder.setNegativeButton("Không", (dialog, which) -> {
        });
        builder.setIcon(R.mipmap.ic_launcher);
        builder.show();
    }

    private void addArrayName() {
        arrayName.add("Android");
        arrayName.add("iOS");
        arrayName.add("PHP");
        arrayName.add("ASP.NET");
        arrayName.add("Unity 3D");
        arrayName.add("Cocos2dx");
        arrayName.add("NodeJS");
    }
}