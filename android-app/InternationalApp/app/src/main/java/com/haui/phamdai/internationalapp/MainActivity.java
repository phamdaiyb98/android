package com.haui.phamdai.internationalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.haui.phamdai.internationalapp.changelanguage.ChangeLanguageActivity;
import com.haui.phamdai.internationalapp.data.SharedPrefs;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button btnXacNhan;
    EditText edtHoTen;
    EditText edtEmail;
    EditText edtSoDienThoai;
    TextView txtThongTin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();

        btnXacNhan.setOnClickListener(v -> {
            String hoTen = edtHoTen.getText().toString();
            String email = edtEmail.getText().toString();
            String soDienThoai = edtSoDienThoai.getText().toString();

            String textChaoBan = getResources().getString(R.string.text_chaoBan);
            String textEmail = getResources().getString(R.string.text_email);
            String textPhone = getResources().getString(R.string.text_soDienThoai);

            txtThongTin.setText(textChaoBan + ": " + hoTen + "\n" + textEmail + ": " + email + "\n" + textPhone + ": " + soDienThoai);
            Toast.makeText(this, SharedPrefs.getInstance().get("selectedLanguage", String.class), Toast.LENGTH_SHORT).show();
        });
    }

    private void AnhXa() {
        btnXacNhan = findViewById(R.id.buttonXacNhan);
        edtHoTen = findViewById(R.id.editTextTextHoTen);
        edtEmail = findViewById(R.id.editTextEmail);
        edtSoDienThoai = findViewById(R.id.editTextSoDienThoai);
        txtThongTin = findViewById(R.id.textViewThongTin);
    }

    public void chooseLanguage(View view) {
        Intent intent = new Intent(this, ChangeLanguageActivity.class);
        startActivity(intent);
    }
}