package com.haui.phamdai.fragmentcommunicatorkpt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtTitle;
    Button btnChangeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtTitle = findViewById(R.id.textViewTitle_Main);
        btnChangeText = findViewById(R.id.buttonChangeText_Main);

        btnChangeText.setOnClickListener(v -> {
            AFragment fragment = (AFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerA_Main);
            fragment.setNoiDung("Change by Activity");
        });
    }
}