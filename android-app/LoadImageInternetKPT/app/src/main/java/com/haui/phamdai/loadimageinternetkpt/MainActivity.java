package com.haui.phamdai.loadimageinternetkpt;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    Button btnLoadImage;
    ImageView ivInternet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLoadImage = findViewById(R.id.buttonLoadImage);
        ivInternet = findViewById(R.id.imageViewInternet);

        btnLoadImage.setOnClickListener(v -> {
            new LoadImage().execute("https://cdn.tgdd.vn/Files/2018/11/20/1132543/android-google_1280x720-800-resize.jpg");
        });
    }

    private class LoadImage extends AsyncTask<String, Void, Bitmap>{

        Bitmap bitmapInternet = null;

        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                URL imageUrl = new URL(strings[0]);
                InputStream inputStream = imageUrl.openConnection().getInputStream();
                bitmapInternet = BitmapFactory.decodeStream(inputStream);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmapInternet;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            ivInternet.setImageBitmap(bitmap);
        }
    }
}