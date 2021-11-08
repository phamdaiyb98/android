package com.haui.phamdai.androidwebservice;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SinhVienAdapter extends RecyclerView.Adapter<SinhVienAdapter.ViewHolder> {

    private List<SinhVien> sinhVienList;
    MainActivity context;

    public SinhVienAdapter(List<SinhVien> sinhVienList, MainActivity context) {
        this.sinhVienList = sinhVienList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sinh_vien, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SinhVienAdapter.ViewHolder holder, int position) {
        SinhVien sinhVien = sinhVienList.get(position);

        holder.txtHoTen.setText(sinhVien.getHoTen());
        holder.txtNamSinh.setText(String.valueOf(sinhVien.getNamSinh()));
        holder.txtDiaChi.setText(sinhVien.getDiaChi());
        holder.ivEdit.setOnClickListener(v -> {
            Intent intent = new Intent(context, AddSinhVienActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("sinhVien", sinhVien);
            intent.putExtra("data", bundle);
            context.startActivity(intent);
        });
        holder.ivDelete.setOnClickListener(v -> {
            xacNhanXoa(sinhVien.getHoTen(), sinhVien.getId(), position);
        });
    }

    private void xacNhanXoa(String ten, int id, int removeIndex) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Bạn có muốn xóa " + ten + " không?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                context.xoaSinhVien(id, removeIndex);
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

    @Override
    public int getItemCount() {
        return sinhVienList.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtHoTen, txtNamSinh, txtDiaChi;
        private ImageView ivEdit, ivDelete;

        public ViewHolder(View itemView) {
            super(itemView);

            txtHoTen = itemView.findViewById(R.id.textViewHoTen);
            txtNamSinh = itemView.findViewById(R.id.textViewNamSinh);
            txtDiaChi = itemView.findViewById(R.id.textViewDiaChi);
            ivEdit = itemView.findViewById(R.id.imageViewEdit);
            ivDelete = itemView.findViewById(R.id.imageViewDelete);
        }
    }
}
