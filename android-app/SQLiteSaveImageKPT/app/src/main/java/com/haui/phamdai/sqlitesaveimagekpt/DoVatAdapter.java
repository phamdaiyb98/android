package com.haui.phamdai.sqlitesaveimagekpt;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DoVatAdapter extends RecyclerView.Adapter<DoVatAdapter.ViewHolder> {

    private final List<DoVat> doVatList;
    MainActivity context;

    public DoVatAdapter(List<DoVat> doVatList, MainActivity context) {
        this.doVatList = doVatList;
        this.context = context;
    }

    public final class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivPhoto, ivEdit, ivDelete;
        TextView txtName;
        TextView txtDescription;

        public ViewHolder(View itemView) {
            super(itemView);
            ivPhoto = itemView.findViewById(R.id.imageViewPhotoItem);
            txtName = itemView.findViewById(R.id.textViewNameItem);
            txtDescription = itemView.findViewById(R.id.textViewDescriptionItem);
            ivEdit = itemView.findViewById(R.id.imageViewEditItem);
            ivDelete = itemView.findViewById(R.id.imageViewDeleteItem);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.do_vat_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DoVatAdapter.ViewHolder holder, int position) {
        DoVat doVat = doVatList.get(position);

        //chuyển byte[] -> bitmap
        byte[] hinhAnh = doVat.getHinhAnh();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh, 0, hinhAnh.length);

        holder.ivPhoto.setImageBitmap(bitmap);
        holder.txtName.setText(doVat.getTen());
        holder.txtDescription.setText(doVat.getMoTa());


        holder.ivEdit.setOnClickListener(v -> {
//            Log.d("AAA", doVatList.get(position).getTen());
            context.updateDoVat(doVat, position);
        });

        holder.ivDelete.setOnClickListener(v -> {
//            Log.d("AAA", doVatList.get(position).getTen());
            context.deleteDoVat(doVat.getId(), position);
        });
    }

    @Override
    public int getItemCount() {
        return doVatList.size();
    }
}
