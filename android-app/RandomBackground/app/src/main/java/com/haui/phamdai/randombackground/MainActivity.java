package com.haui.phamdai.randombackground;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageButton btnStart;
    CheckBox cbDaDieu, cbRongXanh, cbRongTim;
    SeekBar seekBarDaDieu, seekBarRongTim, seekBarRongXanh;
    TextView txtScore;
    int score = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.imageButtonStart);
        cbDaDieu = findViewById(R.id.checkBoxDaDieu);
        cbRongTim = findViewById(R.id.checkBoxRongTim);
        cbRongXanh = findViewById(R.id.checkBoxRongXanh);
        seekBarDaDieu = findViewById(R.id.seekBarDaDieu);
        seekBarRongTim = findViewById(R.id.seekBarRongTim);
        seekBarRongXanh = findViewById(R.id.seekBarRongXanh);
        txtScore = findViewById(R.id.textViewScore);

        txtScore.setText(score + "");

        seekBarDaDieu.setEnabled(false);
        seekBarRongXanh.setEnabled(false);
        seekBarRongTim.setEnabled(false);

        Random random = new Random();
        CountDownTimer countDownTimer = new CountDownTimer(60000, 300) {
            @Override
            public void onTick(long millisUntilFinished) {
                int number = 5;
                int one = random.nextInt(number);
                int two = random.nextInt(number);
                int three = random.nextInt(number);

                seekBarDaDieu.setProgress(seekBarDaDieu.getProgress() + one);
                seekBarRongXanh.setProgress(seekBarRongXanh.getProgress() + two);
                seekBarRongTim.setProgress(seekBarRongTim.getProgress() + three);
                if (seekBarDaDieu.getProgress() >= seekBarDaDieu.getMax()) {
                    btnStart.setVisibility(View.VISIBLE);
                    setStatusCheckBox(true);
                    Toast.makeText(MainActivity.this, "Thí sinh 1", Toast.LENGTH_SHORT).show();
                    if (cbDaDieu.isChecked()) {
                        score += 10;
                    } else {
                        score -= 5;
                    }
                    txtScore.setText(score + "");
                    this.cancel();
                }
                if (seekBarRongTim.getProgress() >= seekBarRongTim.getMax()) {
                    btnStart.setVisibility(View.VISIBLE);
                    setStatusCheckBox(true);
                    Toast.makeText(MainActivity.this, "Thí sinh 2", Toast.LENGTH_SHORT).show();
                    if (cbRongTim.isChecked()) {
                        score += 10;
                    } else {
                        score -= 5;
                    }
                    txtScore.setText(score + "");
                    this.cancel();
                }
                if (seekBarRongXanh.getProgress() >= seekBarRongXanh.getMax()) {
                    btnStart.setVisibility(View.VISIBLE);
                    setStatusCheckBox(true);
                    Toast.makeText(MainActivity.this, "Thí sinh 3", Toast.LENGTH_SHORT).show();
                    if (cbRongXanh.isChecked()) {
                        score += 10;
                    } else {
                        score -= 5;
                    }
                    txtScore.setText(score + "");
                    this.cancel();
                }
            }

            @Override
            public void onFinish() {
            }
        };

        btnStart.setOnClickListener(v -> {
            if (!cbDaDieu.isChecked() && !cbRongTim.isChecked() && !cbRongXanh.isChecked()) {
                Toast.makeText(this, "Vui lòng chọn một thí sinh!", Toast.LENGTH_SHORT).show();
            } else {
                resetSeekBar();
                setStatusCheckBox(false);
                btnStart.setVisibility(View.INVISIBLE);
                countDownTimer.start();
            }
        });

        cbDaDieu.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                cbRongXanh.setChecked(false);
                cbRongTim.setChecked(false);
            }
        });

        cbRongTim.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                cbDaDieu.setChecked(false);
                cbRongXanh.setChecked(false);
            }
        });

        cbRongXanh.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                cbDaDieu.setChecked(false);
                cbRongTim.setChecked(false);
            }
        });
    }

    private void resetSeekBar() {
        seekBarDaDieu.setProgress(0);
        seekBarRongTim.setProgress(0);
        seekBarRongXanh.setProgress(0);
    }

    private void setStatusCheckBox(boolean status){
        cbDaDieu.setEnabled(status);
        cbRongXanh.setEnabled(status);
        cbRongTim.setEnabled(status);
    }

}