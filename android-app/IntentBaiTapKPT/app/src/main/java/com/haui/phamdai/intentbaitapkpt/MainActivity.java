package com.haui.phamdai.intentbaitapkpt;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<String> arrayName;
    ImageView imageViewAbove;
    ImageView imageViewBelow;
    String originImageName;
    TextView textViewScore;
    int score = 100;
    SharedPreferences scoreSharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageViewAbove = findViewById(R.id.imageViewAbove);
        imageViewBelow = findViewById(R.id.imageViewBelow);
        textViewScore = findViewById(R.id.textViewScore);

        scoreSharedPrefs = getSharedPreferences("saveScore", MODE_PRIVATE);
        score = scoreSharedPrefs.getInt("score", 100);
        textViewScore.setText(score + "");

        String[] listName = getResources().getStringArray(R.array.list_name);
        arrayName = new ArrayList<>(Arrays.asList(listName));

        setImageViewAbove();

        imageViewBelow.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ImageActivity.class);
            imageResultLauncher.launch(intent);
//            startActivityForResult(new Intent(MainActivity.this, ImageActivity.class), REQUEST_CODE_IMAGE);
        });
    }

    private void saveScore() {
        SharedPreferences.Editor editor = scoreSharedPrefs.edit();
        editor.putInt("score", score);
        editor.commit();
    }

    //    new versions
    private final ActivityResultLauncher<Intent> imageResultLauncher
            = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getData() != null) {
                    String imageNameReceive = result.getData().getStringExtra("imageName");
                    int idImage = getResources().getIdentifier(imageNameReceive, "drawable", getPackageName());
                    imageViewBelow.setImageResource(idImage);
                    //compare image name
                    if (originImageName.equals(imageNameReceive)) {
//                        Toast.makeText(this, "Chính xác! Bạn được cộng 10 điểm^^", Toast.LENGTH_SHORT).show();
                        score += 10;

                        new CountDownTimer(2000, 100) {
                            @Override
                            public void onTick(long millisUntilFinished) {

                            }

                            @Override
                            public void onFinish() {
                                setImageViewAbove();
                            }
                        }.start();
                    } else {
//                        Toast.makeText(this, "Sai rồi! Bạn bị trừ 5 điểm^^", Toast.LENGTH_SHORT).show();
                        score -= 5;
                    }
                } else {
                    Toast.makeText(this, "Bạn chưa chọn hình! Muốn xem lại bị trừ 15 điểm^^", Toast.LENGTH_SHORT).show();
                    score -= 15;
                }
                saveScore();
                textViewScore.setText(score + "");
            });

//    old versions
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_IMAGE && data != null) {
//            String imageNameReceive = data.getStringExtra("imageName");
//            int idImage = getResources().getIdentifier(imageNameReceive, "drawable", getPackageName());
//            imageViewBelow.setImageResource(idImage);
//            //compare image name
//            if (originImageName.equals(imageNameReceive)) {
//                Toast.makeText(this, "Chính xác! Bạn được cộng 10 điểm^^", Toast.LENGTH_SHORT).show();
//                score += 10;
//
//                new CountDownTimer(2000, 100) {
//                    @Override
//                    public void onTick(long millisUntilFinished) {
//
//                    }
//
//                    @Override
//                    public void onFinish() {
//                        setImageViewAbove();
//                    }
//                }.start();
//            } else {
//                Toast.makeText(this, "Sai rồi! Bạn bị trừ 5 điểm^^", Toast.LENGTH_SHORT).show();
//                score -= 5;
//            }
//            textViewScore.setText(score + "");
//        }
//
//        if (requestCode == REQUEST_CODE_IMAGE && resultCode == RESULT_CANCELED) {
//            Toast.makeText(this, "Bạn chưa chọn hình! Muốn xem lại bị trừ 15 điểm^^", Toast.LENGTH_SHORT).show();
//            score -= 15;
//            textViewScore.setText(score + "");
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuReload) {
            setImageViewAbove();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.reload, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void setImageViewAbove() {
        Collections.shuffle(arrayName);
        originImageName = arrayName.get(5);
        int idImage = getResources().getIdentifier(arrayName.get(5), "drawable", getPackageName());
        imageViewAbove.setImageResource(idImage);
    }
}