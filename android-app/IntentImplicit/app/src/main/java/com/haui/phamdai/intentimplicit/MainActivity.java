package com.haui.phamdai.intentimplicit;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView imageViewBrowser;
    ImageView imageViewMessage;
    ImageView imageViewCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageViewBrowser = findViewById(R.id.imageViewBrowser);
        imageViewMessage = findViewById(R.id.imageViewMessage);
        imageViewCall = findViewById(R.id.imageViewCall);

        imageViewBrowser.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://khoapham.vn"));
            startActivity(intent);
        });

        imageViewMessage.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SENDTO);
            intent.putExtra("sms_body","Khoa Pham Training!");
            intent.setData(Uri.parse("sms:0325808383"));
            startActivity(intent);
        });

        imageViewCall.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_AUTO_REVOKE_PERMISSIONS);
            startActivity(intent);
//            callPhone();
        });
    }

    private void callPhone(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)== PackageManager.PERMISSION_GRANTED){
//            check xem quyen nay duoc he thong cap quyen chua
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:0325808383"));
            startActivity(intent);
        }else if(shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)){
//            co can show education UI ko
            Toast.makeText(this, "Education UI", Toast.LENGTH_SHORT).show();
        }else{
//            hien thi dialog xin quyen
            requestPermissionLauncher.launch(Manifest.permission.CALL_PHONE);
        }
    }

    // ham nhan ket qua response tu nguoi dung
    private ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted ->{
                if(isGranted){
                    Toast.makeText(this, "Granted", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Denied", Toast.LENGTH_SHORT).show();
                }
            });
}