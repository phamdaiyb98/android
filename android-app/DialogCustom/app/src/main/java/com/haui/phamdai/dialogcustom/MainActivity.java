package com.haui.phamdai.dialogcustom;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView txtLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtLogin = findViewById(R.id.textViewDangNhap);

        txtLogin.setOnClickListener(v -> {
            getDialogLogin();
        });
    }

    private void getDialogLogin() {
        Dialog dialog = new Dialog(this);
        // bỏ tiêu đề, phải viết trên dòng setContentView mới được
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_custom);
        // click ra ngoài không bị đóng dialog
        dialog.setCanceledOnTouchOutside(false);

        // ánh xạ
        EditText edtUsername = dialog.findViewById(R.id.edtUsername);
        EditText edtPassword = dialog.findViewById(R.id.edtPassword);
        Button btnLogin = dialog.findViewById(R.id.btnLogin);
        Button btnCancel = dialog.findViewById(R.id.btnCancel);

        btnLogin.setOnClickListener(v -> {
            String username = edtUsername.getText().toString();
            String password = edtPassword.getText().toString();

            if (username.equals("admin") && password.equals("123")) {
                Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Lỗi đăng nhập", Toast.LENGTH_SHORT).show();
            }
        });

        btnCancel.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }
}