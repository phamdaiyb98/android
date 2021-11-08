package com.haui.phamdai.sqlitedatabasekpt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static Database database;
    ListView lvCV;
    List<CongViec> congViecs;
    CongViecAdapter adapter;
//    public static WeakReference<MainActivity> weakReference;
//
//    public static MainActivity getInstance() {
//        return weakReference.get();
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        weakReference = new WeakReference<>(this);

        lvCV = findViewById(R.id.listviewCV);
        congViecs = new ArrayList<>();
        adapter = new CongViecAdapter(this, R.layout.dong_cong_viec, congViecs);
        lvCV.setAdapter(adapter);

        // create database
        database = new Database(this, "ghichu.sqlite", null, 1);

        // create CongViec table
        database.queryData("CREATE TABLE IF NOT EXISTS CongViec(Id INTEGER PRIMARY KEY AUTOINCREMENT, TenCV VARCHAR(200))");

        //insert data
//        database.queryData("INSERT INTO CongViec VALUES(null,'Viết ứng dụng ghi chú')");
        getDataCongViec();
    }

    private void getDataCongViec() {
        //select data
        Cursor cursor = database.getData("SELECT * FROM CongViec");
        congViecs.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String ten = cursor.getString(1);
            congViecs.add(new CongViec(id, ten));
        }

        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_cong_viec, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuAdd) {
            createDialog(Action.CREATE, null);
        }
        return super.onOptionsItemSelected(item);
    }

    public void createDialog(Action action, CongViec congViec) {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_them_cong_viec);

        EditText edtTen = dialog.findViewById(R.id.edittextTen);
        Button btnThem = dialog.findViewById(R.id.buttonAdd);
        Button btnHuy = dialog.findViewById(R.id.buttonHuy);

        if (action.equals(Action.UPDATE)) {
            TextView txtTitle = dialog.findViewById(R.id.textviewTitle);
            edtTen.setText(congViec.getTenCV());
            edtTen.setHint("Nhập công việc");
            btnThem.setText("Sửa");
            txtTitle.setText("Sửa công việc");
        }

        btnHuy.setOnClickListener(v -> {
            dialog.dismiss();
        });

        btnThem.setOnClickListener(v -> {
            String tenCv = edtTen.getText().toString();
            if (tenCv.equals("")) {
                Toast.makeText(this, "Vui lòng nhập tên công việc!", Toast.LENGTH_SHORT).show();
            } else {
                if (action.equals(Action.CREATE)) {
                    database.queryData("INSERT INTO CongViec VALUES(null,'" + tenCv + "')");
                }
                if (action.equals(Action.UPDATE)) {
                    database.queryData("UPDATE CongViec SET TenCV = '" + tenCv + "' WHERE Id = " + congViec.getIdCV());
                }
                dialog.dismiss();
                getDataCongViec();
            }
        });

        dialog.show();
    }

    public void createDeleteDialog(CongViec congViec) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Bạn có muốn xóa " + congViec.getTenCV() + " không?");
        builder.setPositiveButton("Có", (dialog, which) -> {
            database.queryData("DELETE FROM CongViec WHERE Id = " + congViec.getIdCV());
            getDataCongViec();
        });
        builder.setNegativeButton("Không", (dialog, which) -> {
        });
        builder.show();
    }
}