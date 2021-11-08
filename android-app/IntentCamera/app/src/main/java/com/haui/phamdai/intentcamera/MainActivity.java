package com.haui.phamdai.intentcamera;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.MutableLiveData;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button btnCamera;
    ImageView imageViewPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCamera = findViewById(R.id.buttonCamera);
        imageViewPhoto = findViewById(R.id.imageViewPhoto);

        btnCamera.setOnClickListener(v -> {
            boolean cameraPermission = ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_GRANTED;
            boolean isShowEducationUI
                    = ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.CAMERA);
            if (cameraPermission) {
                // take photo
                takePhoto();
            } else if (isShowEducationUI) {
                // show education UI then request permission
                getDialogCameraEduUI();
				requestCameraPermissionLauncher.launch(Manifest.permission.CAMERA);
            } else {
                requestCameraPermissionLauncher.launch(Manifest.permission.CAMERA);
            }
        });

    }

    private void getDialogCameraEduUI() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.camera_permission_education);
        dialog.setCanceledOnTouchOutside(false);

        Button button = dialog.findViewById(R.id.buttonOK);
        button.setOnClickListener(v -> {
            dialog.dismiss();
            requestCameraPermissionLauncher.launch(Manifest.permission.CAMERA);
        });

        dialog.show();
    }

    private void takePhoto() {
        imageResultLauncher.launch(null);
    }

    private final ActivityResultLauncher<String> requestCameraPermissionLauncher
            = registerForActivityResult(new ActivityResultContracts.RequestPermission(),
            result -> {
                if (result) {
                    takePhoto();
                } else {
                    Toast.makeText(MainActivity.this, "Camera permission denied", Toast.LENGTH_LONG).show();
                }
            });

    private final ActivityResultLauncher<Void> imageResultLauncher
            = registerForActivityResult(new ActivityResultContracts.TakePicturePreview(),
            result -> imageViewPhoto.setImageBitmap(result));

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        if (requestCode == PERMISSION_REQUEST_CAMERA && resultCode == RESULT_OK && data != null) {
//            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
//            imageViewPhoto.setImageBitmap(bitmap);
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        if (requestCode == PERMISSION_REQUEST_CAMERA && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//            // granted
//            resultLauncher.launch(imageUri);
//            openCamera();
//        } else {
//            // denied
//            Toast.makeText(this, R.string.cameraPermissionDenied, Toast.LENGTH_SHORT).show();
//        }
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//    }
//
//    private void requestCameraPermission() {
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
//            // open camera
//            openCamera();
//        } else if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
//            // show education permission UI
//            Toast.makeText(this, R.string.educationCameraUI, Toast.LENGTH_SHORT).show();
//            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CAMERA);
//        } else {
//            // request permission
//            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CAMERA);
//        }
//    }

//    private void openCamera() {
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        startActivityForResult(intent, PERMISSION_REQUEST_CAMERA);
//    }
}