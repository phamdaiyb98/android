package com.haui.phamdai.youtubeapikpt;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {

    List<Video> mVideoList;
    PlayListActivity context;

    public VideoAdapter(List<Video> mVideoList, PlayListActivity context) {
        this.mVideoList = mVideoList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_video, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoAdapter.ViewHolder holder, int position) {
        Video video = mVideoList.get(position);

        Picasso.get().load(video.getImageUrl()).into(holder.ivVideo);
        holder.txtTitle.setText(video.getTitle());
        holder.llVideo.setOnClickListener(v -> {
            Intent intent = new Intent(context, PlayVideoActivity.class);
            intent.putExtra("id", video.getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return mVideoList.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivVideo;
        TextView txtTitle;
        LinearLayout llVideo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivVideo = itemView.findViewById(R.id.ivVideo);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            llVideo = itemView.findViewById(R.id.llVideo);
        }
    }
}
