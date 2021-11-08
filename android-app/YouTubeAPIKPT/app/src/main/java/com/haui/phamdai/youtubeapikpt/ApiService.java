package com.haui.phamdai.youtubeapikpt;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

//    "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId=" + mListVideoId + "&maxResults=50&key=" + ApiParam.API_KEY;

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
