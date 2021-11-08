package com.haui.phamdai.readrsskpt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvTieuDe;
    ArrayList<String> arrayTitle;
    ArrayList<String> arrayLink;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvTieuDe = findViewById(R.id.listviewTieuDe);
        arrayTitle = new ArrayList<>();
        arrayLink = new ArrayList<>();

        adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,
                arrayTitle);
        lvTieuDe.setAdapter(adapter);

        new ReadRss().execute("https://thanhnien.vn/rss/cong-nghe/san-pham-moi.rss");
        lvTieuDe.setOnItemClickListener((parent, view, position, id) -> {
//            Toast.makeText(this, arrayLink.get(position), Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, NewsActivity.class);
            intent.putExtra("link", arrayLink.get(position));
            startActivity(intent);
        });
    }

    private class ReadRss extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder content = new StringBuilder();
            try {
                URL url = new URL(strings[0]);
                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    content.append(line);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            XMLDOMParser parser = new XMLDOMParser();

            Document document = parser.getDocument(s);

            NodeList nodeList = document.getElementsByTagName("item");

            String title;
            String link;

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                title = parser.getValue(element, "title");
                link = parser.getValue(element, "link");

                arrayTitle.add(title);
                arrayLink.add(link);
            }

            adapter.notifyDataSetChanged();
        }
    }
}