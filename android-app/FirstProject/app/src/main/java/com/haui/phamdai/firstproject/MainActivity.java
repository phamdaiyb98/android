package com.haui.phamdai.firstproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText edtMin;
    EditText edtMax;
    Button btnGenerator;
    TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtMin = findViewById(R.id.edtMin);
        edtMax = findViewById(R.id.edtMax);
        btnGenerator = findViewById(R.id.btnGenerator);
        txtResult = findViewById(R.id.txtShowResult);

        btnGenerator.setOnClickListener(v -> {
            String minStr = edtMin.getText().toString();
            String maxStr = edtMax.getText().toString();
            if (minStr.isEmpty() || maxStr.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ!", Toast.LENGTH_SHORT).show();
            } else {
                int min = Integer.parseInt(minStr);
                int max = Integer.parseInt(maxStr);
                if (min >= max) {
                    Toast.makeText(this, "Dữ liệu không hợp lệ!", Toast.LENGTH_SHORT).show();
                } else {
                    Random random = new Random();
                    int a = max - min + 1;
                    txtResult.setText(String.valueOf(random.nextInt(a) + min));
                }
            }
        });
    }


}