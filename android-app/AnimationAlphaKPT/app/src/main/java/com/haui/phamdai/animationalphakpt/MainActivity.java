package com.haui.phamdai.animationalphakpt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imageViewAlpha;
    ImageView imageViewRotate;
    ImageView imageViewScale;
    ImageView imageViewTranslate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageViewAlpha = findViewById(R.id.imageViewAlpha);
        imageViewRotate = findViewById(R.id.imageViewRotate);
        imageViewScale = findViewById(R.id.imageViewScale);
        imageViewTranslate = findViewById(R.id.imageViewTranslate);

        Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        Animation animRotate = AnimationUtils.loadAnimation(this, R.anim.anim_rotate);
        Animation animScale = AnimationUtils.loadAnimation(this, R.anim.anim_scale);
        Animation animTranslate = AnimationUtils.loadAnimation(this,R.anim.anim_translate);

//        imageViewScale.startAnimation(animScale);

        imageViewAlpha.setOnClickListener(v -> {
            v.startAnimation(animAlpha);
        });

        imageViewRotate.setOnClickListener(v -> {
            v.startAnimation(animRotate);
        });

        imageViewScale.setOnClickListener(v -> {
            v.startAnimation(animScale);
        });

        imageViewTranslate.setOnClickListener(v -> {
            v.startAnimation(animTranslate);
        });
    }
}