package com.haui.phamdai.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class TraiCayAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<TraiCay> traiCays;

    public TraiCayAdapter(Context context, int layout, List<TraiCay> traiCays) {
        this.context = context;
        this.layout = layout;
        this.traiCays = traiCays;
    }

    @Override
    public int getCount() {
        return traiCays.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    // class dùng để giữ ánh xạ cho view, chỉ ánh xạ 1 lần
    private class ViewHolder {
        TextView txtTen, txtMoTa;
        ImageView imageView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            viewHolder = new ViewHolder();
            //anh xa view
            viewHolder.txtTen = convertView.findViewById(R.id.textViewTen);
            viewHolder.txtMoTa = convertView.findViewById(R.id.textViewMoTa);
            viewHolder.imageView = convertView.findViewById(R.id.imageViewHinh);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //gan gia tri
        TraiCay traiCay = traiCays.get(position);

        viewHolder.txtTen.setText(traiCay.getTen());
        viewHolder.txtMoTa.setText(traiCay.getMoTa());
        viewHolder.imageView.setImageResource(traiCay.getHinhAnh());

        // gan animation
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.scale_list);
        convertView.startAnimation(animation);

        return convertView;
    }
}
