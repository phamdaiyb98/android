package com.haui.phamdai.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnLogin;
    EditText edtUsername, edtPassword;
    CheckBox cbRemember;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapping();
        // sharedPreferences will create a file 'dataLogin.xml' in memory to save data
        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);

        edtUsername.setText(sharedPreferences.getString("username", ""));
        edtPassword.setText(sharedPreferences.getString("password", ""));
        cbRemember.setChecked(sharedPreferences.getBoolean("checked", false));

        btnLogin.setOnClickListener(v -> {
            String username = edtUsername.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();
            if (username.equals("phamdai") && password.equals("1234")) {
                Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                //if user check remember checkbox
                if (cbRemember.isChecked()) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("username", username);
                    editor.putString("password", password);
                    editor.putBoolean("checked", true);
                    editor.commit();
                }else{
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove("username");
                    editor.remove("password");
                    editor.remove("checked");
                    editor.commit();
                }
            } else {
                Toast.makeText(this, "Đăng nhập thất bại!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void mapping() {
        btnLogin = findViewById(R.id.buttonLogin);
        edtUsername = findViewById(R.id.editTextUsername);
        edtPassword = findViewById(R.id.editTextPassword);
        cbRemember = findViewById(R.id.checkBoxRemember);
    }
}