package com.haui.phamdai.internationalapp;

import android.app.Application;

import com.google.gson.Gson;

public class App extends Application {
    private static App instance;
    private static Gson gson;

    public static App self() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        gson = new Gson();
    }

    public Gson getGson() {
        return gson;
    }
}
