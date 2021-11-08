package com.haui.phamdai.internationalapp.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.haui.phamdai.internationalapp.App;

// class lưu các giá trị dạng key-value để trao đổi giữa các màn hình
public class SharedPrefs {
    private static final String PREFS_NAME = "share_prefs";
    private static SharedPrefs instance;
    private SharedPreferences sharedPreferences;

    private SharedPrefs() {
        sharedPreferences = App.self().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public static SharedPrefs getInstance() {
        if (instance == null) {
            instance = new SharedPrefs();
        }
        return instance;
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String key, Class<T> anonymousClass) {
        if (anonymousClass == String.class) {
            return (T) sharedPreferences.getString(key, "");
        } else if (anonymousClass == Boolean.class) {
            return (T) Boolean.valueOf(sharedPreferences.getBoolean(key, false));
        } else if (anonymousClass == Float.class) {
            return (T) Float.valueOf(sharedPreferences.getFloat(key, 0));
        } else if (anonymousClass == Integer.class) {
            return (T) Integer.valueOf(sharedPreferences.getInt(key, 0));
        } else if (anonymousClass == Long.class) {
            return (T) Long.valueOf(sharedPreferences.getLong(key, 0));
        } else {
            return (T) App.self().getGson().fromJson(sharedPreferences.getString(key, ""), anonymousClass);
        }
    }

    public <T> void put(String key, T data) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (data instanceof String) {
            editor.putString(key, (String) data);
        } else if (data instanceof Boolean) {
            editor.putBoolean(key, (Boolean) data);
        } else if (data instanceof Float) {
            editor.putFloat(key, (Float) data);
        } else if (data instanceof Integer) {
            editor.putInt(key, (Integer) data);
        } else if (data instanceof Long) {
            editor.putLong(key, (Long) data);
        } else {
            editor.putString(key, App.self().getGson().toJson(data));
        }
        editor.apply();
    }

    public void clear() {
        sharedPreferences.edit().clear().apply();
    }
}
