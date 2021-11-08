package com.haui.phamdai.medialocalkpt;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    Button btnPlayMP3;
    Button btnPlayMP4;
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlayMP3 = findViewById(R.id.buttonPlayMP3);
        btnPlayMP4 = findViewById(R.id.buttonPlayMP4);
        videoView = findViewById(R.id.videoViewMP4);

        btnPlayMP3.setOnClickListener(v -> {
            MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.mixkit_birds_in_the_jungle_2434);
            mediaPlayer.start();
        });

        btnPlayMP4.setOnClickListener(v -> {
            videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video));
            videoView.start();
            MediaController mediaController = new MediaController(this);
            mediaController.setMediaPlayer(videoView);
            videoView.setMediaController(mediaController);
        });
    }
}