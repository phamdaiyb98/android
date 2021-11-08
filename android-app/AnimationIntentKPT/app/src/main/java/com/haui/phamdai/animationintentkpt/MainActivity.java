package com.haui.phamdai.animationintentkpt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMain = findViewById(R.id.buttoMain);
        btnMain.setOnClickListener(v -> {
            startActivity(new Intent(this, SecondActivity.class));
            overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit);
        });
    }
}