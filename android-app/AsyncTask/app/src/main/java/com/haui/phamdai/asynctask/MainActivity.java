package com.haui.phamdai.asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnXuLy;
    TextView txtXuLy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnXuLy = findViewById(R.id.buttonXuLy);
        txtXuLy = findViewById(R.id.textviewXuLy);

        btnXuLy.setOnClickListener(v -> {
            new CongViec().execute();
        });
    }

    private class CongViec extends AsyncTask<Void, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            txtXuLy.setText("Bắt đầu\n");
        }

        @Override
        protected String doInBackground(Void... voids) {
            for (int i = 1; i <= 5; i++) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress("Xong việc " + i+"\n");
            }
            return "Xong rồi";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            txtXuLy.append(s);
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            txtXuLy.append(values[0]);
        }
    }


}