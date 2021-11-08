package com.haui.phamdai.demngay;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText edtNgayDau;
    EditText edtNgayCuoi;
    Button btnTinh;
    TextView txtKetQua;

    Calendar calendar1;
    Calendar calendar2;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhXa();

        edtNgayDau.setOnClickListener(v -> {
            chonNgay1();
        });

        edtNgayCuoi.setOnClickListener(v -> {
            chonNgay2();
        });

        btnTinh.setOnClickListener(v -> {
            long khoangThoiGian = (calendar2.getTimeInMillis() - calendar1.getTimeInMillis()) / (1000 * 60 * 60 * 24);
            if (khoangThoiGian < 0) {
                Toast.makeText(this, "Vui lòng chọn ngày thứ hai sau ngày thứ nhất", Toast.LENGTH_SHORT).show();
            } else {
                txtKetQua.setText("Khoảng thời gian là: " + khoangThoiGian);
            }
        });

    }

    private void chonNgay1() {
        calendar1 = Calendar.getInstance();
        int defaultDate = calendar1.get(Calendar.DATE);
        int defaultMonth = calendar1.get(Calendar.MONTH);
        int defaultYear = calendar1.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar1.set(year, month, dayOfMonth);
                        edtNgayDau.setText(simpleDateFormat.format(calendar1.getTime()));
                    }
                }, defaultYear, defaultMonth, defaultDate);
        datePickerDialog.show();
    }

    private void chonNgay2() {
        calendar2 = Calendar.getInstance();
        int defaultDate = calendar2.get(Calendar.DATE);
        int defaultMonth = calendar2.get(Calendar.MONTH);
        int defaultYear = calendar2.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar2.set(year, month, dayOfMonth);
                        edtNgayCuoi.setText(simpleDateFormat.format(calendar2.getTime()));
                    }
                }, defaultYear, defaultMonth, defaultDate);
        datePickerDialog.show();
    }

    private void anhXa() {
        edtNgayDau = findViewById(R.id.editTextNgayDau);
        edtNgayCuoi = findViewById(R.id.editTextNgayCuoi);
        btnTinh = findViewById(R.id.buttonTinh);
        txtKetQua = findViewById(R.id.textViewKetQua);
    }
}