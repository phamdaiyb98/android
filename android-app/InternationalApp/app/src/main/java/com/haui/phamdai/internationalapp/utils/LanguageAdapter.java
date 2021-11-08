package com.haui.phamdai.internationalapp.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.haui.phamdai.internationalapp.BuildConfig;
import com.haui.phamdai.internationalapp.R;
import com.haui.phamdai.internationalapp.changelanguage.Language;

import java.util.List;

public class LanguageAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Language> languageList;

    public LanguageAdapter(Context context, int layout, List<Language> languageList) {
        this.context = context;
        this.layout = layout;
        this.languageList = languageList;
    }

    @Override
    public int getCount() {
        return languageList.size();
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
        private TextView textViewNgonNgu;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_language, null);
            viewHolder = new ViewHolder();

            viewHolder.textViewNgonNgu = convertView.findViewById(R.id.textViewNgonNgu);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textViewNgonNgu.setText(languageList.get(position).getName());
        return convertView;
    }
}
