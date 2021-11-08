package com.haui.phamdai.gridviewkpt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import org.xmlpull.v1.XmlPullParser;

import java.util.List;

public class HinhAnhAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<HinhAnh> hinhAnhs;

    public HinhAnhAdapter(Context context, int layout, List<HinhAnh> hinhAnhs) {
        this.context = context;
        this.layout = layout;
        this.hinhAnhs = hinhAnhs;
    }

    @Override
    public int getCount() {
        return hinhAnhs.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder {
        ImageView imageView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            viewHolder.imageView = convertView.findViewById(R.id.imageviewHinhAnh);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        HinhAnh hinhAnh = hinhAnhs.get(position);

        viewHolder.imageView.setImageResource(hinhAnh.getHinh());

        return convertView;
    }
}
