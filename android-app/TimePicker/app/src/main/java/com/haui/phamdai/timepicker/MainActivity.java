package com.haui.phamdai.timepicker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView txtTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtTime = findViewById(R.id.textviewTime);
        txtTime.setOnClickListener(v -> {
            chonGio();
        });
    }

    private void chonGio() {
        Calendar calendar = Calendar.getInstance();
        int defaultHour = calendar.get(Calendar.HOUR_OF_DAY);
        int defaultMinute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                calendar.set(0, 0, 0, hourOfDay, minute);
                txtTime.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, defaultHour, defaultMinute, true);
        timePickerDialog.show();
    }
}