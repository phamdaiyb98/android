package com.haui.phamdai.youtubeapiretrofitkpt;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    ApiService apiService = new Retrofit.Builder()
            .baseUrl("https://www.googleapis.com/")
            .build()
            .create(ApiService.class);

    @GET("youtube/v3/playlistItems")
    Call<ResponseBody> getPlayList(@Query("part") String part,
                                 @Query("playlistId") String playlistId,
                                 @Query("maxResults") String maxResults,
                                 @Query("key") String key
    );
}
