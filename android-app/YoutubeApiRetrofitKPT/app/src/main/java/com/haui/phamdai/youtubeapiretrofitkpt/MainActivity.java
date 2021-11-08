package com.haui.phamdai.youtubeapiretrofitkpt;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ApiService.apiService
                .getPlayList("snippet", ApiParam.VIDEO_LIST_ID, "50", ApiParam.API_KEY)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            assert response.body() != null;
                            JSONObject object = new JSONObject(response.body().string());

                            JSONArray items = object.getJSONArray("items");
                            String title = "", imageUrl = "", id = "";
                            for (int i = 0; i < items.length(); i++) {
                                JSONObject item = items.getJSONObject(i);
                                JSONObject snippet = item.getJSONObject("snippet");
                                title = snippet.getString("title");

                                JSONObject thumbnails = snippet.getJSONObject("thumbnails");
                                JSONObject medium = thumbnails.getJSONObject("medium");
                                imageUrl = medium.getString("url");

                                JSONObject resourceId = snippet.getJSONObject("resourceId");
                                id = resourceId.getString("videoId");

                                Log.d("AAA", title + " - " + imageUrl + " - " + id);
                            }
                        } catch (JSONException | IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
    }
}