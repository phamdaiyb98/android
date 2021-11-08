package com.haui.phamdai.youtubeapikpt;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.youtube.player.YouTubeBaseActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlayListActivity extends YouTubeBaseActivity {

    private List<Video> mVideoList;
    private VideoAdapter mVideoAdapter;
    RecyclerView rvVideoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_list);

        mVideoList = new ArrayList<>();
        rvVideoList = findViewById(R.id.rvVideoList);

        mVideoAdapter = new VideoAdapter(mVideoList, this);
        rvVideoList.setAdapter(mVideoAdapter);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        rvVideoList.setLayoutManager(manager);

        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(this, manager.getOrientation());
        rvVideoList.addItemDecoration(itemDecoration);

        rvVideoList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1)) {
                    // todo: lấy nextToken từ json response thêm nó vào url để lấy trang videoList tiếp theo(50-100)
                    Toast.makeText(PlayListActivity.this, "hết rồi load nữa đi, hơn 190 video cơ mà!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        getPlayList();

//        String url = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId=" + mListVideoId + "&maxResults=50&key=" + ApiParam.API_KEY;
//        readGoogleJson(url);
    }

    private void getPlayList() {
        ApiService.apiService
                .getPlayList("snippet", ApiParam.VIDEO_LIST_ID, "50", ApiParam.API_KEY)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            if (response.body() != null) {
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

                                    mVideoList.add(new Video(id, title, imageUrl));
                                }
                                mVideoAdapter.notifyDataSetChanged();
                            } else {
                                Toast.makeText(PlayListActivity.this, "No data", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException | IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(PlayListActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

//    private void readGoogleJson(String url) {
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
//                response -> {
//                    try {
//                        JSONArray items = response.getJSONArray("items");
//
//                        mVideoList.clear();
//                        String title = "", imageUrl = "", id = "";
//                        for (int i = 0; i < items.length(); i++) {
//                            JSONObject item = items.getJSONObject(i);
//                            JSONObject snippet = item.getJSONObject("snippet");
//                            title = snippet.getString("title");
//
//                            JSONObject thumbnails = snippet.getJSONObject("thumbnails");
//                            JSONObject medium = thumbnails.getJSONObject("medium");
//                            imageUrl = medium.getString("url");
//
//                            JSONObject resourceId = snippet.getJSONObject("resourceId");
//                            id = resourceId.getString("videoId");
//
//                            mVideoList.add(new Video(id, title, imageUrl));
//                        }
//
//                        mVideoAdapter.notifyDataSetChanged();
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }, error -> {
//
//        });
//
//        requestQueue.add(request);
//    }
}