package com.haui.phamdai.mediaonlinekpt;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {

    Button btnPlayPM3, btnPlayMP4, btnPause;
    ProgressBar pbLoading;
    VideoView videoViewMP4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlayPM3 = findViewById(R.id.buttonPlayMP3);
        btnPlayMP4 = findViewById(R.id.buttonPlayVideo);
        videoViewMP4 = findViewById(R.id.videoViewMP4);
        btnPause = findViewById(R.id.buttonPause);

        String url = "https://www.mboxdrive.com/Ocean_waves.mp3";
        MediaPlayer mediaPlayer = new MediaPlayer();
        btnPause.setOnClickListener(v -> {
            if(mediaPlayer.isPlaying()){
                mediaPlayer.pause();
            }else{
                mediaPlayer.start();
            }
        });

        pbLoading = findViewById(R.id.progressBarLoading);
        pbLoading.setVisibility(View.GONE);

        btnPlayPM3.setOnClickListener(v -> {
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            try {
                mediaPlayer.setDataSource(url);
                mediaPlayer.prepareAsync();
                // show progress bar
                pbLoading.setVisibility(View.VISIBLE);
                mediaPlayer.setOnPreparedListener(mp -> {
                    pbLoading.setVisibility(View.GONE);
                    mp.start();
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        btnPlayMP4.setOnClickListener(v -> {
            Uri uri = Uri.parse("https://khoapham.vn/download/vuoncaovietnam.mp4");
            videoViewMP4.setVideoURI(uri);
            videoViewMP4.setMediaController(new MediaController(MainActivity.this));
//            https://stackoverflow.com/questions/9765629/android-videoview-black-screen -> xóa màn hình đen lúc mới vào
            videoViewMP4.setZOrderOnTop(true);
            videoViewMP4.start();
        });
    }
}