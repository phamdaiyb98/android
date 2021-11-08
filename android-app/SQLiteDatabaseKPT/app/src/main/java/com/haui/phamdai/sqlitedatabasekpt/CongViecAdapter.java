package com.haui.phamdai.sqlitedatabasekpt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CongViecAdapter extends BaseAdapter {

    private MainActivity context;
    private int layout;
    private List<CongViec> congViecs;

    public CongViecAdapter(MainActivity context, int layout, List<CongViec> congViecs) {
        this.context = context;
        this.layout = layout;
        this.congViecs = congViecs;
    }

    @Override
    public int getCount() {
        return congViecs.size();
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
        TextView txtTen;
        ImageView ivEdit;
        ImageView ivDelete;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holder.txtTen = convertView.findViewById(R.id.textviewTen);
            holder.ivEdit = convertView.findViewById(R.id.imageviewEdit);
            holder.ivDelete = convertView.findViewById(R.id.imageviewDelete);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final CongViec congViec = congViecs.get(position);

        holder.txtTen.setText(congViec.getTenCV());

        holder.ivEdit.setOnClickListener(v -> {
            context.createDialog(Action.UPDATE, congViec);
        });

        holder.ivDelete.setOnClickListener(v -> {
            context.createDeleteDialog(congViec);
        });
        return convertView;
    }
}
