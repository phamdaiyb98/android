package com.haui.phamdai.multilanguageapp.changelanguage;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.haui.phamdai.multilanguageapp.R;
import com.haui.phamdai.multilanguageapp.data.ItemClickListener;
import com.haui.phamdai.multilanguageapp.model.Language;
import com.haui.phamdai.multilanguageapp.utils.LanguageUtils;

public class ChangeLanguageActivity extends AppCompatActivity {

    private LanguageAdapter mLanguageAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityChangeLanguageBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_change_language);
        mLanguageAdapter = new LanguageAdapter(LanguageUtils.getLanguageData());
        mLanguageAdapter.setListener((position, language) -> {
            if (!language.getmCode().equals(LanguageUtils.getCurrentLanguage().getmCode())) {
                onChangeLanguageSuccessfully(language);
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(ChangeLanguageActivity.this);
        binding.recyclerViewLanguage.setLayoutManager(layoutManager);
        binding.recyclerViewLanguage.setAdapter(mLanguageAdapter);
    }

    private void onChangeLanguageSuccessfully(final Language language) {
        mLanguageAdapter.setCurrentLanguage(language);
        LanguageUtils.changeLanguage(language);
        setResult(RESULT_OK, new Intent());
        finish();
    }
}