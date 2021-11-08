package com.haui.phamdai.calendarkpt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView txtTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtTime = findViewById(R.id.textviewTime);

        // thư viện java.util
        Calendar calendar = Calendar.getInstance();

        txtTime.append(calendar.getTime() + "\n");
        txtTime.append(calendar.get(Calendar.DATE) + "\n");
        txtTime.append(calendar.get(Calendar.MONTH) + "\n");
        txtTime.append(calendar.get(Calendar.YEAR) + "\n");

        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");// 12/12/2017
        txtTime.append(formatDate.format(calendar.getTime()) + "\n");

        txtTime.append(calendar.get(Calendar.HOUR) + "\n");
        txtTime.append(calendar.get(Calendar.HOUR_OF_DAY) + "\n");

        SimpleDateFormat formatHour = new SimpleDateFormat("hh:mm:ss a");// 16:05:22
        txtTime.append(formatHour.format(calendar.getTime()));

    }
}