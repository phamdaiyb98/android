package com.haui.phamdai.drawableshapekpt;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    Button clipButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageViewOakTree);
        clipButton = findViewById(R.id.buttonClip);

        imageView.setImageLevel(1000);

        ClipDrawable clipDrawable = (ClipDrawable) imageView.getDrawable();

        CountDownTimer countDownTimer = new CountDownTimer(60000,500) {
            @Override
            public void onTick(long millisUntilFinished) {
                if(clipDrawable.getLevel()>=10000){
                    imageView.setImageLevel(1000);
                }
                imageView.setImageLevel(clipDrawable.getLevel() + 500);
            }

            @Override
            public void onFinish() {

            }
        };

        clipButton.setOnClickListener(v -> {
            // Handler giống CountDownTimer ở đây handler sẽ chờ 2s sau đó cứ mỗi 0.5s chạy run() một lần
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(clipDrawable.getLevel()>=10000){
                        imageView.setImageLevel(1000);
                    }
                    imageView.setImageLevel(clipDrawable.getLevel() + 500);
                    handler.postDelayed(this,500);
                }
            }, 2000);
//            countDownTimer.start();
        });
    }
}