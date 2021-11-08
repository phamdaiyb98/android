package com.haui.phamdai.listfragmentkpt;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.ListFragment;

public class DanhSachFragment extends ListFragment {

    String[] arrayCity = {"Hà Nội", "Hải Phòng", "Yên Bái", "Cà Mau", "Trường Sa", "Hoàng Sa"};
    ArrayAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, arrayCity);
        setListAdapter(adapter);
        return inflater.inflate(R.layout.fragment_danh_sach, container, false);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Toast.makeText(getActivity(), arrayCity[position], Toast.LENGTH_SHORT).show();
    }
}
