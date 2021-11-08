package com.haui.phamdai.sqlitesaveimagekpt;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnThem;
    RecyclerView recyclerViewDoVat;
    public static Database database;
    List<DoVat> doVatList = new ArrayList<>();
    DoVatAdapter doVatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = new Database(this, "QuanLy.sqlite", null, 1);

        database.queryData("CREATE TABLE IF NOT EXISTS DoVat(Id INTEGER PRIMARY KEY AUTOINCREMENT, Ten VARCHAR(150), MoTa VARCHAR(250), HinhAnh BLOB)");

        btnThem = findViewById(R.id.buttonThem);
        recyclerViewDoVat = findViewById(R.id.recyclerviewDoVat);

        btnThem.setOnClickListener(v -> {
            Intent intent = new Intent(this, UpdateDataDoVatActivity.class);
            startActivity(intent);
        });

        // set layout cho recyclerView
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewDoVat.setLayoutManager(linearLayoutManager);
        // set orientation cho recyclerView
        DividerItemDecoration dividerItemDecoration
                = new DividerItemDecoration(recyclerViewDoVat.getContext(), linearLayoutManager.getOrientation());
        recyclerViewDoVat.addItemDecoration(dividerItemDecoration);
        //set adapter cho recyclerView
        doVatAdapter = new DoVatAdapter(getDoVatList(), this);
        recyclerViewDoVat.setAdapter(doVatAdapter);
    }

    private List<DoVat> getDoVatList() {
        doVatList.clear();
        Cursor cursor = database.getData("SELECT * FROM DoVat");
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String ten = cursor.getString(1);
            String moTa = cursor.getString(2);
            byte[] hinhAnh = cursor.getBlob(3);
            doVatList.add(new DoVat(id, ten, moTa, hinhAnh));
        }
        return doVatList;
    }

    public void updateDoVat(DoVat doVat, int position) {
        Intent intent = new Intent(this, UpdateDataDoVatActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("doVat", doVat);
        bundle.putInt("pos", position);
        intent.putExtra("data", bundle);
        startActivity(intent);
    }

    public void deleteDoVat(int id, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Bạn có muốn xóa đồ vật này không?");
        builder.setPositiveButton("Có", (dialog, which) -> {
            // delete in database
            database.queryData("DELETE FROM DoVat WHERE Id = " + id);

            //delete on UI
            doVatList.remove(position);
            doVatAdapter.notifyItemRemoved(position);
        });
        builder.setNegativeButton("Không", (dialog, which) -> {
        });
        builder.show();
    }
}