package com.haui.phamdai.datepickerdialogkpt;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText edtChonNgay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtChonNgay = (EditText) findViewById(R.id.edittextChonNgay);
        edtChonNgay.setOnClickListener(v -> {
            chonNgay();
        });
    }

    private void chonNgay() {
        // 2021, 05, 15 - sẽ là ngày mặc định mà date picker chọn
        Calendar calendar = Calendar.getInstance();
        int date = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog =
                new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        //set lại ngày giờ cho calendar
                        calendar.set(year, month, dayOfMonth);
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        edtChonNgay.setText(dateFormat.format(calendar.getTime()));
                    }
                }, year, month, date);

        datePickerDialog.show();
    }
}