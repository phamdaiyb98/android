package com.haui.phamdai.intentbaitapkpt;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.Collections;

public class ImageActivity extends Activity {

    TableLayout myTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        myTable = findViewById(R.id.tableLayoutImage);

        int row = 4;
        int column = 3;

        //mix array
        Collections.shuffle(MainActivity.arrayName);

        // create row
        for (int i = 1; i <= row; i++) {
            TableRow tableRow = new TableRow(this);

            //create column => ImageView
            for (int j = 1; j <= column; j++) {
                ImageView imageView = new ImageView(this);
                imageView.setPadding(10, 10, 10, 10);

                Resources r = getResources();
                int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, r.getDisplayMetrics());

                TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(px, px);
                imageView.setLayoutParams(layoutParams);

                int index = column * (i - 1) + j - 1;
                int idImage = getResources().getIdentifier(MainActivity.arrayName.get(index), "drawable", getPackageName());
                imageView.setImageResource(idImage);
                // add imageview vào tablerow
                tableRow.addView(imageView);

                imageView.setOnClickListener(v -> {
                    Intent intent = new Intent();
                    intent.putExtra("imageName", MainActivity.arrayName.get(index));
                    setResult(RESULT_OK, intent);
                    finish();
                });
            }

            //add tablerow vào table
            myTable.addView(tableRow);
        }
    }
}