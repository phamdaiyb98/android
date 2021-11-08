package com.haui.phamdai.sqlitesaveimagekpt;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.ref.WeakReference;

public class UpdateDataDoVatActivity extends AppCompatActivity {

    Button btnAdd, btnCancel, btnTest;
    EditText edtName, edtDescription;
    ImageView ivPhoto, ivCamera, ivFolder;
    TextView txtTitle;
    DoVat doVatUpdate;
    int position;
    boolean isInsert = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_do_vat);

        map();

        Intent intent = getIntent();
        Bundle data = intent.getBundleExtra("data");
        if (data == null) {
            // insert
            isInsert = true;
        } else {
            // update
            isInsert = false;
            doVatUpdate = (DoVat) data.getSerializable("doVat");
            position = data.getInt("pos");

            txtTitle.setText("Sửa thông tin đồ vật");
            btnAdd.setText("Sửa");
            edtName.setText(doVatUpdate.getTen());
            edtDescription.setText(doVatUpdate.getMoTa());
            byte[] hinhAnh = doVatUpdate.getHinhAnh();
            Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh, 0, hinhAnh.length);
            ivPhoto.setImageBitmap(bitmap);
        }

        ivCamera.setOnClickListener(v -> {
//            boolean cameraPermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
//                    == PackageManager.PERMISSION_GRANTED;
//            boolean isShowEduUI = ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA);
//            if (cameraPermission) {
//                //take photo
//                takePhoto();
//            } else if (isShowEduUI) {
//                //show Education UI
//                showEduUI();
//            } else {
//                //request permission
//                requestCameraPermissionLauncher.launch(Manifest.permission.CAMERA);
//            }
        });

        ivFolder.setOnClickListener(v -> {
            imageStorageResultLauncher.launch("image/*");
        });

        btnAdd.setOnClickListener(v -> {
            // chuyển data của imageview -> byte[]
            BitmapDrawable bitmapDrawable = (BitmapDrawable) ivPhoto.getDrawable();
            Bitmap bitmap = bitmapDrawable.getBitmap();
            ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
            // quality : 100 là mặc định, càng giảm thì chất lượng càng cao, quality : 1 là cao nhất
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArray);
            byte[] hinhAnh = byteArray.toByteArray();

            String ten = edtName.getText().toString().trim();
            String moTa = edtDescription.getText().toString().trim();

            if (isInsert) {
                // insert
                MainActivity.database.insertDoVat(ten, moTa, hinhAnh);
            } else {
                // update
                DoVat update = new DoVat(doVatUpdate.getId(), ten, moTa, hinhAnh);
                MainActivity.database.updateDoVat(update);
            }

            Toast.makeText(this, "Đã cập nhật dữ liệu", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class));
        });

        btnCancel.setOnClickListener(v -> startActivity(new Intent(this, MainActivity.class)));
    }

    private void showEduUI() {
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
                    Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show();
                }
            });

    private final ActivityResultLauncher<Void> imageResultLauncher
            = registerForActivityResult(new ActivityResultContracts.TakePicturePreview(),
            new ActivityResultCallback<Bitmap>() {
                @Override
                public void onActivityResult(Bitmap result) {
                    ivPhoto.setImageBitmap(result);
                }
            });

    private final ActivityResultLauncher<String> imageStorageResultLauncher
            = registerForActivityResult(new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri result) {
                    try {
                        InputStream inputStream = getContentResolver().openInputStream(result);
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        ivPhoto.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            });

    private void map() {
        btnAdd = findViewById(R.id.buttonAdd);
        btnCancel = findViewById(R.id.buttonCancel);
        edtName = findViewById(R.id.editTextName);
        edtDescription = findViewById(R.id.editTextDescription);
        ivCamera = findViewById(R.id.imageViewCamera);
        ivPhoto = findViewById(R.id.imageViewPhoto);
        ivFolder = findViewById(R.id.imageViewFolder);
        txtTitle = findViewById(R.id.textViewUpdateDataTitle);

        btnTest = findViewById(R.id.buttonTest);
    }
}