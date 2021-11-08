package com.haui.phamdai.internationalapp.changelanguage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.haui.phamdai.internationalapp.App;
import com.haui.phamdai.internationalapp.MainActivity;
import com.haui.phamdai.internationalapp.R;
import com.haui.phamdai.internationalapp.data.SharedPrefs;
import com.haui.phamdai.internationalapp.utils.LanguageAdapter;
import com.haui.phamdai.internationalapp.utils.LanguageUtils;

import java.util.Locale;

public class ChangeLanguageActivity extends AppCompatActivity {

    ListView listViewNgonNgu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(null);
        setContentView(R.layout.activity_change_language);

        listViewNgonNgu = findViewById(R.id.listviewNgonNgu);

        LanguageAdapter languageAdapter = new LanguageAdapter(this,
                R.layout.item_language,
                LanguageUtils.getLanguageList());
        listViewNgonNgu.setAdapter(languageAdapter);

        listViewNgonNgu.setOnItemClickListener((parent, view, position, id) ->
        {
//            SharedPrefs.getInstance().put("selectedLanguage", LanguageUtils.getLanguageList().get(position).getCode());
            Toast.makeText(this, LanguageUtils.getLanguageList().get(position).getCode(), Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(ChangeLanguageActivity.this, MainActivity.class);
            startActivity(intent);

        });

    }
}