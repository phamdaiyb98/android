package com.haui.phamdai.internationalapp.utils;

import android.app.Application;

import com.haui.phamdai.internationalapp.App;
import com.haui.phamdai.internationalapp.R;
import com.haui.phamdai.internationalapp.changelanguage.Language;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LanguageUtils {
    static List<Language> languageList = new ArrayList<>();
    static List<String> languageName =
            Arrays.asList(App.self().getResources().getStringArray(R.array.language_names));
    static List<String> languageCode =
            Arrays.asList(App.self().getResources().getStringArray(R.array.language_codes));

    public static List<Language> getLanguageList() {
        if (languageCode.size() != languageName.size()) {
            //make sure 2 array has same size
            return languageList;
        }
        for (int i = 0; i < languageCode.size(); i++) {
            languageList.add(new Language(i, languageCode.get(i), languageName.get(i)));
        }
        return languageList;
    }

}
